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
package org.seasar.struts.lessconfig.autoregister.impl;

import java.lang.reflect.Field;

import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.util.FieldUtil;
import org.seasar.struts.lessconfig.autoregister.ActionConfigCreator;
import org.seasar.struts.lessconfig.config.AutoStrutsConfigRule;
import org.seasar.struts.lessconfig.config.StrutsActionConfig;
import org.seasar.struts.lessconfig.config.StrutsActionForwardConfig;
import org.seasar.struts.lessconfig.config.rule.ActionPathNamingRule;
import org.seasar.struts.lessconfig.config.rule.ZeroConfigActionRule;
import org.seasar.struts.lessconfig.factory.StrutsConfigAnnotationHandler;
import org.seasar.struts.lessconfig.factory.StrutsConfigAnnotationHandlerFactory;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class ActionConfigCreatorImpl implements ActionConfigCreator {

    private ZeroConfigActionRule defaultRule;

    public void setDefaultRule(ZeroConfigActionRule defaultRule) {
        this.defaultRule = defaultRule;
    }

    private AutoStrutsConfigRule configRule;

    public void setConfigRule(AutoStrutsConfigRule configRule) {
        this.configRule = configRule;
    }

    private ActionPathNamingRule namingRule;

    public void setNamingRule(ActionPathNamingRule namingRule) {
        this.namingRule = namingRule;
    }

    public ActionConfig createActionConfig(ModuleConfig config, String path) {
        Class actionClass = this.namingRule.toComponentClass(config, path);
        if (actionClass == null) {
            return null;
        }
        return createActionConfig(config, actionClass, path);
    }

    public ActionConfig createActionConfig(ModuleConfig config, Class actionClass) {
        String path = this.namingRule.toActionPathName(actionClass);
        if (path == null) {
            return null;
        }
        return createActionConfig(config, actionClass, path);
    }

    private ActionConfig createActionConfig(ModuleConfig config, Class actionClass, String path) {
        StrutsConfigAnnotationHandler annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
        StrutsActionConfig strutsAction = annHandler.createStrutsActionConfig(actionClass);
        if (!actionClass.getName().matches(this.configRule.getActionClassPattern())) {
            return null;
        }

        ActionConfig actionConfig = this.defaultRule.createActionConfig(config, actionClass, path, strutsAction);
        registerForwardConfigs(config, actionConfig, actionClass);

        return actionConfig;
    }

    private void registerForwardConfigs(ModuleConfig config, ActionConfig actionConfig, Class actionClass) {

        Field[] fields = actionClass.getFields();
        for (int i = 0; i < fields.length; i++) {
            StrutsConfigAnnotationHandler annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
            StrutsActionForwardConfig actionForward = annHandler.createStrutsActionForwardConfig(fields[i]);
            if (actionForward != null) {
                fields[i].setAccessible(true);
                String name = FieldUtil.get(fields[i], actionClass).toString();
                ForwardConfig forwardConfig = this.defaultRule.createActionForwardConfig(config, actionClass, name,
                        actionForward);
                actionConfig.addForwardConfig(forwardConfig);
            }
        }
        this.defaultRule.addForwardConfig(config, actionClass, actionConfig);
    }

}
