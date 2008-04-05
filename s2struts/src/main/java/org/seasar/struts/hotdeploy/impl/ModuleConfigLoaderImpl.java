/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.struts.hotdeploy.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSet;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ConfigRuleSet;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.FormPropertyConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.ModuleConfigFactory;
import org.apache.struts.util.RequestUtils;
import org.seasar.framework.log.Logger;
import org.seasar.struts.hotdeploy.ModuleConfigLoader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * {@link ModuleConfigLoader}をロードします。
 * 
 * @author Katsuhiko Nagashima
 */
public class ModuleConfigLoaderImpl implements ModuleConfigLoader {

    private static final Logger log = Logger.getLogger(ModuleConfigLoaderImpl.class);

    public ModuleConfig load(String prefix) throws ServletException {
        ModuleConfig moduleConfig;
        if ("".equals(prefix)) {
            String config = "/WEB-INF/struts-config.xml";
            String value = getServletConfig().getInitParameter("config");
            if (value != null) {
                config = value;
            }
            moduleConfig = initModuleConfig(prefix, config);
        } else {
            String name = "config/" + prefix;
            moduleConfig = initModuleConfig(prefix, getServletConfig().getInitParameter(name));
        }
        initModuleFormBeans(moduleConfig);
        initModuleForwards(moduleConfig);
        initModuleExceptionConfigs(moduleConfig);
        initModuleActions(moduleConfig);
        moduleConfig.freeze();
        return moduleConfig;
    }

    //
    //
    //

    private ServletContext servletContext;

    /**
     * {@link ServletContext}を設定します。
     * 
     * @param servletContext
     */
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private ServletContext getServletContext() {
        return this.servletContext;
    }

    private ActionServlet getActionServlet() {
        return (ActionServlet) this.servletContext.getAttribute(Globals.ACTION_SERVLET_KEY);
    }

    private ServletConfig getServletConfig() {
        return getActionServlet().getServletConfig();
    }

    //
    // Copy from ActionServlet
    //

    protected String[] registrations = { "-//Apache Software Foundation//DTD Struts Configuration 1.0//EN",
            "/org/apache/struts/resources/struts-config_1_0.dtd",
            "-//Apache Software Foundation//DTD Struts Configuration 1.1//EN",
            "/org/apache/struts/resources/struts-config_1_1.dtd",
            "-//Apache Software Foundation//DTD Struts Configuration 1.2//EN",
            "/org/apache/struts/resources/struts-config_1_2.dtd",
            "-//Apache Software Foundation//DTD Struts Configuration 1.3//EN",
            "/org/apache/struts/resources/struts-config_1_3.dtd",
            "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN", "/org/apache/struts/resources/web-app_2_3.dtd" };

    protected ModuleConfig initModuleConfig(String prefix, String paths) throws ServletException {

        if (log.isDebugEnabled()) {
            log.debug("Initializing module path '" + prefix + "' configuration from '" + paths + "'");
        }

        // Parse the configuration for this module
        ModuleConfigFactory factoryObject = ModuleConfigFactory.createFactory();
        ModuleConfig config = factoryObject.createModuleConfig(prefix);

        // Configure the Digester instance we will use
        Digester digester = initConfigDigester();

        List urls = splitAndResolvePaths(paths);
        URL url;

        for (Iterator i = urls.iterator(); i.hasNext();) {
            url = (URL) i.next();
            digester.push(config);
            this.parseModuleConfigFile(digester, url);
        }

        return config;
    }

    protected Digester initConfigDigester() throws ServletException {
        // Create a new Digester instance with standard capabilities
        Digester configDigester = new Digester();
        configDigester.setNamespaceAware(true);
        configDigester.setValidating(this.isValidating());
        configDigester.setUseContextClassLoader(true);
        configDigester.addRuleSet(new ConfigRuleSet());

        for (int i = 0; i < registrations.length; i += 2) {
            URL url = this.getClass().getResource(registrations[i + 1]);

            if (url != null) {
                configDigester.register(registrations[i], url.toString());
            }
        }

        this.addRuleSets(configDigester);

        // Return the completely configured Digester instance
        return (configDigester);
    }

