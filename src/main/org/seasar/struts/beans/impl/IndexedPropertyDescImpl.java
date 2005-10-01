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

        this.propertyDesc = new PropertyDescImpl(propertyName, propertyType, readMethod, writeMethod, beanDesc);
    }
    private void setupMethods() {
        String methodName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        Method[] getMethods = new Method[0];
        try {
            getMethods = beanDesc.getMethods("get" + methodName);
        } catch (MethodNotFoundRuntimeException e) {
            // ignore
        }
        Method[] setMethods = new Method[0];
        try {
            setMethods = beanDesc.getMethods("set" + methodName);
        } catch (MethodNotFoundRuntimeException e) {
            // ignore
        }

        for (int i = 0; i < getMethods.length; i++) {
            Class[] parameterTypes = getMethods[i].getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(Integer.TYPE)) {
                readMethod = getMethods[i];
            }
        }
        for (int i = 0; i < setMethods.length; i++) {
            Class[] parameterTypes = setMethods[i].getParameterTypes();
            if (parameterTypes.length == 2 && parameterTypes[0].equals(Integer.TYPE)
                    && parameterTypes[1].equals(propertyType)) {
                writeMethod = setMethods[i];
            }
        }
    }
    public void setValue(Object target, int index, Object value) {
        try {
            MethodUtil.invoke(writeMethod, target, new Object[]{new Integer(index), convertIfNeed(value)});
        } catch (Throwable t) {
            throw new IllegalPropertyRuntimeException(beanDesc.getBeanClass(), propertyName, t);
        }
    }

    public Object getValue(Object target, int index) {
        return MethodUtil.invoke(readMethod, target, new Object[]{new Integer(index)});
    }

    public boolean hasReadMethod() {
        return readMethod != null;
    }
    public boolean hasWriteMethod() {
        return writeMethod != null;
    }

    private Object convertIfNeed(Object arg) {
        return propertyDesc.convertIfNeed(arg);

    }
}