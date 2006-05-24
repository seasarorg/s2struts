package org.seasar.struts.pojo.processor;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.pojo.MethodBindingAction;
import org.seasar.struts.pojo.MethodBindingActionFactory;
import org.seasar.struts.processor.ExternalRequestProcessor;

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
