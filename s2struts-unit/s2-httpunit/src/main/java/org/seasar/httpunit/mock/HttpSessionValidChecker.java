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