package org.seasar.struts.glue.util;

import java.lang.reflect.Field;

import org.seasar.struts.glue.exception.GlueIllegalAccessException;

public class FieldUtil {

    private FieldUtil() {
    }

    public static Object get(Field field, Object target)
            throws GlueIllegalAccessException {
        try {
            return field.get(target);
        } catch (IllegalAccessException ex) {
            throw new GlueIllegalAccessException(ex);
        }
    }

    public static void set(Field field, Object target, Object value)
            throws GlueIllegalAccessException {
        try {
            field.set(target, value);
        } catch (IllegalAccessException ex) {
            throw new GlueIllegalAccessException(ex);
        }

    }
}
