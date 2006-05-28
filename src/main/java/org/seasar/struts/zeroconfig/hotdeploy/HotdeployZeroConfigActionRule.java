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
package org.seasar.struts.zeroconfig.hotdeploy;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.struts.action.ActionForward;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.Constants;
import org.seasar.struts.zeroconfig.config.StrutsActionConfig;
import org.seasar.struts.zeroconfig.config.StrutsActionForwardConfig;
import org.seasar.struts.zeroconfig.config.rule.ZeroConfigActionRule;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class HotdeployZeroConfigActionRule implements ZeroConfigActionRule {

    private static final Logger logger = Logger.getLogger(HotdeployZeroConfigActionRule.class);
    
    private NamingRule namingRule;
    
    public void setNamingRule(NamingRule namingRule) {
        this.namingRule = namingRule;
    }
    
    private AutoActionForwardRule forwardRule;
    
    public void setForwardRule(AutoActionForwardRule forwardRule) {
        this.forwardRule = forwardRule;
    }
    
    private AutoActionFormRule formRule;
    
    public void setFormRule(AutoActionFormRule formRule) {
        this.formRule = formRule;
    }

    public String getPath(Class actionClass, ModuleConfig config) {
        throw new  UnsupportedOperationException();
    }

    public String getName(Class actionClass, ModuleConfig config) {
        String name = namingRule.defineName(actionClass).substring(1);
        String formName = name + formRule.getActionFormSuffix();
        if (config.findFormBeanConfig(formName) != null) {
            return formName;
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

    public void addFowardConfig(Class actionClass, ActionConfig actionConfig, ServletContext servletContext) {
        ForwardConfig forwardConfig = actionConfig.findForwardConfig(Constants.SUCCESS);
        if (forwardConfig != null) {
            return;
        }

        String path = null;
        String[] viewExtension = this.forwardRule.getViewExtension();
        for (int i = 0; i < viewExtension.length; i++) {
            String file = getPath(actionClass, null) + "." + viewExtension[i];
            path = this.forwardRule.getDocRoot() + file;
            String packageDir = "/" + actionClass.getPackage().getName().replace('.', '/');

            path = getExistFilePath(this.forwardRule.getDocRoot(), packageDir, file, actionConfig, servletContext);
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
        return file.endsWith(this.forwardRule.getViewExtension()[this.forwardRule.getViewExtension().length - 1]);
    }

}
