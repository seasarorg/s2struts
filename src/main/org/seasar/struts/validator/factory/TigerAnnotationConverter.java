package org.seasar.struts.validator.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Katsuhiko Nagashima
 */
public class TigerAnnotationConverter extends AbstractAnnotationConverter {

    private static final AnnotationConverter instance = new TigerAnnotationConverter();

    private TigerAnnotationConverter() {
    }

    public static AnnotationConverter getInstance() {
        return instance;
    }

    protected Method[] getAnnotationMethods(Object obj) {
        List<Method> result = new ArrayList<Method>();

        Annotation annotation = (Annotation) obj;
        for (Method method : annotation.annotationType().getMethods()) {
            if (method.getDeclaringClass() != Annotation.class) {
                result.add(method);
            }
        }
        
        Method[] array = new Method[result.size()];
        return result.toArray(array);
    }

    protected boolean isInstanceOfAnnotation(Object obj) {
        return (obj instanceof Annotation);
    }

}