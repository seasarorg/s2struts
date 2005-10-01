package org.seasar.struts.factory;

import java.lang.reflect.Field;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.util.FieldUtil;
import org.seasar.struts.config.StrutsActionConfig;
import org.seasar.struts.config.StrutsActionFormConfig;
import org.seasar.struts.config.StrutsActionForwardConfig;
import org.seasar.struts.config.StrutsActionPropertyConfig;
import org.seasar.struts.config.StrutsActionPropertyConfigImpl;

/**
 * @author Katsuhiko Nagashima
 */
public class ConstantAnnotationHandler implements AnnotationHandler {

    private static final String EXPORT_SUFFIX = "_EXPORT";

    public StrutsActionConfig createStrutsActionConfig(Class clazz) {
        return null;
    }

    public StrutsActionForwardConfig createStrutsActionForwardConfig(Field field) {
        return null;
    }

    public StrutsActionFormConfig createStrutsActionFormConfig(Class clazz) {
        return null;
    }

    public StrutsActionPropertyConfig createStrutsActionPropertyConfig(BeanDesc beanDesc, PropertyDesc propertyDesc) {
        String fieldName = propertyDesc.getPropertyName() + EXPORT_SUFFIX;
        if (beanDesc.hasField(fieldName)) {
            Field field = beanDesc.getField(fieldName);
            String value = (String) FieldUtil.get(field, null);
            return new StrutsActionPropertyConfigImpl(value);
        }
        return new StrutsActionPropertyConfigImpl();
    }
    
}
