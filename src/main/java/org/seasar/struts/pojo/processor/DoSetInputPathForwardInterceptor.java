package org.seasar.struts.pojo.processor;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.util.S2StrutsContextUtil;

public class DoSetInputPathForwardInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 7571491617812184586L;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        String uri = (String) invocation.getArguments()[0];
        
        S2StrutsContextUtil.setPath(uri);
        return invocation.proceed();
    }

}
