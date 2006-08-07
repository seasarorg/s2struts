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
package org.seasar.struts.config.rule;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.struts.action.ActionForward;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.Constants;
import org.seasar.struts.config.AutoStrutsConfigRule;
import org.seasar.struts.config.StrutsActionConfig;
import org.seasar.struts.config.StrutsActionForwardConfig;

/**
 * @author Satoshi Kimura
 */
public class ZeroConfigActionRuleImpl implements ZeroConfigActionRule {
    private static final Logger logger = Logger.getLogger(ZeroConfigActionRuleImpl.class);

    private AutoStrutsConfigRule configRule;

    public String getPath(Class actionClass, ModuleConfig config) {
        return getActionPathName(actionClass, config);
    }

    public String getName(Class actionClass, ModuleConfig config) {
        String name = getActionPathName(actionClass, config).substring(1);
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
    
    private String getActionPathName(Class actionClass, ModuleConfig config) {
        String result = getActionComponentName(actionClass);
        if (result == null) {
            result = ClassUtil.getShortClassName(actionClass);
            result = CommonNamingRule.decapitalizeName(result);
        } else if (isPathComponentName(result)) {
            return toPathComponentName(result, config);
        }
        
        result = result.replaceFirst("Impl$", "");
        result = result.replaceFirst("Action$", "");
        return "/" + result;
    }
    
    private String getActionComponentName(Class actionClass) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        if (!container.hasComponentDef(actionClass)) {
            return null;
        }
        
        ComponentDef componentDef = container.getComponentDef(actionClass);
        if (componentDef == null) {
            return null;
        }
        return componentDef.getComponentName();
    }
    
    private boolean isPathComponentName(String componentName) {
        return componentName.startsWith("/");
    }

    private String toPathComponentName(String componentName, ModuleConfig config) {
        return config.getPrefix() + componentName;
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

    public void addFowardConfig(Class actionClass, ActionConfig actionConfig, ServletContext servletContext) {
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

            path = getExistFilePath(this.configRule.getDocRoot(), packageDir, file, actionConfig, servletContext);
            if (path != null) {
                addFowardConfig(path, actionConfig);
                return;
            }
        }
    }

    private void addFowardConfig(String path, ActionConfig actionConfig) {
        ForwardConfig forwardConfig = new ActionForward();
        forwardConfig.setName(Constants.SUCCESS);
        forwardConfig.setPath(path);
        forwardConfig.setRedirect(StrutsActionForwardConfig.DEFAULT_REDIRECT);
        actionConfig.addForwardConfig(forwardConfig);
    }

    private String getExistFilePath(String docRoot, String packageDir, String file, ActionConfig actionConfig,
            ServletContext servletContext) {
        String path = docRoot + packageDir + file;
        if (new File(servletContext.getRealPath(path)).exists()) {
            return path;
        } else if (StringUtil.isEmpty(packageDir)) {
            if (isLastExtension(file) && actionConfig.findForwardConfigs().length == 0) {
                String message = "View file was not found." + new File(path).getAbsolutePath();
                logger.info(message);
                //throw new IllegalStateException("View file was not found." + new File(path).getAbsolutePath());
            }
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

    private boolean isLastExtension(String file) {
        return file.endsWith(this.configRule.getViewExtension()[this.configRule.getViewExtension().length - 1]);
    }

    public void setAutoStrutsConfigPattern(AutoStrutsConfigRule configRule) {
        this.configRule = configRule;
    }
}
