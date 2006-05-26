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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.struts.action.ActionMapping;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.action.ActionFactory;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class ProcessActionCreateInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -3426016173334908255L;

    private ActionFactory actionFactory;

    public void setActionFactory(ActionFactory actionFactory) {
        this.actionFactory = actionFactory;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) invocation.getArguments()[0];
        HttpServletResponse response = (HttpServletResponse) invocation.getArguments()[1];
        ActionMapping mapping = (ActionMapping) invocation.getArguments()[2];
        ExternalRequestProcessor processor = (ExternalRequestProcessor) invocation.getThis();

        return this.actionFactory.processActionCreate(request, response, mapping,
                processor.getLog(), processor.getInternal(), processor.getActionServlet());
    }

}
