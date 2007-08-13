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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ActionConfig;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.impl.BeanDescImpl;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.exception.ClassNotFoundRuntimeException;
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
        Object var = RequestUtil.getValue(getRequest(container), name);
        if (var != null) {
            return var;
        }

        if (container.hasComponentDef(name)) {
            return container.getComponent(name);
        }
        return null;
    }

    public static void importProperties(Object action, S2Container container,
            BeanDesc beanDesc, ActionMapping mapping) {
        importParameter(action, container);
        for (int i = 0; i < beanDesc.getPropertyDescSize(); i++) {
            PropertyDesc propertyDesc = beanDesc.getPropertyDesc(i);
            BindingUtil
                    .importProperty(action, container, propertyDesc, mapping);
        }
    }

    private static void importProperty(Object action, S2Container container,
            PropertyDesc propertyDesc, ActionMapping mapping) {
        if (!propertyDesc.hasWriteMethod()) {
            return;
        }

        String propertyName = BindingUtil.getComponentPropertyName(container,
                propertyDesc);
        Object value = BindingUtil.getValue(container, propertyName);
        if (BindingUtil.isActionFormProperty(propertyName, mapping)) {
            value = ActionFormUtil
                    .getActualForm(getRequest(container), mapping);
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
        } else {
            Class destClass = propertyDesc.getPropertyType();
            try {
                value = DxoUtil.convert(value, destClass);
                propertyDesc.setValue(action, value);
            } catch (ClassNotFoundRuntimeException ignore) {
            } catch (ComponentNotFoundRuntimeException ignore) {
            } catch (NullPointerException ignore) {
            }
        }
    }

    private static void importParameter(Object action, S2Container container) {
        Enumeration paramNames = getRequest(container).getParameterNames();
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
                    index = IntegerConversionUtil.toPrimitiveInt(tokenizer
                            .nextToken());
                } catch (NumberFormatException e) {
                    continue;
                }
                String paramValue = getRequest(container).getParameter(
                        paramName);
                IndexedPropertyDesc propertyDesc = new IndexedPropertyDescImpl(
                        propertyName, String.class, beanDesc);
                if (propertyDesc.hasWriteMethod()) {
                    propertyDesc.setValue(action, index, paramValue);
                }
            }
        }
    }

    public static void exportProperties(Object action, S2Container container,
            BeanDesc beanDesc, ActionMapping mapping) {
        for (int i = 0; i < beanDesc.getPropertyDescSize(); ++i) {
            PropertyDesc propertyDesc = beanDesc.getPropertyDesc(i);
            BindingUtil.exportProperty(action, container, beanDesc,
                    propertyDesc, mapping);
        }
    }

    private static void exportProperty(Object action, S2Container container,
            BeanDesc beanDesc, PropertyDesc propertyDesc, ActionMapping mapping) {
        if (!propertyDesc.hasReadMethod()) {
            return;
        }

        Object value = propertyDesc.getValue(action);
        if (value == null) {
            return;
        }

        HttpServletRequest request = getRequest(container);
        ActionAnnotationHandler annHandler = ActionAnnotationHandlerFactory
                .getAnnotationHandler();
        ActionPropertyConfig propertyConfig = annHandler
                .createActionPropertyConfig(beanDesc, propertyDesc);
        String propertyName = BindingUtil.getComponentPropertyName(container,
                propertyDesc);

        ActionMapping propertyMapping = BindingUtil.getPropertyActionMapping(
                propertyName, mapping);
        if (propertyMapping != null) {
            if (propertyConfig.isUndefinedScope()) {
                ActionFormUtil.setActualForm(request, value, propertyMapping);
            } else if (propertyConfig.isSessionScope()) {
                ActionFormUtil.setSessionActualForm(request, value,
                        propertyMapping);
            } else {
                ActionFormUtil.setRequestActualForm(request, value,
                        propertyMapping);
            }
            return;
        }

        if (propertyConfig.isSessionScope()) {
            request.getSession().setAttribute(propertyName, value);
        } else {
            request.setAttribute(propertyName, value);
        }

    }

    private static ActionMapping getPropertyActionMapping(String propertyName,
            ActionMapping mapping) {
        if (BindingUtil.isActionFormProperty(propertyName, mapping)) {
            return mapping;
        }

        //
        // FormBean�ɑ΂��ĕ�����ActionMapping���o�^����Ă���ꍇ
        // ���ׂĂ�Scope����v���Ă����ꍇ�́A�ŏ���1��ActionMapping��ԋp��
        // Export���邽�߂̏��Ƃ��ė��p����
        // Scope���قȂ�ꍇ�́AScope�̐ݒ�D�揇�ʂ� request > session �Ƃ��Ă��邽��
        // request�X�R�[�v��ActionMapping��D�悵�ĕԋp��
        // Export���邽�߂̏��Ƃ��ė��p����
        //
        ActionMapping result = null;
        ActionConfig[] configs = ModuleConfigUtil
                .findActionConfigsForFormBeanName(propertyName);
        if (configs.length == 0) {
            return null;
        }

        result = (ActionMapping) configs[0];
        for (int i = 1; i < configs.length; i++) {
            if (!(result.getScope().equals(configs[i].getScope()))) {
                if (result.getScope().equals("request")) {
                    return result;
                } else {
                    return (ActionMapping) configs[i];
                }
            }
        }

        return result;
    }

    private static boolean isActionFormProperty(String propertyName,
            ActionMapping mapping) {
        return propertyName.equals(mapping.getAttribute());
    }

    private static String getComponentPropertyName(S2Container container,
            PropertyDesc propertyDesc) {
        String propertyName = propertyDesc.getPropertyName();
        Class propertyClass = propertyDesc.getPropertyType();

        if (!container.hasComponentDef(propertyClass)) {
            return propertyName;
        }

        ComponentDef propertyDef = container.getComponentDef(propertyClass);
        String componentName = propertyDef.getComponentName();
        if (componentName == null) {
            return propertyName;
        }

        if (componentName.endsWith("_" + propertyName)) {
            return componentName;
        }
        return propertyName;
    }

    private static HttpServletRequest getRequest(S2Container container) {
        return S2Util.getRequest(container);
    }

}