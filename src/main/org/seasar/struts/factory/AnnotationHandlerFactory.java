package org.seasar.struts.factory;

import org.seasar.framework.exception.ClassNotFoundRuntimeException;
import org.seasar.framework.util.ClassUtil;

/**
 * @author Katsuhiko Nagashima
 */
public class AnnotationHandlerFactory {
    
    private static final String TIGER_ANNOTATION_HANDLER_CLASS_NAME = "org.seasar.struts.factory.TigerAnnotationHandler";
    
    private static final String BACKPORT175_ANNOTATION_HANDLER_CLASS_NAME = "org.seasar.struts.factory.Backport175AnnotationHandler";

    private static AnnotationHandler annotationHandler;
    
    static {
        Class clazz = ConstantAnnotationHandler.class;
        try {
            clazz = ClassUtil.forName(TIGER_ANNOTATION_HANDLER_CLASS_NAME);
        } catch (ClassNotFoundRuntimeException ignore) {
            try {
                clazz = ClassUtil.forName(BACKPORT175_ANNOTATION_HANDLER_CLASS_NAME);
            } catch (ClassNotFoundRuntimeException ignore2) {
            }
        }
        annotationHandler = (AnnotationHandler) ClassUtil.newInstance(clazz);
    }

    protected AnnotationHandlerFactory() {
    }
    
    public static AnnotationHandler getAnnotationHandler() {
        return annotationHandler;
    }
    
    public static void setAnnotationHandler(AnnotationHandler handler) {
        annotationHandler = handler;
    }

}
