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
package org.seasar.struts.zeroconfig.autoregister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.validator.Form;
import org.apache.commons.validator.FormSet;
import org.apache.commons.validator.ValidatorResources;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.log.Logger;
import org.seasar.struts.zeroconfig.factory.ValidatorAnnotationHandler;
import org.seasar.struts.zeroconfig.factory.ValidatorAnnotationHandlerFactory;

/**
 * @author Katsuhiko Nagashima
 */
public class AutoValidationRegister {

    private static final Logger log = Logger.getLogger(AutoValidationRegister.class);

    private AutoValidationRegister() {
    }

    public static void register(ValidatorResources resources, ModuleConfig config, Collection classes) {
        FormSet formSet = new FormSet();
        for (Iterator iterator = classes.iterator(); iterator.hasNext();) {
            Class clazz = (Class) iterator.next();
            registerValidation(resources, config, formSet, clazz);
        }
        if (formSet.getForms().size() != 0) {
            resources.addFormSet(formSet);
            resources.process();
        }
    }

    private static void registerValidation(ValidatorResources resources, ModuleConfig config, FormSet formSet, Class formClass) {
        String[] formNames = getFormNames(formClass, config);
        for (int i = 0; i < formNames.length; i++) {
            registerForm(resources, formSet, formClass, formNames[i]);
        }
    }
    
    private static String[] getFormNames(Class formClass, ModuleConfig config) {
        List list = new ArrayList();
        FormBeanConfig[] formBeanConfigs = config.findFormBeanConfigs();
        for (int i = 0; i < formBeanConfigs.length; i++) {
            if (formClass.getName().equals(formBeanConfigs[i].getType())) {
                list.add(formBeanConfigs[i].getName());
            }
        }
        
        return (String[]) list.toArray(new String[list.size()]);
    }

    private static void registerForm(ValidatorResources resources, FormSet formSet, Class formClass, String formName) {
        if (registeredValidation(resources, formName)) {
            return;
        }
        if (registeredValidation(formSet, formName)) {
            return;
        }

        ValidatorAnnotationHandler annHandler = ValidatorAnnotationHandlerFactory.getAnnotationHandler();
        Form form = annHandler.createForm(formName, formClass);
        if (form != null && form.getFields().size() != 0) {
            formSet.addForm(form);

            log.debug("auto register " + form);
        }
    }

    private static boolean registeredValidation(ValidatorResources resources, String formName) {
        return resources.getForm(Locale.getDefault(), formName) != null;
    }

    private static boolean registeredValidation(FormSet formSet, String formName) {
        return formSet.getForm(formName) != null;
    }

}