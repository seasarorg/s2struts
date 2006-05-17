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

import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.struts.config.NullStrutsActionFormConfig;
import org.seasar.struts.config.StrutsActionFormConfig;
import org.seasar.struts.config.rule.ZeroConfigActionFormRule;
import org.seasar.struts.factory.StrutsConfigAnnotationHandler;
import org.seasar.struts.factory.StrutsConfigAnnotationHandlerFactory;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class ActionFormConfigCreatorImpl implements ActionFormConfigCreator {

    private NamingRule namingRule;
    
    public void setNamingRule(NamingRule namingRule) {
        this.namingRule = namingRule;
    }

    private ZeroConfigActionFormRule defaultRule;

    public void setDefaultRule(ZeroConfigActionFormRule defaultRule) {
        this.defaultRule = defaultRule;
    }

    public FormBeanConfig createFormBeanConfig(ModuleConfig config, String name) {
        Class formClass = this.namingRule.defineClass(name);
        return createFormBeanConfig(config, formClass, name);
    }

    public FormBeanConfig createFormBeanConfig(ModuleConfig config, Class formClass) {
        if (!this.namingRule.isTargetClass(formClass)) {
            return null;
        }
        String name = this.namingRule.defineName(formClass);
        return createFormBeanConfig(config, formClass, name);
    }

    private FormBeanConfig createFormBeanConfig(ModuleConfig config, Class formClass, String name) {
        StrutsConfigAnnotationHandler annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
        StrutsActionFormConfig strutsActionForm = annHandler.createStrutsActionFormConfig(formClass);
        if (strutsActionForm == null) {
            strutsActionForm = new NullStrutsActionFormConfig();
        }

        FormBeanConfig formBeanConfig = new FormBeanConfig();
        formBeanConfig.setName(name);
        formBeanConfig.setType(formClass.getName());
        
        formBeanConfig.setRestricted(getRestricted(strutsActionForm, formClass, config));
        
        return formBeanConfig;
    }

    private boolean getRestricted(StrutsActionFormConfig form, Class formClass, ModuleConfig config) {
        return form.restricted() == StrutsActionFormConfig.DEFAULT_RESTRICTED ?
                defaultRule.getRestricted(formClass, config) : form.restricted();
    }

}
