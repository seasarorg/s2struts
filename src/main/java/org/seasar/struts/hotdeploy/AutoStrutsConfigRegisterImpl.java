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

import java.util.Collection;
import java.util.Iterator;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.log.Logger;
import org.seasar.struts.util.ClassFinder;
import org.seasar.struts.util.ClassFinderImpl;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class AutoStrutsConfigRegisterImpl implements AutoStrutsConfigRegister {

    private static final Logger log = Logger.getLogger(AutoStrutsConfigRegisterImpl.class);

    private ClassFinder classFinder = new ClassFinderImpl();

    private AutoStrutsConfigRegisterRule retisterRule;

    public AutoStrutsConfigRegisterRule getRetisterRule() {
        return retisterRule;
    }

    public void setRetisterRule(AutoStrutsConfigRegisterRule retisterRule) {
        this.retisterRule = retisterRule;
    }

    private ActionConfigCreator actionConfigCreator;

    public ActionConfigCreator getActionConfigCreator() {
        return actionConfigCreator;
    }

    public void setActionConfigCreator(ActionConfigCreator actionConfigCreator) {
        this.actionConfigCreator = actionConfigCreator;
    }
    
    private ActionFormConfigCreator formConfigCreator;

    public ActionFormConfigCreator getFormConfigCreator() {
        return formConfigCreator;
    }

    public void setFormConfigCreator(ActionFormConfigCreator formConfigCreator) {
        this.formConfigCreator = formConfigCreator;
    }

    public void register(ActionServlet actionServlet, ModuleConfig config) {
        try {
            this.classFinder.find(getRetisterRule().isEnableJar());
            if (actionServlet != null) {
                this.classFinder.find(actionServlet, getRetisterRule().isEnableJar());
            }

            register(config);
        } finally {
            this.classFinder.destroy();
        }
    }

    private void register(ModuleConfig config) {
        Collection classes = this.classFinder.getClassCollection();
        registerActionForms(config, classes);
        registerActions(config, classes);
    }

    private void registerActionForms(ModuleConfig config, Collection classes) {
        for (Iterator iterator = classes.iterator(); iterator.hasNext();) {
            Class clazz = (Class) iterator.next();
            registerActionForm(config, clazz);
        }
    }
    
    private void registerActionForm(ModuleConfig config, Class clazz) {
        FormBeanConfig formConfig = getFormConfigCreator().createFormBeanConfig(config, clazz);
        if (formConfig != null) {
            config.addFormBeanConfig(formConfig);

            if (log.isDebugEnabled()) {
                log.debug("auto register " + formConfig);
            }
        }
    }

    private void registerActions(ModuleConfig config, Collection classes) {
        for (Iterator iterator = classes.iterator(); iterator.hasNext();) {
            Class clazz = (Class) iterator.next();
            registerAction(config, clazz);
        }
    }
    
    private void registerAction(ModuleConfig config, Class clazz) {
        ActionConfig actionConfig = getActionConfigCreator().createActionConfig(config, clazz);
        if (actionConfig != null) {
            config.addActionConfig(actionConfig);

            if (log.isDebugEnabled()) {
                log.debug("auto register " + actionConfig);
                ForwardConfig[] forwardConfigs = actionConfig.findForwardConfigs();
                for (int i = 0; i < forwardConfigs.length; i++) {
                    log.debug("auto register " + forwardConfigs[i]);
                }
            }
        }
    }
    
}
