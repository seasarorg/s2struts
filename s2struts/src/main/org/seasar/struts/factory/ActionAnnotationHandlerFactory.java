package org.seasar.struts.factory;

import org.seasar.framework.exception.ClassNotFoundRuntimeException;
import org.seasar.framework.util.ClassUtil;

public class ActionAnnotationHandlerFactory {

    private static final String TIGER_ANNOTATION_HANDLER_CLASS_NAME = "org.seasar.struts.factory.TigerActionAnnotationHandler";
    
    private static final String BACKPORT175_ANNOTATION_HANDLER_CLASS_NAME = "org.seasar.struts.factory.Backport175ActionAnnotationHandler";

    private static ActionAnnotationHandler annotationHandler;
    
    static {
        Class clazz = ConstantActionAnnotationHandler.class;
        try {
            clazz = ClassUtil.forName(TIGER_ANNOTATION_HANDLER_CLASS_NAME);
        } catch (ClassNotFoundRuntimeException ignore) {
            try {
                clazz = ClassUtil.forName(BACKPORT175_ANNOTATION_HANDLER_CLASS_NAME);
            } catch (ClassNotFoundRuntimeException ignore2) {
            }
        }
        annotationHandler = (ActionAnnotationHandler) ClassUtil.newInstance(clazz);
    }

    protected ActionAnnotationHandlerFactory() {
    }
    
    public static ActionAnnotationHandler getAnnotationHandler() {
        return annotationHandler;
    }
    
    public static void setAnnotationHandler(ActionAnnotationHandler handler) {
        annotationHandler = handler;
    }

}
