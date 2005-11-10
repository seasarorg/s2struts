package org.seasar.struts.factory;

import java.lang.reflect.Field;

import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForm;
import org.seasar.struts.annotation.tiger.StrutsActionForward;
import org.seasar.struts.config.StrutsActionConfig;
import org.seasar.struts.config.StrutsActionFormConfig;
import org.seasar.struts.config.StrutsActionForwardConfig;

public class TigerStrutsConfigAnnotationHandler extends ConstantStrutsConfigAnnotationHandler {

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

}
