package org.seasar.struts.factory;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.struts.config.ActionPropertyConfig;
import org.seasar.struts.config.ActionPropertyConfigImpl;

public class ConstantActionAnnotationHandler implements ActionAnnotationHandler {

    private static final String EXPORT_SUFFIX = "_EXPORT";

    public ActionPropertyConfig createActionPropertyConfig(BeanDesc beanDesc, PropertyDesc propertyDesc) {
        String fieldName = propertyDesc.getPropertyName() + EXPORT_SUFFIX;
        if (!beanDesc.hasField(fieldName)) {
            return new ActionPropertyConfigImpl();
        }
        String value = (String) beanDesc.getFieldValue(fieldName, null);
        return new ActionPropertyConfigImpl(value);
    }

}
