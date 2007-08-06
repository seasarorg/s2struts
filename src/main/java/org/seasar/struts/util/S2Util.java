package org.seasar.struts.util;

import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.MethodUtil;

/**
 * @author Satsohi Kimura
 */
public class S2Util {

    public static HttpServletResponse getResponse(S2Container container) {
        try {
            return getResponse(getExternalContext(container));
        } catch (Throwable e) {
            Method method = ClassUtil.getMethod(S2Container.class, "getResponse", new Class[0]);
            HttpServletResponse res = (HttpServletResponse) MethodUtil.invoke(method, container, null);
            return res;
        }
    }

    private static HttpServletResponse getResponse(Object externalContext) {
        Method method = ClassUtil.getMethod(ClassUtil.forName("org.seasar.framework.container.ExternalContext"),
                "getResponse", new Class[0]);
        return (HttpServletResponse) MethodUtil.invoke(method, externalContext, null);
    }

    public static ServletContext getServletContext(S2Container container) {
        try {
            return getServletContext(getExternalContext(container));
        } catch (Throwable e) {
            Method method = ClassUtil.getMethod(S2Container.class, "getServletContext", new Class[0]);
            ServletContext servletContext = (ServletContext) MethodUtil.invoke(method, container, null);
            return servletContext;
        }
    }

    private static ServletContext getServletContext(Object externalContext) {
        Method method = ClassUtil.getMethod(ClassUtil.forName("org.seasar.framework.container.ExternalContext"),
                "getApplication", new Class[0]);
        return (ServletContext) MethodUtil.invoke(method, externalContext, null);
    }

    public static HttpSession getSession(S2Container container) {
        return getRequest(container).getSession();
    }

    public static HttpServletRequest getRequest(S2Container container) {
        try {
            return getRequest(getExternalContext(container));
        } catch (Throwable e) {
            Method method = ClassUtil.getMethod(S2Container.class, "getRequest", new Class[0]);
            HttpServletRequest req = (HttpServletRequest) MethodUtil.invoke(method, container, null);
            return req;
        }
    }

    private static Object getExternalContext(S2Container container) {
        Method method = ClassUtil.getMethod(S2Container.class, "getExternalContext", new Class[0]);
        return MethodUtil.invoke(method, container, null);
    }

    private static HttpServletRequest getRequest(Object externalContext) {
        Method method = ClassUtil.getMethod(ClassUtil.forName("org.seasar.framework.container.ExternalContext"),
                "getRequest", new Class[0]);
        return (HttpServletRequest) MethodUtil.invoke(method, externalContext, null);
    }

    public static void setRequest(S2Container container, ServletRequest req) {
        setRequest(container, (HttpServletRequest) req);
    }

    public static void setRequest(S2Container container, HttpServletRequest req) {
        try {
            setRequest(getExternalContext(container), (HttpServletRequest) req);
        } catch (Throwable e) {
            Method method = ClassUtil.getMethod(S2Container.class, "setRequest",
                    new Class[] { HttpServletRequest.class });
            MethodUtil.invoke(method, container, new Object[] { req });
        }
    }

    private static void setRequest(Object externalContext, HttpServletRequest request) {
        Method method = ClassUtil.getMethod(ClassUtil.forName("org.seasar.framework.container.ExternalContext"),
                "setRequest", new Class[] { Object.class });
        MethodUtil.invoke(method, externalContext, new Object[] { request });
    }
}
