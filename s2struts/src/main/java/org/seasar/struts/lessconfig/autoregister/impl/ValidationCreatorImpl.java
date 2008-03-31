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

import org.apache.commons.validator.Form;
import org.apache.struts.config.ModuleConfig;
import org.seasar.struts.lessconfig.autoregister.ValidationCreator;
import org.seasar.struts.lessconfig.config.rule.ActionFormNamingRule;
import org.seasar.struts.lessconfig.factory.ValidatorAnnotationHandler;
import org.seasar.struts.lessconfig.factory.ValidatorAnnotationHandlerFactory;

/**
 * {@link ValidationCreator}の実装クラスです。
 * 
 * @author Katsuhiko Nagashima
 */
public class ValidationCreatorImpl implements ValidationCreator {

    private ActionFormNamingRule namingRule;

    /**
     * {@link ActionFormNamingRule}を設定します。
     * 
     * @param namingRule
     */
    public void setNamingRule(ActionFormNamingRule namingRule) {
        this.namingRule = namingRule;
    }

    public Form createForm(ModuleConfig config, String name) {
        Class formClass = this.namingRule.toComponentClass(name);
        if (formClass == null) {
            return null;
        }
        return createForm(config, formClass, name);
    }

    public Form createForm(ModuleConfig config, Class formClass, String name) {
        if (config.findFormBeanConfig(name) == null) {
            return null;
        }

        ValidatorAnnotationHandler annHandler = ValidatorAnnotationHandlerFactory.getAnnotationHandler();
        return annHandler.createForm(name, formClass);
    }

}
