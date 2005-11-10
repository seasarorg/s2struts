package org.seasar.struts.factory;

import java.lang.reflect.Field;

import org.seasar.struts.config.StrutsActionConfig;
import org.seasar.struts.config.StrutsActionFormConfig;
import org.seasar.struts.config.StrutsActionForwardConfig;

/**
 * @author Katsuhiko Nagashima
 */
public interface StrutsConfigAnnotationHandler {

    public StrutsActionConfig createStrutsActionConfig(Class clazz);

    public StrutsActionForwardConfig createStrutsActionForwardConfig(Field field);

    public StrutsActionFormConfig createStrutsActionFormConfig(Class clazz);

}