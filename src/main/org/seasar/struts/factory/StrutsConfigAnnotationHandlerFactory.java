package org.seasar.struts.factory;

import org.seasar.framework.exception.ClassNotFoundRuntimeException;
import org.seasar.framework.util.ClassUtil;

/**
 * @author Katsuhiko Nagashima
 */
public class StrutsConfigAnnotationHandlerFactory {
    
    private static final String TIGER_ANNOTATION_HANDLER_CLASS_NAME = "org.seasar.struts.factory.TigerStrutsConfigAnnotationHandler";
    
    private static final String BACKPORT175_ANNOTATION_HANDLER_CLASS_NAME = "org.seasar.struts.factory.Backport175StrutsConfigAnnotationHandler";

    private static StrutsConfigAnnotationHandler annotationHandler;
    
    static {
        Class clazz = ConstantStrutsConfigAnnotationHandler.class;
        try {
            clazz = ClassUtil.forName(TIGER_ANNOTATION_HANDLER_CLASS_NAME);
        } catch (ClassNotFoundRuntimeException ignore) {
            try {
                clazz = ClassUtil.forName(BACKPORT175_ANNOTATION_HANDLER_CLASS_NAME);
            } catch (ClassNotFoundRuntimeException ignore2) {
            }
        }
        annotationHandler = (StrutsConfigAnnotationHandler) ClassUtil.newInstance(clazz);
    }

    protected StrutsConfigAnnotationHandlerFactory() {
    }
    
    public static StrutsConfigAnnotationHandler getAnnotationHandler() {
        return annotationHandler;
    }
    
    public static void setAnnotationHandler(StrutsConfigAnnotationHandler handler) {
        annotationHandler = handler;
    }

}
