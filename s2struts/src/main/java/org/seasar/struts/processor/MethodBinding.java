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
package org.seasar.struts.processor;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.util.BindingUtil;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class MethodBinding {
    
    private String componentName;
    
    private String methodName;
    
    private int index;
    
    private boolean indexed;
    
    public MethodBinding(String expression) {
        this.componentName = getComponentName(expression);
        this.methodName = getMethodName(expression);
        this.indexed = false;
    }
    
    public MethodBinding(String expression, int index) {
        this.componentName = getComponentName(expression);
        this.methodName = getMethodName(expression);
        this.index = index;
        this.indexed = true;
    }
    
    public Object invoke() {
        return invoke(new ActionMapping());
    }
    
    public Object invoke(ActionMapping mapping) {
        S2Container container = SingletonS2ContainerFactory.getContainer();

        // Throw exception if component do not registered.
        ComponentDef cd = container.getComponentDef(this.componentName);
        Object component = cd.getComponent();
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(cd.getComponentClass());

        BindingUtil.importProperties(component, container, beanDesc, mapping);
        Object ret = invoke(component, beanDesc);
        BindingUtil.exportProperties(component, container, beanDesc, mapping);
        return ret;
    }

    private Object invoke(Object component, BeanDesc beanDesc) {
        if (indexed) {
            return beanDesc.invoke(component, this.methodName, new Object[] { new Integer(index) });
        } else {
            return beanDesc.invoke(component, this.methodName, null);
        }
    }

    private String getComponentName(String expression) {
        if (expression == null) {
            return null;
        }
        int index = expression.indexOf('.');
        if (index > 0) {
            return expression.substring(2, index);
        } else {
            throw new IllegalArgumentException("component was not found. arg: " + expression);
        }
    }

    private String getMethodName(String expression) {
        if (expression == null) {
            return null;
        }
        int index = expression.indexOf('.');
        if (index > 0) {
            return expression.substring(index + 1, expression.length() - 1);
        } else {
            throw new IllegalArgumentException("method was not found. arg: " + expression);
        }
    }

}
