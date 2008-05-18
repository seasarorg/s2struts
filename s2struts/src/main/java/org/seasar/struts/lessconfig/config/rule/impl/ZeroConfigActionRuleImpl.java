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
package org.seasar.struts.lessconfig.config.rule.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;

import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.Constants;
import org.seasar.struts.lessconfig.config.AutoStrutsConfigRule;
import org.seasar.struts.lessconfig.config.StrutsActionConfig;
import org.seasar.struts.lessconfig.config.StrutsActionForwardConfig;
import org.seasar.struts.lessconfig.config.rule.ActionPathNamingRule;
import org.seasar.struts.lessconfig.config.rule.ZeroConfigActionRule;

/**
 * {@link ZeroConfigActionRule}の実装クラスです。
 * 
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class ZeroConfigActionRuleImpl implements ZeroConfigActionRule {
    private static final Logger logger = Logger.getLogger(ZeroConfigActionRuleImpl.class);

    private ServletContext servletContext;

    /**
     * {@link ServletContext}を設定します。
     * 
     * @param servletContext
     */
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private ActionPathNamingRule namingRule;

    /**
     * {@link ActionPathNamingRule}を設定します。
     * 
     * @param namingRule
     */
    public void setNamingRule(ActionPathNamingRule namingRule) {
        this.namingRule = namingRule;
    }

    private AutoStrutsConfigRule configRule;

    /**
     * {@link AutoStrutsConfigRule}を設定します。
     * 
     * @param configRule
     */
    public void setAutoStrutsConfigPattern(AutoStrutsConfigRule configRule) {
        this.configRule = configRule;
    }

    private S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }

    public ActionConfig createActionConfig(ModuleConfig config, Class actionClass, String path,
            StrutsActionConfig strutsAction) {

        ActionConfig actionConfig = (ActionConfig) ClassUtil.newInstance(config.getActionMappingClass());
        actionConfig.setModuleConfig(config);
        if (!StrutsActionConfig.DEFAULT_PATH.equals(strutsAction.path())) {
            actionConfig.setPath(strutsAction.path());
        } else {
            actionConfig.setPath(path);
        }

        if (!StrutsActionConfig.DEFAULT_NAME.equals(strutsAction.name())) {
            actionConfig.setName(strutsAction.name());
        } else {
            actionConfig.setName(this.getName(config, actionClass));
        }

        if (!StrutsActionConfig.DEFAULT_ATTRIBUTE.equals(strutsAction.attribute())) {
            actionConfig.setAttribute(strutsAction.attribute());
        }
        if (!StrutsActionConfig.DEFAULT_FORWARD.equals(strutsAction.forward())) {
            actionConfig.setForward(strutsAction.forward());
        }
        if (!StrutsActionConfig.DEFAULT_INCLUDE.equals(strutsAction.include())) {
            actionConfig.setInclude(strutsAction.include());
        }
        if (!StrutsActionConfig.DEFAULT_INPUT.equals(strutsAction.input())) {
            actionConfig.setInput(strutsAction.input());
        }
        if (!StrutsActionConfig.DEFAULT_PARAMETER.equals(strutsAction.parameter())) {
            actionConfig.setParameter(strutsAction.parameter());
        }
        if (!StrutsActionConfig.DEFAULT_PREFIX.equals(strutsAction.prefix())) {
            actionConfig.setPrefix(strutsAction.prefix());
        }
        if (!StrutsActionConfig.DEFAULT_ROLES.equals(strutsAction.roles())) {
            actionConfig.setRoles(strutsAction.roles());
        }
        if (!StrutsActionConfig.DEFAULT_SCOPE.equals(strutsAction.scope())) {
            actionConfig.setScope(strutsAction.scope());
        }
        if (!StrutsActionConfig.DEFAULT_SUFFIX.equals(strutsAction.suffix())) {
            actionConfig.setSuffix(strutsAction.suffix());
        }
        if (StrutsActionConfig.DEFAULT_UNKNOWN != strutsAction.unknown()) {
            actionConfig.setUnknown(strutsAction.unknown().booleanValue());
        }
        if (StrutsActionConfig.DEFAULT_VALIDATE != strutsAction.validate()) {
            actionConfig.setValidate(strutsAction.validate().booleanValue());
        }
        if (StrutsActionConfig.DEFAULT_CANCELLABLE != strutsAction.cancellable()) {
            actionConfig.setCancellable(strutsAction.cancellable().booleanValue());
        }
        if (!StrutsActionConfig.DEFAULT_CATALOG.equals(strutsAction.catalog())) {
            actionConfig.setCatalog(strutsAction.catalog());
        }
        if (!StrutsActionConfig.DEFAULT_COMMAND.equals(strutsAction.command())) {
            actionConfig.setCommand(strutsAction.command());
        }
        if (!StrutsActionConfig.DEFAULT_INHERIT.equals(strutsAction.inherit())) {
            actionConfig.setExtends(strutsAction.inherit());
        }
        if (actionConfig.getForward() == null && actionConfig.getInclude() == null) {
            actionConfig.setType(actionClass.getName());
        }

        return actionConfig;
    }

    protected String getPath(Class actionClass) {
        String path = this.namingRule.toActionPathName(actionClass);
        if (path == null) {
            throw new RuntimeException();
        }
        return path;
    }

    protected String getName(ModuleConfig config, Class actionClass) {
        String name = getFormNameBase(actionClass);
        String formName = name + "Form";
        String dtoName = name + "Dto";
        if (config.findFormBeanConfig(formName) != null) {
            return formName;
        } else if (config.findFormBeanConfig(dtoName) != null) {
            return dtoName;
        } else if (config.findFormBeanConfig(name) != null) {
            return name;
        }
        return null;
    }

    private String getFormNameBase(Class actionClass) {
        S2Container container = getContainer();

        if (!container.hasComponentDef(actionClass)) {
            return null;
        }
        String result = container.getComponentDef(actionClass).getComponentName();
        if (result.startsWith("/")) {
            result = result.substring(1);
        }
        return result.replaceFirst("Action$", "");
    }

    public ForwardConfig createActionForwardConfig(ModuleConfig config, Class actionClass, String name,
            StrutsActionForwardConfig actionForward) {

        ForwardConfig forwardConfig = (ForwardConfig) ClassUtil.newInstance(config.getActionForwardClass());
        forwardConfig.setName(name);
        forwardConfig.setPath(actionForward.path());

        if (StrutsActionForwardConfig.DEFAULT_REDIRECT != actionForward.redirect()) {
            forwardConfig.setRedirect(actionForward.redirect().booleanValue());
        }

        return forwardConfig;
    }

    public void addForwardConfig(ModuleConfig config, Class actionClass, ActionConfig actionConfig) {
        ForwardConfig forwardConfig = actionConfig.findForwardConfig(Constants.SUCCESS);
        if (forwardConfig != null) {
            return;
        }

        String path = null;
        String actionPath = getPath(actionClass);
        String[] viewExtension = this.configRule.getViewExtension();
        for (int i = 0; i < viewExtension.length; i++) {
            String file = actionPath + "." + viewExtension[i];
            path = this.configRule.getDocRoot() + file;
            String packageDir = ClassUtil.getPackageName(actionClass);
            if (packageDir == null) {
                return;
            }
            packageDir = "/" + packageDir.replace('.', '/');

            String docRoot = this.configRule.getDocRoot();
            path = getExistFilePath(docRoot, packageDir, file, actionConfig, servletContext);
            if (path == null) {
                path = getExistResourcePath(docRoot, packageDir, file, actionConfig, servletContext);
            }
            if (path != null) {
                addForwardConfig(config, path, actionConfig);
                return;
            }
        }

        String message = "View file was not found. " + getPath(actionClass) + "."
                + viewExtension[this.configRule.getViewExtension().length - 1];
        logger.info(message);
    }

    protected void addForwardConfig(ModuleConfig config, String path, ActionConfig actionConfig) {
        ForwardConfig forwardConfig = (ForwardConfig) ClassUtil.newInstance(config.getActionForwardClass());
        forwardConfig.setName(Constants.SUCCESS);
        forwardConfig.setPath(path);
        actionConfig.addForwardConfig(forwardConfig);
    }

    protected String getExistFilePath(String docRoot, String packageDir, String file, ActionConfig actionConfig,
            ServletContext servletContext) {
        if (servletContext.getRealPath("/") == null) {
            return null;
        }

        String path = docRoot + packageDir + file;
        String realPath = servletContext.getRealPath(path);
        if (realPath != null && new File(realPath).exists()) {
            return path;
        }
        if (StringUtil.isEmpty(packageDir)) {
            return null;
        } else {
            int index = packageDir.indexOf("/", 1);
            packageDir = packageDir.substring(index + 1);
            if (index < 0) {
                packageDir = "";
            } else {
                packageDir = "/" + packageDir;
            }
            return getExistFilePath(docRoot, packageDir, file, actionConfig, servletContext);
        }
    }

    protected String getExistResourcePath(String docRoot, String packageDir, String file, ActionConfig actionConfig,
            ServletContext servletContext) {
        String path = docRoot + packageDir + file;
        URL resourceUrl;
        try {
            resourceUrl = servletContext.getResource(path);
        } catch (MalformedURLException e) {
            return null;
        }
        if (resourceUrl != null) {
            return path;
        }
        if (StringUtil.isEmpty(packageDir)) {
            return null;
        } else {
            int index = packageDir.indexOf("/", 1);
            packageDir = packageDir.substring(index + 1);
            if (index < 0) {
                packageDir = "";
            } else {
                packageDir = "/" + packageDir;
            }
            return getExistResourcePath(docRoot, packageDir, file, actionConfig, servletContext);
        }
    }

}