    private void addRuleSets(Digester configDigester) throws ServletException {
        String rulesets = getServletConfig().getInitParameter("rulesets");

        if (rulesets == null) {
            rulesets = "";
        }

        rulesets = rulesets.trim();

        String ruleset;

        while (rulesets.length() > 0) {
            int comma = rulesets.indexOf(",");

            if (comma < 0) {
                ruleset = rulesets.trim();
                rulesets = "";
            } else {
                ruleset = rulesets.substring(0, comma).trim();
                rulesets = rulesets.substring(comma + 1).trim();
            }

            if (log.isDebugEnabled()) {
                log.debug("Configuring custom Digester Ruleset of type " + ruleset);
            }

            try {
                RuleSet instance = (RuleSet) RequestUtils.applicationInstance(ruleset);

                configDigester.addRuleSet(instance);
            } catch (Exception e) {
                log.error("Exception configuring custom Digester RuleSet", e);
                throw new ServletException(e);
            }
        }
    }

    /**
     * <p>
     * Check the status of the <code>validating</code> initialization
     * parameter.
     * </p>
     * 
     * @return true if the module Digester should validate.
     */
    private boolean isValidating() {
        boolean validating = true;
        String value = getServletConfig().getInitParameter("validating");

        if ("false".equalsIgnoreCase(value) || "no".equalsIgnoreCase(value) || "n".equalsIgnoreCase(value)
                || "0".equalsIgnoreCase(value)) {
            validating = false;
        }

        return validating;
    }

