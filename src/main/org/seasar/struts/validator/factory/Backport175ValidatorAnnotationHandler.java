package org.seasar.struts.validator.factory;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.Msg;
import org.codehaus.backport175.reader.Annotation;
import org.codehaus.backport175.reader.Annotations;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.validator.annotation.backport175.Args;
import org.seasar.struts.validator.annotation.backport175.Message;
import org.seasar.struts.validator.annotation.backport175.NoValidate;
import org.seasar.struts.validator.annotation.backport175.Validator;
import org.seasar.struts.validator.annotation.backport175.ValidatorField;
import org.seasar.struts.validator.annotation.backport175.ValidatorTarget;

/**
 * @author Katsuhiko Nagashima
 */
public class Backport175ValidatorAnnotationHandler extends ConstantValidatorAnnotationHandler {

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
        
        Annotation noValidate = Annotations.getAnnotation(NoValidate.class, method);
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
        field.setProperty(propDesc.getPropertyName());
        registConfig(field, method);

        return field;
    }
    
    private boolean hasAnnotation(Method method) {
        Annotation[] annotations = Annotations.getAnnotations(method);
        return (annotations.length != 0);
    }

    private void registConfig(Field field, Method method) {
        registAutoTypeValidatorConfig(field, method);
        
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
    
    private String createDepends(Method method) {
        StringBuffer depends = new StringBuffer("");
        
        String autoTypeValidatorName = getAutoTypeValidatorName(method);
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

    private void addArgs(Field field, Method method) {
        Annotation annotation = Annotations.getAnnotation(Args.class, method);
        String[] keys = {method.getName()};
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
        Annotation annotation = Annotations.getAnnotation(Message.class, method);
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
