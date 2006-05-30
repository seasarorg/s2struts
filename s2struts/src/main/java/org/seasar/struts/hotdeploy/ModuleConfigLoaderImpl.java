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
package org.seasar.struts.hotdeploy;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSet;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ConfigRuleSet;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.ModuleConfigFactory;
import org.apache.struts.util.RequestUtils;
import org.seasar.framework.log.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class ModuleConfigLoaderImpl implements ModuleConfigLoader {

    private static final Logger log = Logger.getLogger(ReloadFindActionConfigInterceptor.class);

    public ModuleConfig load(String prefix) throws ServletException {
        ModuleConfig config;
        if ("".equals(prefix)) {
            config = initModuleConfig(prefix, "/WEB-INF/struts-config.xml");
        } else {
            String name = "config/" + prefix;
            config = initModuleConfig(prefix, getServletConfig().getInitParameter(name));
        }
        config.freeze();
        return config;
    }

    //
    //
    //

    private ServletContext servletContext;

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

    protected String registrations[] = {
            "-//Apache Software Foundation//DTD Struts Configuration 1.0//EN",
            "/org/apache/struts/resources/struts-config_1_0.dtd",
            "-//Apache Software Foundation//DTD Struts Configuration 1.1//EN",
            "/org/apache/struts/resources/struts-config_1_1.dtd",
            "-//Apache Software Foundation//DTD Struts Configuration 1.2//EN",
            "/org/apache/struts/resources/struts-config_1_2.dtd",
            "-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN",
            "/org/apache/struts/resources/web-app_2_2.dtd",
            "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN",
            "/org/apache/struts/resources/web-app_2_3.dtd" };

    protected ModuleConfig initModuleConfig(String prefix, String paths) throws ServletException {

        // :FIXME: Document UnavailableException? (Doesn't actually throw
        // anything)

        if (log.isDebugEnabled()) {
            log.debug("Initializing module path '" + prefix + "' configuration from '" + paths
                    + "'");
        }

        // Parse the configuration for this module
        ModuleConfigFactory factoryObject = ModuleConfigFactory.createFactory();
        ModuleConfig config = factoryObject.createModuleConfig(prefix);

        // Configure the Digester instance we will use
        Digester digester = initConfigDigester();

        // Process each specified resource path
        while (paths.length() > 0) {
            digester.push(config);
            String path = null;
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

            this.parseModuleConfigFile(digester, path);
        }

        // Force creation and registration of DynaActionFormClass instances
        // for all dynamic form beans we wil be using
        FormBeanConfig fbs[] = config.findFormBeanConfigs();
        for (int i = 0; i < fbs.length; i++) {
            if (fbs[i].getDynamic()) {
                fbs[i].getDynaActionFormClass();
            }
        }

        return config;
    }

    protected Digester initConfigDigester() throws ServletException {

        // :FIXME: Where can ServletException be thrown?

        // Create a new Digester instance with standard capabilities
        Digester configDigester = new Digester();
        configDigester.setNamespaceAware(true);
        configDigester.setValidating(false);
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
        String ruleset = null;
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

    protected void parseModuleConfigFile(Digester digester, String path)
            throws UnavailableException {

        InputStream input = null;
        try {
            URL url = getServletContext().getResource(path);

            // If the config isn't in the servlet context, try the class loader
            // which allows the config files to be stored in a jar
            if (url == null) {
                url = getClass().getResource(path);
            }

            if (url == null) {
                String msg = getActionServlet().getInternal().getMessage("configMissing", path);
                log.error(msg);
                throw new UnavailableException(msg);
            }

            InputSource is = new InputSource(url.toExternalForm());
            input = url.openStream();
            is.setByteStream(input);
            digester.parse(is);

        } catch (MalformedURLException e) {
            handleConfigException(path, e);
        } catch (IOException e) {
            handleConfigException(path, e);
        } catch (SAXException e) {
            handleConfigException(path, e);
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

    private void handleConfigException(String path, Exception e) throws UnavailableException {

        String msg = getActionServlet().getInternal().getMessage("configParse", path);
        log.error(msg, e);
        throw new UnavailableException(msg);
    }

}
