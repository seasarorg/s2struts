/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.struts.lessconfig.factory;

import java.lang.reflect.Field;

import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForm;
import org.seasar.struts.annotation.tiger.StrutsActionForward;
import org.seasar.struts.lessconfig.config.StrutsActionConfig;
import org.seasar.struts.lessconfig.config.StrutsActionFormConfig;
import org.seasar.struts.lessconfig.config.StrutsActionForwardConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class TigerStrutsConfigAnnotationHandler extends
        ConstantStrutsConfigAnnotationHandler {

    public StrutsActionConfig createStrutsActionConfig(Class clazz) {
        Class<?> actionClass = clazz;
        final StrutsAction config = actionClass
                .getAnnotation(StrutsAction.class);
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

            public Boolean validate() {
                return config.validate().getBoolean();
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

            public Boolean unknown() {
                return config.unknown().getBoolean();
            }

            public String roles() {
                return config.roles();
            }

            public Boolean cancellable() {
                return config.cancellable().getBoolean();
            }

            public String catalog() {
                return config.catalog();
            }

            public String command() {
                return config.command();
            }

            public String inherit() {
                return config.inherit();
            }

        };
    }

    public StrutsActionForwardConfig createStrutsActionForwardConfig(Field field) {
        final StrutsActionForward config = field
                .getAnnotation(StrutsActionForward.class);
        if (config == null) {
            return super.createStrutsActionForwardConfig(field);
        }
        return new StrutsActionForwardConfig() {

            public String path() {
                return config.path();
            }

            public Boolean redirect() {
                return config.redirect().getBoolean();
            }

        };
    }

    public StrutsActionFormConfig createStrutsActionFormConfig(Class clazz) {
        Class<?> formClass = clazz;
        final StrutsActionForm config = formClass
                .getAnnotation(StrutsActionForm.class);
        if (config == null) {
            return super.createStrutsActionFormConfig(clazz);
        }
        return new StrutsActionFormConfig() {

            public String name() {
                return config.name();
            }

            public Boolean restricted() {
                return config.restricted().getBoolean();
            }

            public String inherit() {
                return config.inherit();
            }

        };
    }

}
