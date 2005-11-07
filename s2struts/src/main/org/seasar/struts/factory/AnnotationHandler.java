package org.seasar.struts.factory;

import java.lang.reflect.Field;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.struts.config.StrutsActionConfig;
import org.seasar.struts.config.StrutsActionFormConfig;
import org.seasar.struts.config.StrutsActionForwardConfig;
import org.seasar.struts.config.StrutsActionPropertyConfig;

/**
 * @author Katsuhiko Nagashima
 */
public interface AnnotationHandler {

    public StrutsActionConfig createStrutsActionConfig(Class clazz);

    public StrutsActionForwardConfig createStrutsActionForwardConfig(Field field);

    public StrutsActionFormConfig createStrutsActionFormConfig(Class clazz);

    public StrutsActionPropertyConfig createStrutsActionPropertyConfig(BeanDesc beanDesc, PropertyDesc propertyDesc);

}