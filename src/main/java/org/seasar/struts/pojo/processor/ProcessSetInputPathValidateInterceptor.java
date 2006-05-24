package org.seasar.struts.pojo.processor;

import java.lang.reflect.InvocationTargetException;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionMapping;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.exception.IllegalAccessRuntimeException;
import org.seasar.framework.exception.InvocationTargetRuntimeException;
import org.seasar.framework.exception.NoSuchMethodRuntimeException;
import org.seasar.struts.util.S2StrutsContextUtil;

public class ProcessSetInputPathValidateInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -3183639341132304339L;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        ActionMapping mapping = (ActionMapping) invocation.getArguments()[3];
        
        String input = mapping.getInput();
        if (input == null && S2StrutsContextUtil.getPreviousInputPath() != null) {
            ActionMapping newMapping = new ActionMapping();

            try {
                PropertyUtils.copyProperties(newMapping, mapping);
            } catch (IllegalAccessException e) {
                throw new IllegalAccessRuntimeException(newMapping.getClass(), e);
            } catch (InvocationTargetException e) {
                throw new InvocationTargetRuntimeException(newMapping.getClass(), e);
            } catch (NoSuchMethodException e) {
                throw new NoSuchMethodRuntimeException(newMapping.getClass(), null, null, e);
            }

            input = S2StrutsContextUtil.getPreviousInputPath();
            newMapping.setInput(input);
            invocation.getArguments()[3] = newMapping;
        }
        return invocation.proceed();
    }

}
