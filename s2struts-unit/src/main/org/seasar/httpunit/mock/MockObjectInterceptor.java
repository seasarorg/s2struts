package org.seasar.httpunit.mock;

import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;

public class MockObjectInterceptor extends AbstractInterceptor {
    private String methodName;
    private Object[] arguments;
    private Map values = new HashMap();
    private Map propertyValues = new HashMap();
    private Object target;

    /**
     * 戻り値を設定する
     * 
     * @param method
     * @param value
     */
    public void setReturnValue(Object method, Object value) {
        this.values.put(method, value);
    }

    /**
     * 戻り値を取得する
     * 
     * @param method
     * @return
     */
    public Object getReturnValue(Object method) {
        return this.values.get(method);
    }

    /**
     * key:valueの形の戻り値を設定する
     * 
     * @param method
     * @param key
     * @param value
     */
    public void setReturnValue(Object method, Object key, Object value) {
        Map methodMap = (Map) this.values.get(method);

        if (methodMap == null) {
            methodMap = new HashMap();
            values.put(method, methodMap);
        }

        methodMap.put(key, value);
    }

    /**
     * key,valueの形の戻り値を取得する
     * 
     * @param method
     * @param key
     * @return
     */
    public Object getReturnValue(Object method, Object key) {
        Object methodValues = this.values.get(method);

        if (methodValues != null) {
            if (methodValues instanceof Map) {
                return ((Map) methodValues).get(key);
            } else {
                return methodValues;
            }
        }

        return null;
    }

    /**
     * 処理の委譲オブジェクトを設定する。 <br>
     * 戻り値が設定されていない場合に、呼び出される。
     * 
     * @param target
     */
    public void setDelegateTarget(Object target) {
        this.target = target;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        init(invocation);

        setPropertyValue();

        Object retValue = getMockValue(invocation);

        if (retValue == null) {
            retValue = delegate(invocation);
        }

        if (retValue == null) {
            retValue = getPropertyValue(methodName);
        }

        return retValue;
    }

    public void setPropertyValue() {
        if (methodName.startsWith("set") && (arguments.length == 1)) {
            this.propertyValues.put(methodName.substring("set".length()), arguments[0]);
        }
    }

    public Object getPropertyValue(String methodName) {
        return getPropertyValue(methodName, false);
    }

    public Object getPropertyValue(String methodName, boolean force) {
        if ((arguments == null) && (force == false)) {
            return null;
        }

        if (force || (arguments.length == 0)) {
            if (methodName.startsWith("get")) {
                return this.propertyValues.get(methodName.substring("get".length()));
            } else if (methodName.startsWith("is")) {
                return this.propertyValues.get(methodName.substring("is".length()));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private void init(MethodInvocation invocation) {
        methodName = invocation.getMethod().getName();
        arguments = invocation.getArguments();
    }

    private Object getMockValue(MethodInvocation invocation) {
        Object retValue = null;

        if (arguments.length == 0) {
            retValue = getReturnValue(methodName);
        } else if (arguments.length == 1) {
            retValue = getReturnValue(methodName, arguments[0]);
        }

        return retValue;
    }

    private Object delegate(MethodInvocation invocation) {
        if (target == null) {
            return null;
        }

        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(target.getClass());

        if (beanDesc.hasMethod(methodName)) {
            return beanDesc.invoke(target, methodName, arguments);
        } else {
            return null;
        }
    }
}