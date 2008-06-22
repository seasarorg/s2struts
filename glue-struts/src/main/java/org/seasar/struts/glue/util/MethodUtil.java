package org.seasar.struts.glue.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.seasar.struts.glue.exception.IllegalAccessRuntimeException;
import org.seasar.struts.glue.exception.InvocationTargetRuntimeException;

public class MethodUtil {

    private MethodUtil() {
    }

    public static Object invoke(final Method method, final Object target,
            final Object[] args) throws InvocationTargetRuntimeException,
            IllegalAccessRuntimeException {
        try {
            return method.invoke(target, args);
        } catch (final InvocationTargetException ex) {
            final Throwable t = ex.getCause();
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }
            if (t instanceof Error) {
                throw (Error) t;
            }
            throw new InvocationTargetRuntimeException(ex);
        } catch (final IllegalAccessException ex) {
            throw new IllegalAccessRuntimeException(ex);
        }
    }
}
