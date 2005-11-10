package org.seasar.struts.factory;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.struts.config.ActionPropertyConfig;

public interface ActionAnnotationHandler {

    public ActionPropertyConfig createActionPropertyConfig(BeanDesc beanDesc, PropertyDesc propertyDesc);

}
