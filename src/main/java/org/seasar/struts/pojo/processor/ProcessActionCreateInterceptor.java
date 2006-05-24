package org.seasar.struts.pojo.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.struts.action.ActionMapping;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.action.ActionFactory;
import org.seasar.struts.processor.ExternalRequestProcessor;

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
