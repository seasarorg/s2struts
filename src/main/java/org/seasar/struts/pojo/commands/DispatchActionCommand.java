package org.seasar.struts.pojo.commands;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.util.MethodUtil;
import org.seasar.struts.pojo.ActionCommand;
import org.seasar.struts.pojo.ActionInvocation;

public class DispatchActionCommand implements ActionCommand {

    public String execute(ActionInvocation invocation) {
        HttpServletRequest request = invocation.getRequest();
        ActionMapping mapping = invocation.getActionMapping();
        Class actionInterface = invocation.getActionInterface();
        Object action = invocation.getActionInstance();
        
        String param = mapping.getParameter();
        if (param == null) {
            return invocation.execute();
        }
        
        Method[] methods = actionInterface.getMethods();
        if (methods.length < 2) {
            return invocation.execute();
        }

        String methodName = request.getParameter(param);
        Method method = getMethod(methods, methodName);
        if (method == null) {
            return invocation.execute();
        }
        return (String) MethodUtil.invoke(method, action, null);
    }
    
    private Method getMethod(Method[] methods, String methodName) {
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().equals(methodName)
                    && method.getParameterTypes().length == 0) {
                return method;
            }
        }
        return null;
    }

}
