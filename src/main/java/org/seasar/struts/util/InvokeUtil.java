/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author Satoshi Kimura
 */
public class InvokeUtil {
    
    public static Object invoke(String methodBindingExpression) {
        return invoke(methodBindingExpression, new ActionMapping());
    }
    
    public static Object invoke(String methodBindingExpression, ActionMapping mapping) {
        S2Container container = SingletonS2ContainerFactory.getContainer();

        String componentName = getComponentName(methodBindingExpression);
        String methodName = getMethodName(methodBindingExpression);

        // Throw exception if component do not registered.
        ComponentDef cd = container.getComponentDef(componentName);
        Object component = cd.getComponent();
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(cd.getComponentClass());

        BindingUtil.importProperties(component, container, beanDesc, mapping);
        Object ret = invoke(component, beanDesc, methodName);
        BindingUtil.exportProperties(component, container, beanDesc, mapping);
        return ret;
    }

    private static Object invoke(Object component, BeanDesc beanDesc, String methodName) {
        return beanDesc.invoke(component, methodName, null);
    }

    private static String getComponentName(String methodBindingExpression) {
        if (methodBindingExpression == null) {
            return null;
        }
        int index = methodBindingExpression.indexOf('.');
        if (index > 0) {
            return methodBindingExpression.substring(2, index);
        } else {
            throw new IllegalArgumentException("component was not found. arg: " + methodBindingExpression);
        }
    }

    private static String getMethodName(String methodBindingExpression) {
        if (methodBindingExpression == null) {
            return null;
        }
        int index = methodBindingExpression.indexOf('.');
        if (index > 0) {
            return methodBindingExpression.substring(index + 1, methodBindingExpression.length() - 1);
        } else {
            throw new IllegalArgumentException("method was not found. arg: " + methodBindingExpression);
        }
    }
}
