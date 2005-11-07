package org.seasar.struts.context;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.Base64Util;
import org.seasar.struts.Constants;

/**
 * @author Satoshi Kimura
 */
public class S2StrutsContextImpl implements S2StrutsContext {
    private String path;

    private Map methodBindingExpressions = new HashMap();

    public void clear(ContentsType type) {
        if (type == ContentsType.MethodBindingExpression) {
            this.methodBindingExpressions = new HashMap();
        }
    }

    public String getPath() {
        String param = getRequest().getParameter(Constants.PAGE_NAME_ELEMENT_VALUE);

        if (param != null) {
            this.path = new String(Base64Util.decode(param));
        }

        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMethodBindingExpression(String key, String value, String methodBindingExpression) {
        this.methodBindingExpressions.put(key + value, methodBindingExpression);
    }

    public String getMethodBindingExpression() {
        HttpServletRequest request = getRequest();
        for (Enumeration paramNames = request.getParameterNames(); paramNames.hasMoreElements();) {
            String key = (String)paramNames.nextElement();
            String value = request.getParameter(key);
            Object ret = this.methodBindingExpressions.get(key + value);
            if (ret == null) {
                key = key.replaceFirst("(\\.x$)|(\\.y$)", "");
                value = request.getParameter(key);
                ret = this.methodBindingExpressions.get(key + value);
            }
            if (ret != null) {
                return (String)ret;
            }
        }
        return null;
    }

    private static final HttpServletRequest getRequest() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return container.getRequest();
    }
}
