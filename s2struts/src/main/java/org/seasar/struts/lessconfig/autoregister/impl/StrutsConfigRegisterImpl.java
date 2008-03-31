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

import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

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
import org.seasar.framework.util.ClassUtil;
import org.seasar.struts.lessconfig.autoregister.ActionConfigCreator;
import org.seasar.struts.lessconfig.autoregister.ActionFormConfigCreator;
import org.seasar.struts.lessconfig.autoregister.ClassComparator;
import org.seasar.struts.lessconfig.autoregister.StrutsConfigRegister;
import org.seasar.struts.lessconfig.autoregister.ValidationCreator;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class StrutsConfigRegisterImpl implements StrutsConfigRegister {

    private static final Logger log = Logger.getLogger(StrutsConfigRegisterImpl.class);

    private ServletContext servletContext;

    /**
     * {@link ServletContext}を設定します。
     * 
     * @param servletContext
     */
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private ActionConfigCreator actionConfigCreator;

    /**
     * {@link ActionConfigCreator}を設定します。
     * 
     * @param actionConfigCreator
     */
    public void setActionConfigCreator(ActionConfigCreator actionConfigCreator) {
        this.actionConfigCreator = actionConfigCreator;
    }

    private ActionFormConfigCreator formConfigCreator;

    /**
     * {@link ActionFormConfigCreator}を設定します。
     * 
     * @param formConfigCreator
     */
    public void setFormConfigCreator(ActionFormConfigCreator formConfigCreator) {
        this.formConfigCreator = formConfigCreator;
    }

    private ValidationCreator validationCreator;

    /**
     * {@link ValidationCreator}を設定します。
     * 
     * @param validationCreator
     */
    public void setValidationConfigCreator(ValidationCreator validationCreator) {
        this.validationCreator = validationCreator;
    }

    public void register(ModuleConfig config, Collection classes) {
        Collection sorted = ClassComparator.sort(classes);

        registerActionForms(config, sorted);
        registerActions(config, sorted);
        registerValidations(config);
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
            if (!registeredActionForm(config, formConfig)) {
                config.addFormBeanConfig(formConfig);

                if (log.isDebugEnabled()) {
                    log.debug("auto register " + formConfig);
                }
            }
        }
    }

    private boolean registeredActionForm(ModuleConfig config, FormBeanConfig formConfig) {
        return (config.findFormBeanConfig(formConfig.getName()) != null);
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
            if (!registeredActionConfig(config, actionConfig)) {
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

    /**
     * {@link ActionConfig}を登録します。
     * 
     * @param config
     * @param actionConfig
     * @return
     */
    public static boolean registeredActionConfig(ModuleConfig config, ActionConfig actionConfig) {
        ActionConfig[] actionConfigs = config.findActionConfigs();
        for (int i = 0; i < actionConfigs.length; ++i) {
            if (actionConfig.getPath().equals(actionConfigs[i].getPath())) {
                return true;
            }
        }
        return false;
    }

    private void registerValidations(ModuleConfig config) {
        // Create Forms.
        ValidatorResources currentResources = getValidatorResources(config);
        FormSet formSet = new FormSet();

        FormBeanConfig[] formConfigs = config.findFormBeanConfigs();
        for (int i = 0; i < formConfigs.length; i++) {
            if (!registeredValidation(currentResources, formConfigs[i])) {
                Class clazz = ClassUtil.forName(formConfigs[i].getType());
                String name = formConfigs[i].getName();
                Form form = this.validationCreator.createForm(config, clazz, name);
                if (form != null) {
                    formSet.addForm(form);

                    if (log.isDebugEnabled()) {
                        log.debug("auto register " + form);
                    }
                }
            }
        }

        // Initialize ValidatorResources
        if (formSet.getForms().size() != 0) {
            ValidatorResources resources = new ValidatorResources();
            resources.addFormSet(formSet);
            resources.process();
            addValidatorResources(config, resources);
        }
    }

    private boolean registeredValidation(ValidatorResources resources, FormBeanConfig formConfig) {
        return (resources.getForm(Locale.getDefault(), formConfig.getName()) != null);
    }

    private void addValidatorResources(ModuleConfig config, ValidatorResources resources) {
        CompositeValidatorResources compositeResources = new CompositeValidatorResources();
        ValidatorResources current = getValidatorResources(config);

        compositeResources.addResources(current);
        compositeResources.addResources(resources);

        setValidatorResources(config, compositeResources);
    }

    private ValidatorResources getValidatorResources(ModuleConfig config) {
        return (ValidatorResources) this.servletContext
                .getAttribute(ValidatorPlugIn.VALIDATOR_KEY + config.getPrefix());
    }

    private void setValidatorResources(ModuleConfig config, ValidatorResources resources) {
        this.servletContext.setAttribute(ValidatorPlugIn.VALIDATOR_KEY + config.getPrefix(), resources);
    }

}
