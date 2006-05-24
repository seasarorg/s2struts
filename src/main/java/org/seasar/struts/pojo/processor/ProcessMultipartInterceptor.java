package org.seasar.struts.pojo.processor;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.util.S2StrutsContextUtil;

public class ProcessMultipartInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -1113473917001484741L;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        HttpServletRequest result = (HttpServletRequest) invocation.proceed();
        S2StrutsContextUtil.setRequest(result);
        return result;
    }

}
