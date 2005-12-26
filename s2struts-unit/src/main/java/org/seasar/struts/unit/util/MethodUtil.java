package org.seasar.struts.unit.util;

import java.lang.reflect.Method;

import org.seasar.framework.exception.NoSuchMethodRuntimeException;

/**
 * <p>
 * public以外のメソッドの実行も可能にするユーティリティーです。
 * </p>
 * <p>
 * 指定したメソッドが存在しない場合には、親クラスを参照し、メソッドを検索します。
 * </p>
 * 
 * @author Satoshi Kimura
 */
public class MethodUtil {

    private MethodUtil() {
    }

    /**
     * 指定したメソッドを実行します。
     * 
     * @param target 実行対象のオブジェクト
     * @param methodName メソッドの名前
     * @param parameterTypes パラメータの型を表す配列
     * @param args メソッド呼び出しに使用される引数
     * @return 実行したメソッドの戻り値
     */
    public static Object invoke(Object target, String methodName, Class[] parameterTypes, Object[] args) {
        Class clazz = target.getClass();

        try {
            return invoke(clazz, target, methodName, parameterTypes, args);
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodRuntimeException(clazz, methodName, parameterTypes, e);
        }
    }

    private static Object invoke(Class targetClass, Object target, String methodName, Class[] parameterTypes,
            Object[] args) throws NoSuchMethodException {
        try {
            Method method = targetClass.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);

            return org.seasar.framework.util.MethodUtil.invoke(method, target, args);
        } catch (NoSuchMethodException e) {
            Class superClass = targetClass.getSuperclass();

            if (superClass != null) {
                return invoke(superClass, target, methodName, parameterTypes, args);
            } else {
                throw e;
            }
        }
    }
}