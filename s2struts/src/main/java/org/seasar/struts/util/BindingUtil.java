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
package org.seasar.struts.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.impl.BeanDescImpl;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.beans.IndexedPropertyDesc;
import org.seasar.struts.beans.impl.IndexedPropertyDescImpl;
import org.seasar.struts.config.ActionPropertyConfig;
import org.seasar.struts.factory.ActionAnnotationHandler;
import org.seasar.struts.factory.ActionAnnotationHandlerFactory;

/**
 * @author Satoshi Kimura
 */
public class BindingUtil {
    private static Map primitiveMap = new HashMap();

    static {
        primitiveMap.put(Character.TYPE, Character.class);
        primitiveMap.put(Short.TYPE, Short.class);
        primitiveMap.put(Integer.TYPE, Integer.class);
        primitiveMap.put(Long.TYPE, Long.class);
        primitiveMap.put(Double.TYPE, Double.class);
        primitiveMap.put(Float.TYPE, Float.class);
        primitiveMap.put(Boolean.TYPE, Boolean.class);
    }

    private BindingUtil() {
    }

    private static Class getPrimitiveWrappedClass(Class primitiveClass) {
        return (Class) primitiveMap.get(primitiveClass);
    }

    private static Object getValue(S2Container container, String name) {
        Object var = RequestUtil.getValue(container.getRequest(), name);
        if (var != null) {
            return var;
        }
        
        if (container.hasComponentDef(name)) {
            return container.getComponent(name);
        }
        return null;
    }

    public static void importProperties(Object action, S2Container container, BeanDesc beanDesc, ActionMapping mapping) {
        importParameter(action, container);
        for (int i = 0; i < beanDesc.getPropertyDescSize(); i++) {
            PropertyDesc propertyDesc = beanDesc.getPropertyDesc(i);
            BindingUtil.importProperty(action, container, propertyDesc, mapping);
        }
    }

    private static void importProperty(Object action, S2Container container, PropertyDesc propertyDesc, ActionMapping mapping) {
        if (!propertyDesc.hasWriteMethod()) {
            return;
        }
        
        String propertyName = propertyDesc.getPropertyName();
        Object value = BindingUtil.getValue(container, propertyName);
        if (BindingUtil.isActionFormProperty(propertyDesc, mapping)) {
            value = ActionFormUtil.getActualForm(container.getRequest(), mapping);
        } else {
            value = BeanValidatorFormUtil.toBean(value);
        }
        if (value == null) {
            return;
        }
        
        Class propertyType = propertyDesc.getPropertyType();
        if (propertyType.isPrimitive()) {
            propertyType = getPrimitiveWrappedClass(propertyType);
        }
        if (propertyType.isInstance(value)) {
            propertyDesc.setValue(action, value);
        }
    }

    private static void importParameter(Object action, S2Container container) {
        Enumeration paramNames = container.getRequest().getParameterNames();
        BeanDesc beanDesc = new BeanDescImpl(action.getClass());
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if (paramName.endsWith("]") == false) {
                continue;
            }
            StringTokenizer tokenizer = new StringTokenizer(paramName, "[]");
            if (tokenizer.countTokens() == 2) {
                String propertyName = tokenizer.nextToken();
                int index = 0;
                try {
                    index = IntegerConversionUtil.toPrimitiveInt(tokenizer.nextToken());
                } catch (NumberFormatException e) {
                    continue;
                }
                String paramValue = container.getRequest().getParameter(paramName);
                IndexedPropertyDesc propertyDesc = new IndexedPropertyDescImpl(propertyName, String.class, beanDesc);
                if (propertyDesc.hasWriteMethod()) {
                    propertyDesc.setValue(action, index, paramValue);
                }
            }
        }
    }

    public static void exportProperties(Object action, S2Container container, BeanDesc beanDesc, ActionMapping mapping) {
        for (int i = 0; i < beanDesc.getPropertyDescSize(); ++i) {
            PropertyDesc propertyDesc = beanDesc.getPropertyDesc(i);
            BindingUtil.exportProperty(action, container, beanDesc, propertyDesc, mapping);
        }
    }

    private static void exportProperty(Object action, S2Container container, BeanDesc beanDesc, PropertyDesc propertyDesc, ActionMapping mapping) {
        if (!propertyDesc.hasReadMethod()) {
            return;
        }
        
        Object value = propertyDesc.getValue(action);
        if (value == null) {
            return;
        }
        
        if (BindingUtil.isActionFormProperty(propertyDesc, mapping)) {
            ActionFormUtil.setActualForm(container.getRequest(), value, mapping);
        } else {
            String propertyName = propertyDesc.getPropertyName();
            if (BeanValidatorFormUtil.isBeanValidatorForm(container.getRequest(), propertyName)) {
                value = BeanValidatorFormUtil.toBeanValidatorForm(container.getRequest(), propertyName, value);
            }
            
            ActionAnnotationHandler annHandler = ActionAnnotationHandlerFactory.getAnnotationHandler();
            ActionPropertyConfig propertyConfig = annHandler.createActionPropertyConfig(beanDesc, propertyDesc);
            if (propertyConfig.isSessionScope()) {
                container.getSession().setAttribute(propertyName, value);
            } else {
                container.getRequest().setAttribute(propertyName, value);
            }
        }
    }

    private static boolean isActionFormProperty(PropertyDesc propertyDesc, ActionMapping mapping) {
        return propertyDesc.getPropertyName().equals(mapping.getAttribute());
    }

}