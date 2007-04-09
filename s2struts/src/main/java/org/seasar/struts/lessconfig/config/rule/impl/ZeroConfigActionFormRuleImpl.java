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
package org.seasar.struts.lessconfig.config.rule.impl;

import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.util.ClassUtil;
import org.seasar.struts.lessconfig.config.StrutsActionFormConfig;
import org.seasar.struts.lessconfig.config.rule.ZeroConfigActionFormRule;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class ZeroConfigActionFormRuleImpl implements ZeroConfigActionFormRule {

    public FormBeanConfig createFormBeanConfig(ModuleConfig config, Class formClass, String name,
            StrutsActionFormConfig strutsActionForm) {

        FormBeanConfig formBeanConfig = (FormBeanConfig) ClassUtil.newInstance(config.getActionFormBeanClass());
        formBeanConfig.setType(formClass.getName());
        if (!StrutsActionFormConfig.DEFAULT_NAME.equals(strutsActionForm.name())) {
            formBeanConfig.setName(strutsActionForm.name());
        } else {
            formBeanConfig.setName(name);
        }

        if (StrutsActionFormConfig.DEFAULT_RESTRICTED != strutsActionForm.restricted()) {
            formBeanConfig.setRestricted(strutsActionForm.restricted().booleanValue());
        }

        if (!StrutsActionFormConfig.DEFAULT_INHERIT.equals(strutsActionForm.inherit())) {
            formBeanConfig.setExtends(strutsActionForm.inherit());
        }

        return formBeanConfig;
    }

}
