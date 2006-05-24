package org.seasar.struts.pojo;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.util.BindingUtil;

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
