package org.seasar.struts.pojo.processor;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.struts.action.ActionMapping;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.pojo.PojoProcessAction;
import org.seasar.struts.util.ClassRegister;

public class ProcessPojoActionCreateInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -8477606812838214606L;

    private ClassRegister classRegister;

    public void setClassRegister(ClassRegister classRegister) {
        this.classRegister = classRegister;
    }

    private PojoProcessAction pojoProcessAction;

    public void setPojoProcessAction(PojoProcessAction pojoProcessAction) {
        this.pojoProcessAction = pojoProcessAction;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        ActionMapping mapping = (ActionMapping) invocation.getArguments()[2];
        
        String type = mapping.getType();
        if (type != null) {
            Class action = this.classRegister.getClass(type);
            if (action.isInterface()) {
                // TODO pojoProcessAction.setServlet()の実行についてどうするか。
                // 今は利用してないので実行しなくてもOKだけど、将来必要になった場合、
                // どうするか考える必要がある。
                return this.pojoProcessAction;
            }
        }

        return invocation.proceed();
    }

}
