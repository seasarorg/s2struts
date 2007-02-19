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
package org.seasar.struts.validator.factory;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Map;

import org.apache.commons.validator.Field;
import org.codehaus.backport175.reader.Annotation;
import org.codehaus.backport175.reader.Annotations;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.validator.annotation.backport175.Args;
import org.seasar.struts.validator.annotation.backport175.Message;
import org.seasar.struts.validator.annotation.backport175.Messages;
import org.seasar.struts.validator.annotation.backport175.NoValidate;
import org.seasar.struts.validator.annotation.backport175.Validator;
import org.seasar.struts.validator.annotation.backport175.ValidatorField;
import org.seasar.struts.validator.annotation.backport175.ValidatorTarget;

/**
 * @author Katsuhiko Nagashima
 */
public class Backport175ValidatorAnnotationHandler extends ConstantValidatorAnnotationHandler {

    protected Comparator getPropertyDescComparator(BeanDesc beanDesc) {
        return new Backport175PropertyDescComparator(beanDesc);
    }

    protected boolean noValidate(BeanDesc beanDesc, PropertyDesc propDesc) {
        Method method = getMethodForValidation(propDesc);
        if (!hasAnnotation(method)) {
            return super.noValidate(beanDesc, propDesc);
        }

        return Annotations.getAnnotation(NoValidate.class, method) != null;
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

        Annotation[] annotations = Annotations.getAnnotations(method);
        for (int i = 0; i < annotations.length; i++) {
            Class type = annotations[i].annotationType();
            Annotation target = Annotations.getAnnotation(ValidatorTarget.class, type);
            if (target != null) {
                String depend;
                if (annotations[i] instanceof ValidatorField) {
                    depend = createValidatorFieldDepends((ValidatorField) annotations[i]);
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

        Annotation annotation = Annotations.getAnnotation(Message.class, method);
        if (annotation != null) {
            Map parameter = Backport175AnnotationConverter.getInstance().toMap(annotation);
            executeMessageConfigRegister(field, parameter);
        }

        Messages messages = (Messages) Annotations.getAnnotation(Messages.class, method);
        if (messages == null) {
            return;
        }

        for (int i = 0; i < messages.value().length; i++) {
            Map parameter = Backport175AnnotationConverter.getInstance().toMap(messages.value()[i]);
            executeMessageConfigRegister(field, parameter);
        }
    }

    protected boolean hasArgsAnnotation(BeanDesc beanDesc, PropertyDesc propDesc) {
        Method method = getMethodForValidation(propDesc);
        if (!hasAnnotation(method)) {
            return super.hasArgsAnnotation(beanDesc, propDesc);
        }

        Args args = (Args) Annotations.getAnnotation(Args.class, method);
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

        Map parameter = null;
        Annotation annotation = Annotations.getAnnotation(Args.class, method);
        if (annotation != null) {
            parameter = Backport175AnnotationConverter.getInstance().toMap(annotation);
            executeArgsConfigRegister(field, parameter);
        }
    }

    protected boolean hasArgAnnotation(BeanDesc beanDesc, PropertyDesc propDesc) {
        Method method = getMethodForValidation(propDesc);
        if (!hasAnnotation(method)) {
            return super.hasArgsAnnotation(beanDesc, propDesc);
        }

        Args args = (Args) Annotations.getAnnotation(Args.class, method);
        if (args == null) {
            return false;
        }
        return (args.value().length > 0);
    }

    protected void registerArg(Field field, BeanDesc beanDesc, PropertyDesc propDesc) {
        Method method = getMethodForValidation(propDesc);
        if (!hasAnnotation(method)) {
            super.registerArgs(field, beanDesc, propDesc);
            return;
        }

        Args args = (Args) Annotations.getAnnotation(Args.class, method);
        if (args == null) {
            return;
        }

        for (int i = 0; i < args.value().length; i++) {
            Map parameter = Backport175AnnotationConverter.getInstance().toMap(args.value()[i]);
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

        Annotation[] annotations = Annotations.getAnnotations(method);
        for (int i = 0; i < annotations.length; i++) {
            Class type = annotations[i].annotationType();
            Annotation target = Annotations.getAnnotation(ValidatorTarget.class, type);
            if (target != null) {
                String validatorName = getValidatorName(type);
                if (hasConfigRegister(validatorName)) {
                    Map parameter = Backport175AnnotationConverter.getInstance().toMap(annotations[i]);
                    executeConfigRegister(field, validatorName, parameter);
                }
            }
        }
    }

    // -----------------------------------------------------------------------

    private boolean hasAnnotation(Method method) {
        return Annotations.getAnnotations(method).length != 0;
    }

    private String createValidatorFieldDepends(ValidatorField validatorField) {
        StringBuffer result = new StringBuffer("");
        if (validatorField.validators() != null) {
            for (int i = 0; i < validatorField.validators().length; i++) {
                Validator val = validatorField.validators()[i];
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
