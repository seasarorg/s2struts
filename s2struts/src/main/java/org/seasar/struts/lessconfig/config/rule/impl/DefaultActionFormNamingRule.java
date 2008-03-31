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

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.ClassUtil;
import org.seasar.struts.lessconfig.config.StrutsActionFormConfig;
import org.seasar.struts.lessconfig.config.rule.ActionFormNamingRule;
import org.seasar.struts.lessconfig.config.rule.CommonNamingRule;
import org.seasar.struts.lessconfig.factory.StrutsConfigAnnotationHandler;
import org.seasar.struts.lessconfig.factory.StrutsConfigAnnotationHandlerFactory;

/**
 * {@link ActionFormNamingRule}の実装クラスです。
 * 
 * @author Katsuhiko Nagashima
 */
public class DefaultActionFormNamingRule implements ActionFormNamingRule {

    protected static final String DTO_SUFFIX = "Dto";

    protected static final String FORM_SUFFIX = "Form";

    private S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }

    public Class toComponentClass(String name) {
        S2Container container = getContainer();

        if (container.hasComponentDef(name)) {
            return container.getComponentDef(name).getComponentClass();
        }

        Class formClass = null;
        if (name.endsWith(DTO_SUFFIX)) {
            String componentName = name.substring(0, name.length() - DTO_SUFFIX.length()) + FORM_SUFFIX;
            if (container.hasComponentDef(componentName)) {
                formClass = container.getComponentDef(componentName).getComponentClass();
            }
        }
        if (name.endsWith(FORM_SUFFIX)) {
            String componentName = name.substring(0, name.length() - FORM_SUFFIX.length()) + DTO_SUFFIX;
            if (container.hasComponentDef(componentName)) {
                formClass = container.getComponentDef(componentName).getComponentClass();
            }
        }
        if (formClass != null) {
            StrutsConfigAnnotationHandler annHandler = StrutsConfigAnnotationHandlerFactory.getAnnotationHandler();
            StrutsActionFormConfig strutsActionForm = annHandler.createStrutsActionFormConfig(formClass);
            if (strutsActionForm != null && name.equals(strutsActionForm.name())) {
                return formClass;
            }
        }

        return null;
    }

    public String toActionFormName(Class formClass) {
        S2Container container = getContainer();
        if (!container.hasComponentDef(formClass)) {
            String name = ClassUtil.getShortClassName(formClass);
            return CommonNamingRule.decapitalizeName(name);
        }

        return container.getComponentDef(formClass).getComponentName();
    }

}
