package org.seasar.struts.util;

import java.lang.reflect.Method;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.exception.ClassNotFoundRuntimeException;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.MethodUtil;

/**
 * @author Satsohi Kimura
 */
public class DxoUtil {
    public static Object convert(Object source, Class destClass)
            throws ClassNotFoundRuntimeException, NullPointerException {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        Class converterFactoryClass = ClassUtil
                .forName("org.seasar.extension.dxo.converter.ConverterFactory");
        Object converterFactory = container.getComponent(converterFactoryClass);
        Method method = ClassUtil.getMethod(converterFactoryClass,
                "getConverter", new Class[] { Class.class, Class.class });
        Object converter = MethodUtil.invoke(method, converterFactory,
                new Class[] { source.getClass(), destClass });

        Class converterClass = ClassUtil
                .forName("org.seasar.extension.dxo.converter.Converter");
        Class conversionContextClass = ClassUtil
                .forName("org.seasar.extension.dxo.converter.ConversionContext");

        Method convertMethod = ClassUtil
                .getMethod(converterClass, "convert", new Class[] {
                        Object.class, Class.class, conversionContextClass });
        return MethodUtil.invoke(convertMethod, converter, new Object[] {
                source, destClass, null });
    }
}
