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

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletContext;

import org.apache.commons.validator.Form;
import org.apache.commons.validator.FormSet;
import org.apache.commons.validator.ValidatorResources;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.validator.ValidatorPlugIn;
import org.seasar.framework.log.Logger;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class StrutsConfigRegisterImpl implements StrutsConfigRegister {

    private static final Logger log = Logger.getLogger(StrutsConfigRegisterImpl.class);
    
    private ServletContext servletContext;
    
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    
    private ActionConfigCreator actionConfigCreator;

    public void setActionConfigCreator(ActionConfigCreator actionConfigCreator) {
        this.actionConfigCreator = actionConfigCreator;
    }
    
    private ActionFormConfigCreator formConfigCreator;

    public void setFormConfigCreator(ActionFormConfigCreator formConfigCreator) {
        this.formConfigCreator = formConfigCreator;
    }
    
    private ValidationCreator validationCreator;
    
    public void setValidationConfigCreator(ValidationCreator validationCreator) {
        this.validationCreator = validationCreator;
    }

    public void register(ModuleConfig config, Collection classes) {
        registerActionForms(config, classes);
        registerActions(config, classes);
        registerValidations(config, classes);
    }
    
    private void registerActionForms(ModuleConfig config, Collection classes) {
        for (Iterator iterator = classes.iterator(); iterator.hasNext();) {
            Class clazz = (Class) iterator.next();
            registerActionForm(config, clazz);
        }
    }
    
    private void registerActionForm(ModuleConfig config, Class clazz) {
        FormBeanConfig formConfig = this.formConfigCreator.createFormBeanConfig(config, clazz);
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
        ActionConfig actionConfig = this.actionConfigCreator.createActionConfig(config, clazz);
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

    private void registerValidations(ModuleConfig config, Collection classes) {
        // Create Forms.
        FormSet formSet = new FormSet();
        for (Iterator iterator = classes.iterator(); iterator.hasNext();) {
            Class clazz = (Class) iterator.next();
            Form form = this.validationCreator.createForm(config, clazz);
            if (form != null) {
                formSet.addForm(form);
                
                if (log.isDebugEnabled()) {
                    log.debug("auto register " + form);
                }
            }
        }

        // Initialize ValidatorResources
        ValidatorResources resources = getValidatorResources(config);
        if (formSet.getForms().size() != 0) {
            resources.addFormSet(formSet);
            resources.process();
        }
    }
    
    private ValidatorResources getValidatorResources(ModuleConfig config) {
        return (ValidatorResources) this.servletContext.getAttribute(ValidatorPlugIn.VALIDATOR_KEY + config.getPrefix());
    }
    
}
