package org.seasar.struts.pojo.commands;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.util.MethodUtil;
import org.seasar.struts.pojo.PojoCommand;
import org.seasar.struts.pojo.PojoInvocation;
import org.seasar.struts.util.IndexedUtil;

public class IndexedDispatchCommand implements PojoCommand {

    public String execute(PojoInvocation invocation) {
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

        for (Enumeration paramNames = request.getParameterNames(); paramNames.hasMoreElements();) {
            String key = (String) paramNames.nextElement();
            String methodName = request.getParameter(key);
            if (IndexedUtil.isIndexedParameter(key)) {
                String indexedParam = IndexedUtil.getParameter(key);
                if (param.equals(indexedParam)) {
                    int index = IndexedUtil.getIndex(key);
                    Method method = getMethod(methods, methodName);
                    if (method != null) {
                        return (String) MethodUtil.invoke(method, action,
                                new Object[] { new Integer(index) });
                    }
                }
            }
        }

        return invocation.execute();
    }

    private Method getMethod(Method[] methods, String methodName) {
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().equals(methodName) && method.getParameterTypes().length == 1
                    && method.getParameterTypes()[0].equals(Integer.TYPE)) {
                return method;
            }
        }
        return null;
    }

}
