package org.seasar.struts.validator.factory;

import org.seasar.framework.exception.ClassNotFoundRuntimeException;
import org.seasar.framework.util.ClassUtil;

/**
 * @author Katsuhiko Nagashima
 */
public class ValidatorAnnotationHandlerFactory {

    private static final String TIGER_ANNOTATION_HANDLER_CLASS_NAME = "org.seasar.struts.validator.factory.TigerValidatorAnnotationHandler";
    
    private static final String BACKPORT175_ANNOTATION_HANDLER_CLASS_NAME = "org.seasar.struts.validator.factory.Backport175ValidatorAnnotationHandler";

    private static ValidatorAnnotationHandler annotationHandler;
    
    static {
        Class clazz = ConstantValidatorAnnotationHandler.class;
        try {
            clazz = ClassUtil.forName(TIGER_ANNOTATION_HANDLER_CLASS_NAME);
        } catch (ClassNotFoundRuntimeException ignore) {
            try {
                clazz = ClassUtil.forName(BACKPORT175_ANNOTATION_HANDLER_CLASS_NAME);
            } catch (ClassNotFoundRuntimeException ignore2) {
            }
        }
        annotationHandler = (ValidatorAnnotationHandler) ClassUtil.newInstance(clazz);
    }

    protected ValidatorAnnotationHandlerFactory() {
    }
    
    public static ValidatorAnnotationHandler getAnnotationHandler() {
        return annotationHandler;
    }
    
    public static void setAnnotationHandler(ValidatorAnnotationHandler handler) {
        annotationHandler = handler;
    }

}
