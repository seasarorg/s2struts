package org.seasar.struts.glue.util;

import java.lang.reflect.Field;

import org.seasar.struts.glue.exception.IllegalAccessRuntimeException;

public class FieldUtil {

    private FieldUtil() {
    }

    public static Object get(final Field field, final Object target)
            throws IllegalAccessRuntimeException {
        try {
            return field.get(target);
        } catch (final IllegalAccessException ex) {
            throw new IllegalAccessRuntimeException(ex);
        }
    }

    public static void set(final Field field, final Object target,
            final Object value) throws IllegalAccessRuntimeException {
        try {
            field.set(target, value);
        } catch (final IllegalAccessException ex) {
            throw new IllegalAccessRuntimeException(ex);
        }

    }
}
