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