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

import org.apache.struts.action.ActionForward;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
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
 * @author Satoshi Kimura
 */
public class ZeroConfigActionRuleImpl implements ZeroConfigActionRule {
    private static final Logger logger = Logger.getLogger(ZeroConfigActionRuleImpl.class);

    private ActionPathNamingRule namingRule;

    public void setNamingRule(ActionPathNamingRule namingRule) {
        this.namingRule = namingRule;
    }

    private AutoStrutsConfigRule configRule;

    public void setAutoStrutsConfigPattern(AutoStrutsConfigRule configRule) {
        this.configRule = configRule;
    }

    public String getPath(Class actionClass, ModuleConfig config) {
        String path = this.namingRule.toActionPathName(actionClass);
        if (path == null) {
            throw new RuntimeException();
        }
        return path;
    }

    public String getName(Class actionClass, ModuleConfig config) {
        String name = getPath(actionClass, config).substring(1);
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

    public String getScope(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_SCOPE;
    }

    public boolean getValidate(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_VALIDATE;
    }

    public String getInput(Class actionClass, ModuleConfig config) {
        return null;
    }

    public String getParameter(Class actionClass, ModuleConfig config) {
        return null;
    }

    public String getAttribute(Class actionClass, ModuleConfig config) {
        return null;
    }

    public String getForward(Class actionClass, ModuleConfig config) {
        return null;
    }

    public String getInclude(Class actionClass, ModuleConfig config) {
        return null;
    }

    public String getPrefix(Class actionClass, ModuleConfig config) {
        return null;
    }

    public String getSuffix(Class actionClass, ModuleConfig config) {
        return null;
    }

    public boolean getUnknown(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_UNKNOWN;
    }

    public boolean getCancellable(Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_CANCELLABLE;
    }

    public String getRoles(Class actionClass, ModuleConfig config) {
        return null;
    }

    public void addForwardConfig(Class actionClass, ActionConfig actionConfig, ServletContext servletContext) {
        ForwardConfig forwardConfig = actionConfig.findForwardConfig(Constants.SUCCESS);
        if (forwardConfig != null) {
            return;
        }

        String path = null;
        String[] viewExtension = this.configRule.getViewExtension();
        for (int i = 0; i < viewExtension.length; i++) {
            String file = getPath(actionClass, null) + "." + viewExtension[i];
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
                addForwardConfig(path, actionConfig);
                return;
            }
        }

        String message = "View file was not found. " + getPath(actionClass, null) + "."
                + viewExtension[this.configRule.getViewExtension().length - 1];
        logger.info(message);
    }

    private void addForwardConfig(String path, ActionConfig actionConfig) {
        ForwardConfig forwardConfig = new ActionForward();
        forwardConfig.setName(Constants.SUCCESS);
        forwardConfig.setPath(path);
        forwardConfig.setRedirect(StrutsActionForwardConfig.DEFAULT_REDIRECT);
        actionConfig.addForwardConfig(forwardConfig);
    }

    private String getExistFilePath(String docRoot, String packageDir, String file,
            ActionConfig actionConfig, ServletContext servletContext) {
        if (servletContext.getRealPath("/") == null) {
            return null;
        }

        String path = docRoot + packageDir + file;
        String realPath = servletContext.getRealPath(path);
        if (new File(realPath).exists()) {
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

    private String getExistResourcePath(String docRoot, String packageDir, String file,
            ActionConfig actionConfig, ServletContext servletContext) {
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
