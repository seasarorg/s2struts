package org.seasar.struts.validator.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.Msg;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.validator.annotation.Args;
import org.seasar.struts.validator.annotation.Message;
import org.seasar.struts.validator.annotation.NoValidate;
import org.seasar.struts.validator.annotation.Validator;
import org.seasar.struts.validator.annotation.ValidatorField;
import org.seasar.struts.validator.annotation.ValidatorTarget;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class TigerValidatorAnnotationHandler extends ConstantValidatorAnnotationHandler {

    protected Field createField(Form form, BeanDesc beanDesc, PropertyDesc propDesc) {
        if (!propDesc.hasWriteMethod()) {
            return null;
        }

        String propName = propDesc.getPropertyName();
        if (form.getField(propName) != null) {
            // registed
            return null;
        }

        Method method = propDesc.getWriteMethod();
        if (!hasAnnotation(method)) {
            return super.createField(form, beanDesc, propDesc);
        }

        NoValidate noValidate = method.getAnnotation(NoValidate.class);
        if (noValidate != null) {
            return null;
        }

        String depends = createDepends(method);
        if (depends == null) {
            return null;
        }

        Field field = new Field();
        addMessage(field, method);
        addArgs(field, method);
        field.setDepends(depends);
        String propertyName = propDesc.getPropertyName();
        field.setProperty(propertyName);
        registConfig(field, method);

        return field;
    }

    private boolean hasAnnotation(Method method) {
        Annotation[] annotations = method.getAnnotations();
        return (annotations.length != 0);
    }

    private void registConfig(Field field, Method method) {
        registAutoTypeValidatorConfig(field, method);

        for (Annotation annotation : method.getAnnotations()) {
            Class<?> type = annotation.annotationType();
            ValidatorTarget commonValidator = type
                    .getAnnotation(ValidatorTarget.class);
            if (commonValidator != null) {
                String validatorName = getValidatorName(type);
                if (hasConfigRegister(validatorName)) {
                    Map parameter = TigerAnnotationConverter.getInstance().toMap(annotation);
                    executeConfigRegister(field, validatorName, parameter);
                }
            }
        }
    }

    private String createDepends(Method method) {
        StringBuffer depends = new StringBuffer("");

        String autoTypeValidatorName = getAutoTypeValidatorName(method);
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

    private void addArgs(Field field, Method method) {
        Annotation annotation = method.getAnnotation(Args.class);
        String[] keys = { method.getName() };
        boolean resource = false;
        if (annotation != null) {
            Args args = (Args) annotation;
            keys = toArrays(args.keys());
            resource = args.resource();
        }
        for (int i = 0; i < keys.length; i++) {
            Arg arg = new Arg();
            arg.setKey(keys[i]);
            arg.setResource(resource);
            arg.setPosition(i);
            field.addArg(arg);
        }
    }

    private void addMessage(Field field, Method method) {
        Annotation annotation = method.getAnnotation(Message.class);
        if (annotation != null) {
            Message message = (Message) annotation;
            Msg msg = new Msg();
            msg.setBundle(message.bundle());
            msg.setKey(message.key());
            msg.setName(message.name());
            msg.setResource(message.resource());
            field.addMsg(msg);
        }
    }

}
