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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Map;

import org.apache.commons.validator.Field;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.validator.annotation.tiger.Arg;
import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.Message;
import org.seasar.struts.validator.annotation.tiger.NoValidate;
import org.seasar.struts.validator.annotation.tiger.Validator;
import org.seasar.struts.validator.annotation.tiger.ValidatorField;
import org.seasar.struts.validator.annotation.tiger.ValidatorTarget;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class TigerValidatorAnnotationHandler extends ConstantValidatorAnnotationHandler {

    protected Comparator getPropertyDescComparator(BeanDesc beanDesc) {
        return new TigerPropertyDescComparator(beanDesc);
    }

    protected boolean noValidate(BeanDesc beanDesc, PropertyDesc propDesc) {
        Method method = getMethodForValidation(propDesc);
        if (!hasAnnotation(method)) {
            return super.noValidate(beanDesc, propDesc);
        }

        return method.getAnnotation(NoValidate.class) != null;
    }

    protected String getDepends(BeanDesc beanDesc, PropertyDesc propDesc) {
        Method method = getMethodForValidation(propDesc);
        if (!hasAnnotation(method)) {
            return super.getDepends(beanDesc, propDesc);
        }

        StringBuffer depends = new StringBuffer("");

        String autoTypeValidatorName = getAutoTypeValidatorName(propDesc);
        if (!StringUtil.isEmpty(autoTypeValidatorName)) {
            depends.append(autoTypeValidatorName).append(",");
        }

        for (Annotation annotation : method.getAnnotations()) {
            Class<?> type = annotation.annotationType();
            ValidatorTarget target = type.getAnnotation(ValidatorTarget.class);
            if (target != null) {
                String depend = "";
                if (annotation instanceof ValidatorField) {
                    depend = createValidatorFieldDepends((ValidatorField) annotation);
                } else {
                    depend = getValidatorName(type);
                }
                depends.append(depend);
                depends.append(",");
            }
        }
        if (depends.length() < 1) {
            return null;
        }
        depends.setLength(depends.length() - 1);
        return depends.toString();
    }

    protected void registerMessage(Field field, BeanDesc beanDesc, PropertyDesc propDesc) {
        Method method = getMethodForValidation(propDesc);
        if (!hasAnnotation(method)) {
            super.registerMessage(field, beanDesc, propDesc);
            return;
        }

        Annotation annotation = method.getAnnotation(Message.class);
        if (annotation != null) {
            Map parameter = TigerAnnotationConverter.getInstance().toMap(annotation);
            executeMessageConfigRegister(field, parameter);
        }
    }

    protected boolean hasArgsAnnotation(BeanDesc beanDesc, PropertyDesc propDesc) {
        Method method = getMethodForValidation(propDesc);
        if (!hasAnnotation(method)) {
            return super.hasArgsAnnotation(beanDesc, propDesc);
        }

        Args args = method.getAnnotation(Args.class);
        if (args == null) {
            return false;
        }
        return (!StringUtil.isEmpty(args.keys()));
    }

    protected void registerArgs(Field field, BeanDesc beanDesc, PropertyDesc propDesc) {
        Method method = getMethodForValidation(propDesc);
        if (!hasAnnotation(method)) {
            super.registerArgs(field, beanDesc, propDesc);
            return;
        }

        Annotation annotation = method.getAnnotation(Args.class);
        if (annotation != null) {
            Map parameter = TigerAnnotationConverter.getInstance().toMap(annotation);
            executeArgsConfigRegister(field, parameter);
        }
    }

    protected boolean hasArgAnnotation(BeanDesc beanDesc, PropertyDesc propDesc) {
        Method method = getMethodForValidation(propDesc);
        if (!hasAnnotation(method)) {
            return super.hasArgsAnnotation(beanDesc, propDesc);
        }

        Args args = method.getAnnotation(Args.class);
        if (args == null) {
            return false;
        }
        return (args.args().length > 0);
    }

    protected void registerArg(Field field, BeanDesc beanDesc, PropertyDesc propDesc) {
        Method method = getMethodForValidation(propDesc);
        if (!hasAnnotation(method)) {
            super.registerArgs(field, beanDesc, propDesc);
            return;
        }

        Args args = method.getAnnotation(Args.class);
        if (args == null) {
            return;
        }

        for (Arg arg : args.args()) {
            Map parameter = TigerAnnotationConverter.getInstance().toMap(arg);
            executeArgConfigRegister(field, parameter);
        }
    }

    protected void registerConfig(Field field, BeanDesc beanDesc, PropertyDesc propDesc) {
        Method method = getMethodForValidation(propDesc);
        if (!hasAnnotation(method)) {
            super.registerConfig(field, beanDesc, propDesc);
            return;
        }

        registerAutoTypeValidatorConfig(field, propDesc);

        for (Annotation annotation : method.getAnnotations()) {
            Class<?> type = annotation.annotationType();
            ValidatorTarget target = type.getAnnotation(ValidatorTarget.class);
            if (target != null) {
                String validatorName = getValidatorName(type);
                if (hasConfigRegister(validatorName)) {
                    Map parameter = TigerAnnotationConverter.getInstance().toMap(annotation);
                    executeConfigRegister(field, validatorName, parameter);
                }
            }
        }
    }

    // -----------------------------------------------------------------------

    private boolean hasAnnotation(Method method) {
        return method.getAnnotations().length != 0;
    }

    private String createValidatorFieldDepends(ValidatorField validatorField) {
        StringBuffer result = new StringBuffer("");
        if (validatorField.validators() != null) {
            for (Validator val : validatorField.validators()) {
                result.append(val.name());
                result.append(",");
            }
        }

        if (result.length() == 0) {
            return null;
        }
        result.setLength(result.length() - 1);
        return result.toString();
    }

}
