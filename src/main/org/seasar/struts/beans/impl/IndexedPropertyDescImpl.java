package org.seasar.struts.beans.impl;

import java.lang.reflect.Method;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.IllegalPropertyRuntimeException;
import org.seasar.framework.beans.MethodNotFoundRuntimeException;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.impl.PropertyDescImpl;
import org.seasar.framework.util.MethodUtil;
import org.seasar.struts.beans.IndexedPropertyDesc;

/**
 * @author Satoshi Kimura
 */
public class IndexedPropertyDescImpl implements IndexedPropertyDesc {
    private String propertyName;
    private Class propertyType;
    private BeanDesc beanDesc;
    private Method writeMethod;
    private Method readMethod;
    private PropertyDesc propertyDesc;

    /**
     * 
     */
    public IndexedPropertyDescImpl(String propertyName, Class propertyType, BeanDesc beanDesc) {
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.beanDesc = beanDesc;

        setupMethods();

        this.propertyDesc = new PropertyDescImpl(propertyName, propertyType, this.readMethod, this.writeMethod, beanDesc);
    }
    private void setupMethods() {
        String methodName = this.propertyName.substring(0, 1).toUpperCase() + this.propertyName.substring(1);
        Method[] getMethods = new Method[0];
        try {
            getMethods = this.beanDesc.getMethods("get" + methodName);
        } catch (MethodNotFoundRuntimeException e) {
            // ignore
        }
        Method[] setMethods = new Method[0];
        try {
            setMethods = this.beanDesc.getMethods("set" + methodName);
        } catch (MethodNotFoundRuntimeException e) {
            // ignore
        }

        for (int i = 0; i < getMethods.length; i++) {
            Class[] parameterTypes = getMethods[i].getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(Integer.TYPE)) {
                this.readMethod = getMethods[i];
            }
        }
        for (int i = 0; i < setMethods.length; i++) {
            Class[] parameterTypes = setMethods[i].getParameterTypes();
            if (parameterTypes.length == 2 && parameterTypes[0].equals(Integer.TYPE)
                    && parameterTypes[1].equals(this.propertyType)) {
                this.writeMethod = setMethods[i];
            }
        }
    }
    public void setValue(Object target, int index, Object value) {
        try {
            MethodUtil.invoke(this.writeMethod, target, new Object[]{new Integer(index), convertIfNeed(value)});
        } catch (Throwable t) {
            throw new IllegalPropertyRuntimeException(this.beanDesc.getBeanClass(), this.propertyName, t);
        }
    }

    public Object getValue(Object target, int index) {
        return MethodUtil.invoke(this.readMethod, target, new Object[]{new Integer(index)});
    }

    public boolean hasReadMethod() {
        return this.readMethod != null;
    }
    public boolean hasWriteMethod() {
        return this.writeMethod != null;
    }

    private Object convertIfNeed(Object arg) {
        return this.propertyDesc.convertIfNeed(arg);

    }
}