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

import java.util.Locale;
import java.util.Map;

import org.apache.commons.validator.Constant;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.FormSet;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorResources;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.ModuleUtils;
import org.seasar.framework.log.Logger;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class HotdeployValidatorResources extends ValidatorResources {

    private static final long serialVersionUID = -6030764274075450761L;
    
    private static final Logger log = Logger.getLogger(HotdeployValidatorResources.class);

    private ValidationCreator validationCreator;
    
    public void setValidationCreator(ValidationCreator validationCreator) {
        this.validationCreator = validationCreator;
    }
    
    private ValidatorResources resources;

    public HotdeployValidatorResources(ValidatorResources resources) {
        this.resources = resources;
    }

    public void addConstant(Constant arg0) {
        resources.addConstant(arg0);
    }

    public void addConstant(String arg0, String arg1) {
        resources.addConstant(arg0, arg1);
    }

    public void addConstantParam(String arg0, String arg1) {
        resources.addConstantParam(arg0, arg1);
    }

    public void addFormSet(FormSet arg0) {
        resources.addFormSet(arg0);
    }

    public void addValidatorAction(ValidatorAction arg0) {
        resources.addValidatorAction(arg0);
    }

    public boolean equals(Object arg0) {
        return resources.equals(arg0);
    }

    public Form get(Locale arg0, Object arg1) {
        return resources.get(arg0, arg1);
    }

    public Form get(String arg0, String arg1, String arg2, Object arg3) {
        return resources.get(arg0, arg1, arg2, arg3);
    }

    public Form getForm(Locale locale, String formKey) {
        return this.getForm(locale.getLanguage(), locale.getCountry(), locale
                .getVariant(), formKey);
    }

    public Form getForm(String language, String country, String variant, String formKey) {
        ModuleConfig config = ModuleUtils.getInstance().getModuleConfig(S2StrutsContextUtil.getRequest());
        Form form = this.validationCreator.createForm(config, formKey);
        if (form == null) {
            return resources.getForm(language, country, variant, formKey);
        }
        if (log.isDebugEnabled()) {
            log.debug("auto create " + form);
        }
        
        // initialize form...
        FormSet formSet = new FormSet();
        formSet.addForm(form);
        resources.addFormSet(formSet);
        resources.process();
        // ...initialized form
        
        return form;
    }

    public ValidatorAction getValidatorAction(String arg0) {
        return resources.getValidatorAction(arg0);
    }

    public Map getValidatorActions() {
        return resources.getValidatorActions();
    }

    public int hashCode() {
        return resources.hashCode();
    }

    public void process() {
        resources.process();
    }

    public void processForms() {
        resources.processForms();
    }

    public void put(FormSet arg0) {
        resources.put(arg0);
    }

    public String toString() {
        return resources.toString();
    }

}
