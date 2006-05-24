package org.seasar.struts.pojo;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionServlet;
import org.seasar.struts.util.IndexedUtil;
import org.seasar.struts.util.S2StrutsContextUtil;

public class MethodBindingActionFactory {

    public static MethodBindingAction createMethodBindingAction(HttpServletRequest request,
            ActionServlet servlet) {

        MethodBinding methodBinding = createMethodBinding(request);
        if (methodBinding == null) {
            return null;
        }
        MethodBindingAction action = new MethodBindingAction(methodBinding);
        action.setServlet(servlet);
        return action;
    }

    private static MethodBinding createMethodBinding(HttpServletRequest request) {
        for (Enumeration paramNames = request.getParameterNames(); paramNames.hasMoreElements();) {
            String key = (String) paramNames.nextElement();
            String value = request.getParameter(key);
            String expression = S2StrutsContextUtil.getMethodBindingExpression(key, value);
            if (expression != null) {
                return new MethodBinding(expression);
            }

            // image tag
            String imageKey = key.replaceFirst("(\\.x$)|(\\.y$)", "");
            expression = S2StrutsContextUtil.getMethodBindingExpression(imageKey, null);
            if (expression != null) {
                return new MethodBinding(expression);
            }

            // indexed
            if (IndexedUtil.isIndexedParameter(key)) {
                String indexedKey = IndexedUtil.getParameter(key);
                int index = IndexedUtil.getIndex(key);
                expression = S2StrutsContextUtil.getMethodBindingExpression(indexedKey, value);
                if (expression != null) {
                    return new MethodBinding(expression, index);
                }
            }
        }
        return null;
    }

}
