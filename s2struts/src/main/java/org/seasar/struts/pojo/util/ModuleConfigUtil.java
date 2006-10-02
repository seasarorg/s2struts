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
package org.seasar.struts.pojo.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.ModuleUtils;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ModuleConfigUtil {

    private ModuleConfigUtil() {
    }

    public static ModuleConfig getModuleConfig() {
        ServletContext context = S2StrutsContextUtil.getServletContext();
        HttpServletRequest request = S2StrutsContextUtil.getRequest();
        return ModuleUtils.getInstance().getModuleConfig(request, context);
    }

    public static ActionConfig findActionConfigForFormBeanName(String beanName) {
        ModuleConfig config = ModuleConfigUtil.getModuleConfig();
        if (config == null) {
            return null;
        }
        
        FormBeanConfig beanConfig = config.findFormBeanConfig(beanName);
        if (beanConfig == null) {
            return null;
        }

        ActionConfig[] actionConfigs = config.findActionConfigs();
        for (int i = 0; i < actionConfigs.length; i++) {
            ActionConfig actionConfig = actionConfigs[i];
            if (beanConfig.getName().equals(actionConfig.getName())) {
                return actionConfig;
            }
        }
        return null;
    }

}
