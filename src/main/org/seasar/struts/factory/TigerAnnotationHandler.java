package org.seasar.struts.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.struts.Constants;
import org.seasar.struts.annotation.Export;
import org.seasar.struts.annotation.ExportToSession;
import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForm;
import org.seasar.struts.annotation.StrutsActionForward;
import org.seasar.struts.config.StrutsActionConfig;
import org.seasar.struts.config.StrutsActionFormConfig;
import org.seasar.struts.config.StrutsActionForwardConfig;
import org.seasar.struts.config.StrutsActionPropertyConfig;
import org.seasar.struts.config.StrutsActionPropertyConfigImpl;

public class TigerAnnotationHandler extends ConstantAnnotationHandler {

    public StrutsActionConfig createStrutsActionConfig(Class clazz) {
        Class<?> actionClass = clazz;
        final StrutsAction config = actionClass.getAnnotation(StrutsAction.class);
        if (config == null) {
            return super.createStrutsActionConfig(clazz);
        }
        return new StrutsActionConfig() {
            public String path() {
                return config.path();
            }

            public String name() {
                return config.name();
            }

            public String scope() {
                return config.scope().getScopeMode();
            }

            public boolean validate() {
                return config.validate();
            }

            public String input() {
                return config.input();
            }

            public String parameter() {
                return config.parameter();
            }

            public String attribute() {
                return config.attribute();
            }

            public String forward() {
                return config.forward();
            }

            public String include() {
                return config.include();
            }

            public String prefix() {
                return config.prefix();
            }

            public String suffix() {
                return config.suffix();
            }

            public boolean unknown() {
                return config.unknown();
            }

            public String roles() {
                return config.roles();
            }

        };
    }

    public StrutsActionForwardConfig createStrutsActionForwardConfig(Field field) {
        final StrutsActionForward config = field.getAnnotation(StrutsActionForward.class);
        if (config == null) {
            return super.createStrutsActionForwardConfig(field);
        }
        return new StrutsActionForwardConfig() {

            public String path() {
                return config.path();
            }

            public boolean redirect() {
                return config.redirect();
            }
            
        };
    }

    public StrutsActionFormConfig createStrutsActionFormConfig(Class clazz) {
        Class<?> formClass = clazz;
        final StrutsActionForm config = formClass.getAnnotation(StrutsActionForm.class);
        if (config == null) {
            return super.createStrutsActionFormConfig(clazz);
        }
        return new StrutsActionFormConfig() {

            public String name() {
                return config.name();
            }

            public boolean restricted() {
                return config.restricted();
            }
            
        };
    }

    public StrutsActionPropertyConfig createStrutsActionPropertyConfig(BeanDesc beanDesc, PropertyDesc propertyDesc) {
        Method readMehod = propertyDesc.getReadMethod();
        ExportToSession toSession = readMehod.getAnnotation(ExportToSession.class);
        if (toSession != null) {
            return new StrutsActionPropertyConfigImpl(Constants.SESSION);
        }
        Export export = readMehod.getAnnotation(Export.class);
        if (export != null) {
            return new StrutsActionPropertyConfigImpl(export.value().getScopeMode());
        }
        return super.createStrutsActionPropertyConfig(beanDesc, propertyDesc);
    }

}
