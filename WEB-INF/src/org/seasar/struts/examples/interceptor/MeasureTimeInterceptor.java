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
package org.seasar.struts.examples.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Satoshi Kimura
 */
public class MeasureTimeInterceptor implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Date start = new Date();
        
        Object ret = invocation.proceed();
        
        Object[] args = invocation.getArguments();
        HttpServletRequest request = (HttpServletRequest)args[0];
        String message = request.getServletPath() + " : Time = "
                + (new Date().getTime() - start.getTime()+ " milliseconds");
        System.out.println(message);
        return ret;

    }

}