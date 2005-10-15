package org.seasar.struts.validator.factory;

import java.lang.reflect.Method;

import org.codehaus.backport175.reader.Annotation;

/**
 * @author Katsuhiko Nagashima
 */
public class Backport175AnnotationConverter extends AbstractAnnotationConverter {

    private static final AnnotationConverter instance = new Backport175AnnotationConverter();

    private Backport175AnnotationConverter() {
    }

    public static AnnotationConverter getInstance() {
        return instance;
    }

    protected Method[] getAnnotationMethods(Object obj) {
        Annotation annotation = (Annotation) obj;
        return annotation.annotationType().getMethods();
    }

    protected boolean isInstanceOfAnnotation(Object obj) {
        return (obj instanceof Annotation);
    }

}