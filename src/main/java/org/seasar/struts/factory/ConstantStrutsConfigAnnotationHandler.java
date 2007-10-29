/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.struts.factory;

import java.lang.reflect.Field;
import java.util.Map;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.struts.config.StrutsActionConfig;
import org.seasar.struts.config.StrutsActionFormConfig;
import org.seasar.struts.config.StrutsActionForwardConfig;
import org.seasar.struts.util.ConstantAnnotationUtil;
import org.seasar.struts.util.ConstantValueUtil;

/**
 * @author Katsuhiko Nagashima
 */
public class ConstantStrutsConfigAnnotationHandler implements
        StrutsConfigAnnotationHandler {

    private static final String ACTION = "ACTION";

    private static final String FORM = "FORM";

    private static final String FORWARD_SUFFIX = "_FORWARD";

    public StrutsActionConfig createStrutsActionConfig(Class clazz) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(clazz);
        if (!beanDesc.hasField(ACTION)) {
            return null;
        }
        if (clazz != beanDesc.getField(ACTION).getDeclaringClass()) {
            return null;
        }
        if (!ConstantAnnotationUtil.isConstantAnnotationStringField(beanDesc
                .getField(ACTION))) {
            return null;
        }
        String value = (String) beanDesc.getFieldValue(ACTION, null);
        final Map parameters = ConstantValueUtil.toMap(value);

        return new StrutsActionConfig() {

            public String path() {
                String value = (String) parameters.get("path");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_PATH;
                }
                return value;
            }

            public String name() {
                String value = (String) parameters.get("name");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_NAME;
                }
                return value;
            }

            public String scope() {
                String value = (String) parameters.get("scope");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_SCOPE;
                }
                return value;
            }

            public boolean validate() {
                String value = (String) parameters.get("validate");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_VALIDATE;
                }
                return BooleanConversionUtil.toPrimitiveBoolean(value);
            }

            public String input() {
                String value = (String) parameters.get("input");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_INPUT;
                }
                return value;
            }

            public String parameter() {
                String value = (String) parameters.get("parameter");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_PARAMETER;
                }
                return value;
            }

            public String attribute() {
                String value = (String) parameters.get("attribute");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_ATTRIBUTE;
                }
                return value;
            }

            public String forward() {
                String value = (String) parameters.get("forward");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_FORWARD;
                }
                return value;
            }

            public String include() {
                String value = (String) parameters.get("include");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_INCLUDE;
                }
                return value;
            }

            public String prefix() {
                String value = (String) parameters.get("prefix");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_PREFIX;
                }
                return value;
            }

            public String suffix() {
                String value = (String) parameters.get("suffix");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_SUFFIX;
                }
                return value;
            }

            public boolean unknown() {
                String value = (String) parameters.get("unknown");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_UNKNOWN;
                }
                return BooleanConversionUtil.toPrimitiveBoolean(value);
            }

            public String roles() {
                String value = (String) parameters.get("roles");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_ROLES;
                }
                return value;
            }

            public boolean cancellable() {
                String value = (String) parameters.get("cancellable");
                if (value == null) {
                    return StrutsActionConfig.DEFAULT_CANCELLABLE;
                }
                return BooleanConversionUtil.toPrimitiveBoolean(value);
            }

        };
    }

    public StrutsActionForwardConfig createStrutsActionForwardConfig(Field field) {
        String fieldName = field.getName() + FORWARD_SUFFIX;
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(field
                .getDeclaringClass());
        if (!beanDesc.hasField(fieldName)) {
            return null;
        }
        if (!ConstantAnnotationUtil.isConstantAnnotationStringField(beanDesc
                .getField(fieldName))) {
            return null;
        }
        String value = (String) beanDesc.getFieldValue(fieldName, null);
        final Map parameters = ConstantValueUtil.toMap(value, "path");

        return new StrutsActionForwardConfig() {

            public String path() {
                return (String) parameters.get("path");
            }

            public boolean redirect() {
                String value = (String) parameters.get("redirect");
                if (value == null) {
                    return StrutsActionForwardConfig.DEFAULT_REDIRECT;
                }
                return BooleanConversionUtil.toPrimitiveBoolean(value);
            }
        };
    }

    public StrutsActionFormConfig createStrutsActionFormConfig(Class clazz) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(clazz);
        if (!beanDesc.hasField(FORM)) {
            return null;
        }
        if (clazz != beanDesc.getField(FORM).getDeclaringClass()) {
            return null;
        }
        if (!ConstantAnnotationUtil.isConstantAnnotationStringField(beanDesc
                .getField(FORM))) {
            return null;
        }

        String value = (String) beanDesc.getFieldValue(FORM, null);
        final Map parameters = ConstantValueUtil.toMap(value, "name");

        return new StrutsActionFormConfig() {

            public String name() {
                String value = (String) parameters.get("name");
                if (value == null) {
                    return StrutsActionFormConfig.DEFAULT_NAME;
                }
                return value;
            }

            public boolean restricted() {
                String value = (String) parameters.get("restricted");
                if (value == null) {
                    return StrutsActionFormConfig.DEFAULT_RESTRICTED;
                }
                return BooleanConversionUtil.toPrimitiveBoolean(value);
            }

        };
    }

}
