package org.seasar.struts.validator.factory;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.Msg;
import org.codehaus.backport175.reader.Annotation;
import org.codehaus.backport175.reader.Annotations;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.ClassUtil;
import org.seasar.struts.config.rule.CommonNamingRule;
import org.seasar.struts.validator.annotation.Args;
import org.seasar.struts.validator.annotation.CommonValidator;
import org.seasar.struts.validator.annotation.Message;
import org.seasar.struts.validator.annotation.NoValidate;
import org.seasar.struts.validator.annotation.Validator;
import org.seasar.struts.validator.annotation.ValidatorField;
import org.seasar.struts.validator.config.ConfigRegister;

/**
 * @author Katsuhiko Nagashima
 */
public class Backport175ValidatorAnnotationHandler implements ValidatorAnnotationHandler {

    private Class DUMMY_CLASS = DummyClass.class;

    private static final String VALIDATOR_TYPE_PREFIX_REGEX = "Type$";
    
    public Form createForm(String formName, Class formClass) {
        Form form = new Form();
        form.setName(formName);

        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(formClass);
        for (int i = 0; i < beanDesc.getPropertyDescSize(); i++) {
            PropertyDesc propDesc = beanDesc.getPropertyDesc(i);
            Field field = createField(propDesc, form);
            if (field != null) {
                form.addField(field);
            }
        }
        return form;
    }

    private Field createField(PropertyDesc propDesc, Form form) {
        if (!propDesc.hasWriteMethod()) {
            return null;
        }

        String propName = propDesc.getPropertyName();
        if (form.getField(propName) != null) {
            // registed
            return null;
        }

        Method method = propDesc.getWriteMethod();
        
        Annotation noValidate = Annotations.getAnnotation(NoValidate.class, method);
        if (noValidate != null) {
            return null;
        }

        Annotation[] annotations = Annotations.getAnnotations(method);
        annotations = addTypeValidation(annotations, method);
        if (annotations == null) {
            return null;
        }

        String depends = createDepends(annotations);
        if (depends == null) {
            return null;
        }

        Field field = new Field();
        addMessage(field, method);
        addArgs(field, method);
        field.setDepends(depends);
        String propertyName = propDesc.getPropertyName();
        field.setProperty(propertyName);
        registConfig(annotations, field, method);

        return field;
    }

    private Annotation[] addTypeValidation(Annotation[] annotations, Method method) {
        if (annotations == null) {
            return null;
        }

        Class[] classes = method.getParameterTypes();
        Class paramType = classes[classes.length - 1];
        List list = new ArrayList();
        list.addAll(Arrays.asList(annotations));

        if (paramType.equals(Byte.class) || paramType.equals(Byte.TYPE)) {
            list.add(0, Annotations.getAnnotation(org.seasar.struts.validator.annotation.ByteType.class, DUMMY_CLASS));
        } else if (paramType.equals(Date.class) || paramType.equals(Timestamp.class)) {
            list.add(0, Annotations.getAnnotation(org.seasar.struts.validator.annotation.DateType.class, DUMMY_CLASS));
        } else if (paramType.equals(Double.class) || paramType.equals(Double.TYPE)) {
            list.add(0, Annotations.getAnnotation(org.seasar.struts.validator.annotation.DoubleType.class, DUMMY_CLASS));
        } else if (paramType.equals(Float.class) || paramType.equals(Float.TYPE)) {
            list.add(0, Annotations.getAnnotation(org.seasar.struts.validator.annotation.FloatType.class, DUMMY_CLASS));
        } else if (paramType.equals(Integer.class) || paramType.equals(Integer.TYPE)) {
            list.add(0, Annotations.getAnnotation(org.seasar.struts.validator.annotation.IntegerType.class, DUMMY_CLASS));
        } else if (paramType.equals(Long.class) || paramType.equals(Long.TYPE)) {
            list.add(0, Annotations.getAnnotation(org.seasar.struts.validator.annotation.LongType.class, DUMMY_CLASS));
        } else if (paramType.equals(Short.class) || paramType.equals(Short.TYPE)) {
            list.add(0, Annotations.getAnnotation(org.seasar.struts.validator.annotation.ShortType.class, DUMMY_CLASS));
        }

        if (list.size() == 0) {
            return null;
        }

        return (Annotation[]) list.toArray(new Annotation[list.size()]);
    }

    private void registConfig(Annotation[] annotations, Field field, Method method) {
        for (int i = 0; i < annotations.length; i++) {
            Class type = annotations[i].annotationType();
            if (CommonValidator.class.isAssignableFrom(type)) {
                String shortClassName = ClassUtil.getShortClassName(type);
                String registerName = CommonNamingRule.decapitalizeName(shortClassName) + "ConfigRegister";
                S2Container container = SingletonS2ContainerFactory.getContainer();
                if (container.hasComponentDef(registerName)) {
                    ConfigRegister register = (ConfigRegister) container.getComponent(registerName);
                    register.regist(field, method);
                }
            }
        }
    }

    private String createDepends(Annotation[] annotations) {
        StringBuffer depends = new StringBuffer("");
        for (int i = 0; i < annotations.length; i++) {
            Class type = annotations[i].annotationType();
            if (CommonValidator.class.isAssignableFrom(type)) {
                CommonValidator validator = (CommonValidator) annotations[i];
                String depend = validator.type();
                if (validator instanceof ValidatorField) {
                    depend = createValidatorFieldDepends((ValidatorField) validator);
                } else {
                    if (depend == null) {
                        String validatorName = CommonNamingRule.decapitalizeName(ClassUtil.getShortClassName(type));
                        depend = validatorName.replaceFirst(VALIDATOR_TYPE_PREFIX_REGEX, "");
                    }
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

    private String[] toArrays(String str) {
        StringTokenizer tokenizer = new StringTokenizer(str, ",");
        List list = new ArrayList();
        while (tokenizer.hasMoreElements()) {
            list.add(tokenizer.nextToken().trim());
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * @org.seasar.struts.validator.annotation.ByteType
     * @org.seasar.struts.validator.annotation.DateType
     * @org.seasar.struts.validator.annotation.DoubleType
     * @org.seasar.struts.validator.annotation.FloatType
     * @org.seasar.struts.validator.annotation.IntegerType
     * @org.seasar.struts.validator.annotation.LongType
     * @org.seasar.struts.validator.annotation.ShortType
     */
    private static class DummyClass {

    }
    
}
