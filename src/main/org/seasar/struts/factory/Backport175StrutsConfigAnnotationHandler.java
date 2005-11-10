package org.seasar.struts.factory;

import java.lang.reflect.Field;

import org.codehaus.backport175.reader.Annotations;
import org.seasar.struts.annotation.backport175.StrutsAction;
import org.seasar.struts.annotation.backport175.StrutsActionForm;
import org.seasar.struts.annotation.backport175.StrutsActionForward;
import org.seasar.struts.config.StrutsActionConfig;
import org.seasar.struts.config.StrutsActionFormConfig;
import org.seasar.struts.config.StrutsActionForwardConfig;

/**
 * @author Katsuhiko Nagashima
 */
public class Backport175StrutsConfigAnnotationHandler extends ConstantStrutsConfigAnnotationHandler {

    public StrutsActionConfig createStrutsActionConfig(Class clazz) {
        final StrutsAction config = (StrutsAction) Annotations.getAnnotation(StrutsAction.class, clazz);
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
                return config.scope();
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
        final StrutsActionForward config = (StrutsActionForward) Annotations.getAnnotation(StrutsActionForward.class, field);
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
        final StrutsActionForm config = (StrutsActionForm) Annotations.getAnnotation(StrutsActionForm.class, clazz);
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
