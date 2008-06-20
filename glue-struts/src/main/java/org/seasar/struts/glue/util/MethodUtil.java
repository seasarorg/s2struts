package org.seasar.struts.glue.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.seasar.struts.glue.exception.GlueIllegalAccessException;
import org.seasar.struts.glue.exception.GlueInvocationTargetException;

public class MethodUtil {

    private MethodUtil() {
    }

    public static Object invoke(Method method, Object target, Object[] args)
            throws GlueInvocationTargetException,
            GlueIllegalAccessException {
        try {
            return method.invoke(target, args);
        } catch (InvocationTargetException ex) {
            Throwable t = ex.getCause();
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }
            if (t instanceof Error) {
                throw (Error) t;
            }
            throw new GlueInvocationTargetException(ex);
        } catch (IllegalAccessException ex) {
            throw new GlueIllegalAccessException(ex);
        }
    }
}
