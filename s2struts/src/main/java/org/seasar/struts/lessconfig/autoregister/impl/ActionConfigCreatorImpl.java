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

import javax.servlet.ServletContext;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.util.FieldUtil;
import org.seasar.struts.lessconfig.autoregister.ActionConfigCreator;
import org.seasar.struts.lessconfig.config.AutoStrutsConfigRule;
import org.seasar.struts.lessconfig.config.NullStrutsActionConfig;
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

    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

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
        if (strutsAction == null) {
            if (actionClass.getName().matches(this.configRule.getActionClassPattern())) {
                strutsAction = new NullStrutsActionConfig();
            } else {
                return null;
            }
        }

        ActionConfig actionConfig = new ActionMapping();
        if (StrutsActionConfig.DEFAULT_PATH.equals(strutsAction.path())) {
            actionConfig.setPath(path);
        } else {
            actionConfig.setPath(strutsAction.path());
        }
        actionConfig.setType(actionClass.getName());
        actionConfig.setModuleConfig(config);

        actionConfig.setName(getName(strutsAction, actionClass, config));
        actionConfig.setAttribute(getAttribute(strutsAction, actionClass, config));
        actionConfig.setForward(getForward(strutsAction, actionClass, config));
        actionConfig.setInclude(getInclude(strutsAction, actionClass, config));
        actionConfig.setInput(getInput(strutsAction, actionClass, config));
        actionConfig.setParameter(getParameter(strutsAction, actionClass, config));
        actionConfig.setPrefix(getPrefix(strutsAction, actionClass, config));
        actionConfig.setRoles(getRoles(strutsAction, actionClass, config));
        actionConfig.setScope(getScope(strutsAction, actionClass, config));
        actionConfig.setSuffix(getSuffix(strutsAction, actionClass, config));
        actionConfig.setUnknown(getUnknown(strutsAction, actionClass, config));
        actionConfig.setValidate(getValidate(strutsAction, actionClass, config));
        actionConfig.setCancellable(getCancellable(strutsAction, actionClass, config));

        registerForwardConfigs(actionConfig, actionClass);

        return actionConfig;
    }

    private void registerForwardConfigs(ActionConfig actionConfig, Class actionClass) {

        Field[] fields = actionClass.getFields();
        for (int i = 0; i < fields.length; i++) {
            StrutsConfigAnnotationHandler annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
            StrutsActionForwardConfig actionForward = annHandler.createStrutsActionForwardConfig(fields[i]);
            if (actionForward != null) {
                fields[i].setAccessible(true);
                String name = FieldUtil.get(fields[i], actionClass).toString();
                ForwardConfig forwardConfig = new ActionForward();
                forwardConfig.setName(name);
                forwardConfig.setPath(actionForward.path());
                forwardConfig.setRedirect(actionForward.redirect());
                actionConfig.addForwardConfig(forwardConfig);
            }
        }
        this.defaultRule.addForwardConfig(actionClass, actionConfig, this.servletContext);
    }

    private String getAttribute(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_ATTRIBUTE.equals(action.attribute()) ? this.defaultRule.getAttribute(
                actionClass, config) : action.attribute();
    }

    private String getForward(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_FORWARD.equals(action.forward()) ? this.defaultRule.getForward(actionClass,
                config) : action.forward();
    }

    private String getInclude(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_INCLUDE.equals(action.include()) ? this.defaultRule.getInclude(actionClass,
                config) : action.include();
    }

    private String getInput(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_INPUT.equals(action.input()) ? this.defaultRule.getInput(actionClass, config)
                : action.input();
    }

    private String getName(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_NAME.equals(action.name()) ? this.defaultRule.getName(actionClass, config)
                : action.name();
    }

    private String getParameter(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_PARAMETER.equals(action.parameter()) ? this.defaultRule.getParameter(
                actionClass, config) : action.parameter();
    }

    private String getPrefix(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_PREFIX.equals(action.prefix()) ? this.defaultRule.getPrefix(actionClass,
                config) : action.prefix();
    }

    private String getRoles(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_ROLES.equals(action.roles()) ? this.defaultRule.getRoles(actionClass, config)
                : action.roles();
    }

    private String getScope(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_SCOPE.equals(action.scope()) ? this.defaultRule.getScope(actionClass, config)
                : action.scope();
    }

    private String getSuffix(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return StrutsActionConfig.DEFAULT_SUFFIX.equals(action.suffix()) ? this.defaultRule.getSuffix(actionClass,
                config) : action.suffix();
    }

    private boolean getUnknown(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return action.unknown() == StrutsActionConfig.DEFAULT_UNKNOWN ? this.defaultRule
                .getUnknown(actionClass, config) : action.unknown();
    }

    private boolean getValidate(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return action.validate() == StrutsActionConfig.DEFAULT_VALIDATE ? this.defaultRule.getValidate(actionClass,
                config) : action.validate();
    }

    private boolean getCancellable(StrutsActionConfig action, Class actionClass, ModuleConfig config) {
        return action.cancellable() == StrutsActionConfig.DEFAULT_CANCELLABLE ? this.defaultRule.getCancellable(
                actionClass, config) : action.cancellable();
    }

}
