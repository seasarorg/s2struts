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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.config.rule.CommonNamingRule;
import org.seasar.struts.validator.config.ConfigRegister;

/**
 * @author Katsuhiko Nagashima
 */
public abstract class AbstractValidatorAnnotationHandler implements ValidatorAnnotationHandler {

    private static final String VALIDATOR_TYPE_PREFIX_RE = "Type$";

    public Form createForm(String formName, Class formClass) {
        Form form = new Form();
        form.setName(formName);

        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(formClass);
        for (int i = 0; i < beanDesc.getPropertyDescSize(); i++) {
            PropertyDesc propDesc = beanDesc.getPropertyDesc(i);
            Field field = createField(form, beanDesc, propDesc);
            if (field != null) {
                form.addField(field);
            }
        }
        return form;
    }

    protected abstract Field createField(Form form, BeanDesc beanDesc, PropertyDesc propDesc);

    protected String getAutoTypeValidatorName(Method method) {
        Class[] classes = method.getParameterTypes();
        Class paramType = classes[classes.length - 1];

        if (paramType.equals(Byte.class) || paramType.equals(Byte.TYPE)) {
            return "byte";
        } else if (paramType.equals(Date.class) || paramType.equals(Timestamp.class)) {
            return "date";
        } else if (paramType.equals(Double.class) || paramType.equals(Double.TYPE)) {
            return "double";
        } else if (paramType.equals(Float.class) || paramType.equals(Float.TYPE)) {
            return "float";
        } else if (paramType.equals(Integer.class) || paramType.equals(Integer.TYPE)) {
            return "integer";
        } else if (paramType.equals(Long.class) || paramType.equals(Long.TYPE)) {
            return "long";
        } else if (paramType.equals(Short.class) || paramType.equals(Short.TYPE)) {
            return "short";
        }

        return null;
    }

    protected void registAutoTypeValidatorConfig(Field field, Method method) {
        String autoTypeValidatorName = getAutoTypeValidatorName(method);
        if (StringUtil.isEmpty(autoTypeValidatorName)) {
            return;
        }

        if (hasConfigRegister(autoTypeValidatorName)) {
            executeConfigRegister(field, autoTypeValidatorName, new HashMap());
        }
    }

    protected boolean hasConfigRegister(String validatorName) {
        String registerName = getConfigRegisterName(validatorName);
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return container.hasComponentDef(registerName);
    }

    protected void executeConfigRegister(Field field, String validatorName, Map parameters) {
        String registerName = getConfigRegisterName(validatorName);
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ConfigRegister register = (ConfigRegister) container.getComponent(registerName);
        register.regist(field, parameters);
    }

    protected String getValidatorName(Class clazz) {
        String validatorName = CommonNamingRule.decapitalizeName(ClassUtil.getShortClassName(clazz));
        return validatorName.replaceFirst(VALIDATOR_TYPE_PREFIX_RE, "");
    }

    protected String[] toArrays(String str) {
        StringTokenizer tokenizer = new StringTokenizer(str, ",");
        List list = new ArrayList();
        while (tokenizer.hasMoreElements()) {
            list.add(tokenizer.nextToken().trim());
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    private String getConfigRegisterName(String validatorName) {
        return validatorName + "ConfigRegister";
    }

}
