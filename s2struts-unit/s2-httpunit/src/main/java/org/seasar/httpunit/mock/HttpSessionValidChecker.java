package org.seasar.httpunit.mock;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

public class HttpSessionValidChecker extends AbstractInterceptor {
    MockObjectInterceptor mockObjectInterceptor;

    public HttpSessionValidChecker() {
    }

    public void setMockObjectInterceptor(MockObjectInterceptor mockObjectInterceptor) {
        this.mockObjectInterceptor = mockObjectInterceptor;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object isValidValue = mockObjectInterceptor.getPropertyValue(MockHttpSession.METHOD_NAME_IS_VALID, true);
        String methodName = invocation.getMethod().getName();

        if (methodName.equals(MockHttpSession.METHOD_NAME_IS_VALID)) {
            return isValidValue;
        } else if (methodName.equals(MockHttpSession.METHOD_NAME_SET_VALID)) {
            return invocation.proceed();
        }

        if ((isValidValue != null) && isValidValue.equals(Boolean.TRUE)) {
            throw new IllegalStateException("session has been invalidated!");
        }

        return invocation.proceed();
    }
}