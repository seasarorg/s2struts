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
package org.seasar.struts.pojo.processor;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.pojo.MethodBindingAction;
import org.seasar.struts.pojo.MethodBindingActionFactory;
import org.seasar.struts.processor.ExternalRequestProcessor;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class ProcessMethodBindingActionCreatorInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -2632163952881261609L;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) invocation.getArguments()[0];
        ExternalRequestProcessor processor = (ExternalRequestProcessor) invocation.getThis();

        MethodBindingAction action = MethodBindingActionFactory.createMethodBindingAction(request,
                processor.getActionServlet());
        if (action != null) {
            return action;
        }

        return invocation.proceed();
    }

}
