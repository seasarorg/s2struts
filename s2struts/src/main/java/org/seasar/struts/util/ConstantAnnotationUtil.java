package org.seasar.struts.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 定数アノテーションのためのユーティリティクラスです。
 * 
 * @author Satoshi Kimura
 */
public abstract class ConstantAnnotationUtil {

    protected ConstantAnnotationUtil() {
        super();
    }

    /**
     * 定数アノテーションの型が{@link String}であれば<code>true</code>を返します。
     * 
     * @param field
     * @return
     */
    public static boolean isConstantAnnotationStringField(Field field) {
        final int modifiers = field.getModifiers();
        return Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)
                && field.getType().equals(String.class);
    }

    /**
     * 定数アノテーションの型が{@link Class}であれば<code>true</code>を返します。
     * 
     * @param field
     * @return
     */
    public static boolean isConstantAnnotationClassField(Field field) {
        final int modifiers = field.getModifiers();
        return Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)
                && field.getType().equals(Class.class);
    }

    /**
     * 定数アノテーションの型が<code>int</code>であれば<code>true</code>を返します。
     * 
     * @param field
     * @return
     */
    public static boolean isConstantAnnotationIntField(Field field) {
        final int modifiers = field.getModifiers();
        return Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)
                && field.getType().equals(Integer.TYPE);
    }

}
