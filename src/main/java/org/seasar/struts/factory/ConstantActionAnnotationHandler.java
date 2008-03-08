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
package org.seasar.struts.factory;

import java.lang.reflect.Method;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.struts.config.ActionPropertyConfig;
import org.seasar.struts.config.ActionPropertyConfigImpl;
import org.seasar.struts.util.ConstantAnnotationUtil;

/**
 * @author Katsuhiko Nagashima
 */
public class ConstantActionAnnotationHandler implements ActionAnnotationHandler {

    private static final String EXPORT_SUFFIX = "_EXPORT";

    private static final String BINDING_METHOD_SUFFIX = "_BINDING_METHOD";

    public ActionPropertyConfig createActionPropertyConfig(BeanDesc beanDesc,
            PropertyDesc propertyDesc) {
        String fieldName = propertyDesc.getPropertyName() + EXPORT_SUFFIX;
        if (!beanDesc.hasField(fieldName)) {
            return new ActionPropertyConfigImpl();
        }
        if (!ConstantAnnotationUtil.isConstantAnnotationStringField(beanDesc
                .getField(fieldName))) {
            return new ActionPropertyConfigImpl();
        }
        String value = (String) beanDesc.getFieldValue(fieldName, null);
        return new ActionPropertyConfigImpl(value);
    }

    public String getPath(Method method) {
        Class clazz = method.getDeclaringClass();
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(clazz);
        String fieldName = method.getName() + BINDING_METHOD_SUFFIX;
        if (beanDesc.hasField(fieldName)) {
            String value = (String) beanDesc.getFieldValue(fieldName, null);
            return value.replaceFirst("path\\s*=\\s*", "");
        }
        return null;
    }

}
