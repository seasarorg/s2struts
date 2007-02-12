package org.seasar.struts.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Satoshi Kimura
 * 
 */
public abstract class ConstantAnnotationUtil {

	protected ConstantAnnotationUtil() {
		super();
	}

    public static boolean isConstantAnnotationStringField(Field field) {
        final int modifiers = field.getModifiers();
        return Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)
                && field.getType().equals(String.class);
    }
    public static boolean isConstantAnnotationClassField(Field field) {
        final int modifiers = field.getModifiers();
        return Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)
                && field.getType().equals(Class.class);
    }

    public static boolean isConstantAnnotationIntField(Field field) {
        final int modifiers = field.getModifiers();
        return Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)
                && field.getType().equals(Integer.TYPE);
    }

}