    protected void parseModuleConfigFile(Digester digester, URL url) throws UnavailableException {
        InputStream input = null;

        try {
            InputSource is = new InputSource(url.toExternalForm());
            URLConnection conn = url.openConnection();

            conn.setUseCaches(false);
            conn.connect();
            input = conn.getInputStream();
            is.setByteStream(input);
            digester.parse(is);
        } catch (IOException e) {
            handleConfigException(url.toString(), e);
        } catch (SAXException e) {
            handleConfigException(url.toString(), e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    throw new UnavailableException(e.getMessage());
                }
            }
        }
    }

    /**
     * <p>
     * Simplifies exception handling in the parseModuleConfigFile method.
     * <p>
     * 
     * @param path
     *            The path to which the exception relates.
     * @param e
     *            The exception to be wrapped and thrown.
     * @throws UnavailableException
     *             as a wrapper around Exception
     */
    private void handleConfigException(String path, Exception e) throws UnavailableException {
        String msg = getActionServlet().getInternal().getMessage("configParse", path);

        log.error(msg, e);
        throw new UnavailableException(msg);
    }

    /**
     * <p>
     * Handle errors related to creating an instance of the specified class.
     * </p>
     * 
     * @param className
     *            The className that could not be instantiated.
     * @param e
     *            The exception that was caught.
     * @throws ServletException
     *             to communicate the error.
     */
    private void handleCreationException(String className, Exception e) throws ServletException {
        String errorMessage = getActionServlet().getInternal().getMessage("configExtends.creation", className);

        log.error(errorMessage, e);
        throw new UnavailableException(errorMessage);
    }

    /**
     * <p>
     * General handling for exceptions caught while inheriting config
     * information.
     * </p>
     * 
     * @param configType
     *            The type of configuration object of configName.
     * @param configName
     *            The name of the config that could not be extended.
     * @param e
     *            The exception that was caught.
     * @throws ServletException
     *             to communicate the error.
     */
    private void handleGeneralExtensionException(String configType, String configName, Exception e)
            throws ServletException {
        String errorMessage = getActionServlet().getInternal().getMessage("configExtends", configType, configName);

        log.error(errorMessage, e);
        throw new UnavailableException(errorMessage);
    }

    /**
     * <p>
     * Handle errors caused by required fields that were not specified.
     * </p>
     * 
     * @param field
     *            The name of the required field that was not found.
     * @param configType
     *            The type of configuration object of configName.
     * @param configName
     *            The name of the config that's missing the required value.
     * @throws ServletException
     *             to communicate the error.
     */
    private void handleValueRequiredException(String field, String configType, String configName)
            throws ServletException {
        String errorMessage = getActionServlet().getInternal().getMessage("configFieldRequired", field, configType,
                configName);

        log.error(errorMessage);
        throw new UnavailableException(errorMessage);
    }

    protected List splitAndResolvePaths(String paths) throws ServletException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        if (loader == null) {
            loader = this.getClass().getClassLoader();
        }

        ArrayList resolvedUrls = new ArrayList();

        URL resource;
        String path = null;

        try {
            // Process each specified resource path
            while (paths.length() > 0) {
                resource = null;

                int comma = paths.indexOf(',');

                if (comma >= 0) {
                    path = paths.substring(0, comma).trim();
                    paths = paths.substring(comma + 1);
                } else {
                    path = paths.trim();
                    paths = "";
                }

                if (path.length() < 1) {
                    break;
                }

                if (path.charAt(0) == '/') {
                    resource = getServletContext().getResource(path);
                }

                if (resource == null) {
                    if (log.isDebugEnabled()) {
                        log.debug("Unable to locate " + path + " in the servlet context, " + "trying classloader.");
                    }

                    Enumeration e = loader.getResources(path);

                    if (!e.hasMoreElements()) {
                        String msg = getActionServlet().getInternal().getMessage("configMissing", path);

                        log.error(msg);
                        throw new UnavailableException(msg);
                    } else {
                        while (e.hasMoreElements()) {
                            resolvedUrls.add(e.nextElement());
                        }
                    }
                } else {
                    resolvedUrls.add(resource);
                }
            }
        } catch (MalformedURLException e) {
            handleConfigException(path, e);
        } catch (IOException e) {
            handleConfigException(path, e);
        }

        return resolvedUrls;
    }

    /**
     * <p>
     * Initialize the form beans for the specified module.
     * </p>
     * 
     * @param config
     *            ModuleConfig information for this module
     * @throws ServletException
     *             if initialization cannot be performed
     * @since Struts 1.3
     */
    protected void initModuleFormBeans(ModuleConfig config) throws ServletException {
        if (log.isDebugEnabled()) {
            log.debug("Initializing module path '" + config.getPrefix() + "' form beans");
        }

        // Process form bean extensions.
        FormBeanConfig[] formBeans = config.findFormBeanConfigs();

        for (int i = 0; i < formBeans.length; i++) {
            FormBeanConfig beanConfig = formBeans[i];

            processFormBeanExtension(beanConfig, config);
        }

        for (int i = 0; i < formBeans.length; i++) {
            FormBeanConfig formBean = formBeans[i];

            // Verify that required fields are all present for the form config
            if (formBean.getType() == null) {
                handleValueRequiredException("type", formBean.getName(), "form bean");
            }

            // ... and the property configs
            FormPropertyConfig[] fpcs = formBean.findFormPropertyConfigs();

            for (int j = 0; j < fpcs.length; j++) {
                FormPropertyConfig property = fpcs[j];

                if (property.getType() == null) {
                    handleValueRequiredException("type", property.getName(), "form property");
                }
            }

            // Force creation and registration of DynaActionFormClass instances
            // for all dynamic form beans
            if (formBean.getDynamic()) {
                formBean.getDynaActionFormClass();
            }
        }
    }

    /**
     * <p>
     * Extend the form bean's configuration as necessary.
     * </p>
     * 
     * @param beanConfig
     *            the configuration to process.
     * @param moduleConfig
     *            the module configuration for this module.
     * @throws ServletException
     *             if initialization cannot be performed.
     */
    protected void processFormBeanExtension(FormBeanConfig beanConfig, ModuleConfig moduleConfig)
            throws ServletException {
        try {
            if (!beanConfig.isExtensionProcessed()) {
                if (log.isDebugEnabled()) {
                    log.debug("Processing extensions for '" + beanConfig.getName() + "'");
                }

                beanConfig = processFormBeanConfigClass(beanConfig, moduleConfig);

                beanConfig.processExtends(moduleConfig);
            }
        } catch (ServletException e) {
            throw e;
        } catch (Exception e) {
            handleGeneralExtensionException("FormBeanConfig", beanConfig.getName(), e);
        }
    }

    /**
     * <p>
     * Checks if the current beanConfig is using the correct class based on the
     * class of its ancestor form bean config.
     * </p>
     * 
     * @param beanConfig
     *            The form bean to check.
     * @param moduleConfig
     *            The config for the current module.
     * @return The form bean config using the correct class as determined by the
     *         config's ancestor and its own overridden value.
     * @throws UnavailableException
     *             if an instance of the form bean config class cannot be
     *             created.
     * @throws ServletException
     *             on class creation error
     */
    protected FormBeanConfig processFormBeanConfigClass(FormBeanConfig beanConfig, ModuleConfig moduleConfig)
            throws ServletException {
        String ancestor = beanConfig.getExtends();

        if (ancestor == null) {
            // Nothing to do, then
            return beanConfig;
        }

        // Make sure that this bean is of the right class
        FormBeanConfig baseConfig = moduleConfig.findFormBeanConfig(ancestor);

        if (baseConfig == null) {
            throw new UnavailableException("Unable to find " + "form bean '" + ancestor + "' to extend.");
        }

        // Was our bean's class overridden already?
        if (beanConfig.getClass().equals(FormBeanConfig.class)) {
            // Ensure that our bean is using the correct class
            if (!baseConfig.getClass().equals(beanConfig.getClass())) {
                // Replace the bean with an instance of the correct class
                FormBeanConfig newBeanConfig = null;
                String baseConfigClassName = baseConfig.getClass().getName();

                try {
                    newBeanConfig = (FormBeanConfig) RequestUtils.applicationInstance(baseConfigClassName);

                    // copy the values
                    BeanUtils.copyProperties(newBeanConfig, beanConfig);

                    FormPropertyConfig[] fpc = beanConfig.findFormPropertyConfigs();

                    for (int i = 0; i < fpc.length; i++) {
                        newBeanConfig.addFormPropertyConfig(fpc[i]);
                    }
                } catch (Exception e) {
                    handleCreationException(baseConfigClassName, e);
                }

                // replace beanConfig with newBeanConfig
                moduleConfig.removeFormBeanConfig(beanConfig);
                moduleConfig.addFormBeanConfig(newBeanConfig);
                beanConfig = newBeanConfig;
            }
        }

        return beanConfig;
    }

    /**
     * <p>
     * Initialize the forwards for the specified module.
     * </p>
     * 
     * @param config
     *            ModuleConfig information for this module
     * @throws ServletException
     *             if initialization cannot be performed
     */
    protected void initModuleForwards(ModuleConfig config) throws ServletException {
        if (log.isDebugEnabled()) {
            log.debug("Initializing module path '" + config.getPrefix() + "' forwards");
        }

        // Process forwards extensions.
        ForwardConfig[] forwards = config.findForwardConfigs();

        for (int i = 0; i < forwards.length; i++) {
            ForwardConfig forward = forwards[i];

            processForwardExtension(forward, config);
        }

        for (int i = 0; i < forwards.length; i++) {
            ForwardConfig forward = forwards[i];

            // Verify that required fields are all present for the forward
            if (forward.getPath() == null) {
                handleValueRequiredException("path", forward.getName(), "global forward");
            }
        }
    }

    /**
     * <p>
     * Extend the forward's configuration as necessary.
     * </p>
     * 
     * @param forwardConfig
     *            the configuration to process.
     * @param moduleConfig
     *            the module configuration for this module.
     * @throws ServletException
     *             if initialization cannot be performed.
     */
    protected void processForwardExtension(ForwardConfig forwardConfig, ModuleConfig moduleConfig)
            throws ServletException {
        try {
            if (!forwardConfig.isExtensionProcessed()) {
                if (log.isDebugEnabled()) {
                    log.debug("Processing extensions for '" + forwardConfig.getName() + "'");
                }

                forwardConfig = processForwardConfigClass(forwardConfig, moduleConfig);

                forwardConfig.processExtends(moduleConfig, null);
            }
        } catch (ServletException e) {
            throw e;
        } catch (Exception e) {
            handleGeneralExtensionException("Forward", forwardConfig.getName(), e);
        }
    }

    /**
     * <p>
     * Checks if the current forwardConfig is using the correct class based on
     * the class of its configuration ancestor.
     * </p>
     * 
     * @param forwardConfig
     *            The forward to check.
     * @param moduleConfig
     *            The config for the current module.
     * @return The forward config using the correct class as determined by the
     *         config's ancestor and its own overridden value.
     * @throws UnavailableException
     *             if an instance of the forward config class cannot be created.
     * @throws ServletException
     *             on class creation error
     */
    protected ForwardConfig processForwardConfigClass(ForwardConfig forwardConfig, ModuleConfig moduleConfig)
            throws ServletException {
        String ancestor = forwardConfig.getExtends();

        if (ancestor == null) {
            // Nothing to do, then
            return forwardConfig;
        }

        // Make sure that this config is of the right class
        ForwardConfig baseConfig = moduleConfig.findForwardConfig(ancestor);

        if (baseConfig == null) {
            throw new UnavailableException("Unable to find " + "forward '" + ancestor + "' to extend.");
        }

        // Was our forwards's class overridden already?
        if (forwardConfig.getClass().equals(ActionForward.class)) {
            // Ensure that our forward is using the correct class
            if (!baseConfig.getClass().equals(forwardConfig.getClass())) {
                // Replace the config with an instance of the correct class
                ForwardConfig newForwardConfig = null;
                String baseConfigClassName = baseConfig.getClass().getName();

                try {
                    newForwardConfig = (ForwardConfig) RequestUtils.applicationInstance(baseConfigClassName);

                    // copy the values
                    BeanUtils.copyProperties(newForwardConfig, forwardConfig);
                } catch (Exception e) {
                    handleCreationException(baseConfigClassName, e);
                }

                // replace forwardConfig with newForwardConfig
                moduleConfig.removeForwardConfig(forwardConfig);
                moduleConfig.addForwardConfig(newForwardConfig);
                forwardConfig = newForwardConfig;
            }
        }

        return forwardConfig;
    }

    /**
     * <p>
     * Initialize the exception handlers for the specified module.
     * </p>
     * 
     * @param config
     *            ModuleConfig information for this module
     * @throws ServletException
     *             if initialization cannot be performed
     * @since Struts 1.3
     */
    protected void initModuleExceptionConfigs(ModuleConfig config) throws ServletException {
        if (log.isDebugEnabled()) {
            log.debug("Initializing module path '" + config.getPrefix() + "' forwards");
        }

        // Process exception config extensions.
        ExceptionConfig[] exceptions = config.findExceptionConfigs();

        for (int i = 0; i < exceptions.length; i++) {
            ExceptionConfig exception = exceptions[i];

            processExceptionExtension(exception, config);
        }

        for (int i = 0; i < exceptions.length; i++) {
            ExceptionConfig exception = exceptions[i];

            // Verify that required fields are all present for the config
            if (exception.getKey() == null) {
                handleValueRequiredException("key", exception.getType(), "global exception config");
            }
        }
    }

    /**
     * <p>
     * Extend the exception's configuration as necessary.
     * </p>
     * 
     * @param exceptionConfig
     *            the configuration to process.
     * @param moduleConfig
     *            the module configuration for this module.
     * @throws ServletException
     *             if initialization cannot be performed.
     */
    protected void processExceptionExtension(ExceptionConfig exceptionConfig, ModuleConfig moduleConfig)
            throws ServletException {
        try {
            if (!exceptionConfig.isExtensionProcessed()) {
                if (log.isDebugEnabled()) {
                    log.debug("Processing extensions for '" + exceptionConfig.getType() + "'");
                }

                exceptionConfig = processExceptionConfigClass(exceptionConfig, moduleConfig);

                exceptionConfig.processExtends(moduleConfig, null);
            }
        } catch (ServletException e) {
            throw e;
        } catch (Exception e) {
            handleGeneralExtensionException("Exception", exceptionConfig.getType(), e);
        }
    }

    /**
     * <p>
     * Checks if the current exceptionConfig is using the correct class based on
     * the class of its configuration ancestor.
     * </p>
     * 
     * @param exceptionConfig
     *            The config to check.
     * @param moduleConfig
     *            The config for the current module.
     * @return The exception config using the correct class as determined by the
     *         config's ancestor and its own overridden value.
     * @throws ServletException
     *             if an instance of the exception config class cannot be
     *             created.
     */
    protected ExceptionConfig processExceptionConfigClass(ExceptionConfig exceptionConfig, ModuleConfig moduleConfig)
            throws ServletException {
        String ancestor = exceptionConfig.getExtends();

        if (ancestor == null) {
            // Nothing to do, then
            return exceptionConfig;
        }

        // Make sure that this config is of the right class
        ExceptionConfig baseConfig = moduleConfig.findExceptionConfig(ancestor);

        if (baseConfig == null) {
            throw new UnavailableException("Unable to find " + "exception config '" + ancestor + "' to extend.");
        }

        // Was our config's class overridden already?
        if (exceptionConfig.getClass().equals(ExceptionConfig.class)) {
            // Ensure that our config is using the correct class
            if (!baseConfig.getClass().equals(exceptionConfig.getClass())) {
                // Replace the config with an instance of the correct class
                ExceptionConfig newExceptionConfig = null;
                String baseConfigClassName = baseConfig.getClass().getName();

                try {
                    newExceptionConfig = (ExceptionConfig) RequestUtils.applicationInstance(baseConfigClassName);

                    // copy the values
                    BeanUtils.copyProperties(newExceptionConfig, exceptionConfig);
                } catch (Exception e) {
                    handleCreationException(baseConfigClassName, e);
                }

                // replace exceptionConfig with newExceptionConfig
                moduleConfig.removeExceptionConfig(exceptionConfig);
                moduleConfig.addExceptionConfig(newExceptionConfig);
                exceptionConfig = newExceptionConfig;
            }
        }

        return exceptionConfig;
    }

    /**
     * <p>
     * Initialize the action configs for the specified module.
     * </p>
     * 
     * @param config
     *            ModuleConfig information for this module
     * @throws ServletException
     *             if initialization cannot be performed
     * @since Struts 1.3
     */
    protected void initModuleActions(ModuleConfig config) throws ServletException {
        if (log.isDebugEnabled()) {
            log.debug("Initializing module path '" + config.getPrefix() + "' action configs");
        }

        // Process ActionConfig extensions.
        ActionConfig[] actionConfigs = config.findActionConfigs();

        for (int i = 0; i < actionConfigs.length; i++) {
            ActionConfig actionConfig = actionConfigs[i];

            processActionConfigExtension(actionConfig, config);
        }

        for (int i = 0; i < actionConfigs.length; i++) {
            ActionConfig actionConfig = actionConfigs[i];

            // Verify that required fields are all present for the forward
            // configs
            ForwardConfig[] forwards = actionConfig.findForwardConfigs();

            for (int j = 0; j < forwards.length; j++) {
                ForwardConfig forward = forwards[j];

                if (forward.getPath() == null) {
                    handleValueRequiredException("path", forward.getName(), "action forward");
                }
            }

            // ... and the exception configs
            ExceptionConfig[] exceptions = actionConfig.findExceptionConfigs();

            for (int j = 0; j < exceptions.length; j++) {
                ExceptionConfig exception = exceptions[j];

                if (exception.getKey() == null) {
                    handleValueRequiredException("key", exception.getType(), "action exception config");
                }
            }
        }
    }

    /**
     * <p>
     * Extend the action's configuration as necessary.
     * </p>
     * 
     * @param actionConfig
     *            the configuration to process.
     * @param moduleConfig
     *            the module configuration for this module.
     * @throws ServletException
     *             if initialization cannot be performed.
     */
    protected void processActionConfigExtension(ActionConfig actionConfig, ModuleConfig moduleConfig)
            throws ServletException {
        try {
            if (!actionConfig.isExtensionProcessed()) {
                if (log.isDebugEnabled()) {
                    log.debug("Processing extensions for '" + actionConfig.getPath() + "'");
                }

                actionConfig = processActionConfigClass(actionConfig, moduleConfig);

                actionConfig.processExtends(moduleConfig);
            }
        } catch (ServletException e) {
            throw e;
        } catch (Exception e) {
            handleGeneralExtensionException("Action", actionConfig.getPath(), e);
        }
    }

    /**
     * <p>
     * Checks if the current actionConfig is using the correct class based on
     * the class of its ancestor ActionConfig.
     * </p>
     * 
     * @param actionConfig
     *            The action config to check.
     * @param moduleConfig
     *            The config for the current module.
     * @return The config object using the correct class as determined by the
     *         config's ancestor and its own overridden value.
     * @throws ServletException
     *             if an instance of the action config class cannot be created.
     */
    protected ActionConfig processActionConfigClass(ActionConfig actionConfig, ModuleConfig moduleConfig)
            throws ServletException {
        String ancestor = actionConfig.getExtends();

        if (ancestor == null) {
            // Nothing to do, then
            return actionConfig;
        }

        // Make sure that this config is of the right class
        ActionConfig baseConfig = moduleConfig.findActionConfig(ancestor);

        if (baseConfig == null) {
            throw new UnavailableException("Unable to find " + "action config for '" + ancestor + "' to extend.");
        }

        // Was our actionConfig's class overridden already?
        if (actionConfig.getClass().equals(ActionMapping.class)) {
            // Ensure that our config is using the correct class
            if (!baseConfig.getClass().equals(actionConfig.getClass())) {
                // Replace the config with an instance of the correct class
                ActionConfig newActionConfig = null;
                String baseConfigClassName = baseConfig.getClass().getName();

                try {
                    newActionConfig = (ActionConfig) RequestUtils.applicationInstance(baseConfigClassName);

                    // copy the values
                    BeanUtils.copyProperties(newActionConfig, actionConfig);

                    // copy the forward and exception configs, too
                    ForwardConfig[] forwards = actionConfig.findForwardConfigs();

                    for (int i = 0; i < forwards.length; i++) {
                        newActionConfig.addForwardConfig(forwards[i]);
                    }

                    ExceptionConfig[] exceptions = actionConfig.findExceptionConfigs();

                    for (int i = 0; i < exceptions.length; i++) {
                        newActionConfig.addExceptionConfig(exceptions[i]);
                    }
                } catch (Exception e) {
                    handleCreationException(baseConfigClassName, e);
                }

                // replace actionConfig with newActionConfig
                moduleConfig.removeActionConfig(actionConfig);
                moduleConfig.addActionConfig(newActionConfig);
                actionConfig = newActionConfig;
            }
        }

        return actionConfig;
    }

}
