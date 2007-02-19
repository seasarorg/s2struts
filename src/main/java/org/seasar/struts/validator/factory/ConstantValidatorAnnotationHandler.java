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
package org.seasar.struts.validator.factory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.Field;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.util.ConstantAnnotationUtil;
import org.seasar.struts.util.ConstantValueUtil;

/**
 * @author Katsuhiko Nagashima
 */
public class ConstantValidatorAnnotationHandler extends AbstractValidatorAnnotationHandler {

    private static final String VALIDATOR_SUFFIX = "_VALIDATOR";

    private static final String ARGS_SUFFIX = "_VALIDATOR_ARGS";

    private static final String ARG_SUFFIX = "_VALIDATOR_ARG";

    private static final String MESSAGE_SUFFIX = "_VALIDATOR_MESSAGE";

    private static final String VALIDATOR_DEFAULT_KEY = "type";

    private static final String ARGS_DEFAULT_KEY = "keys";

    private static final String ARG_DEFAULT_KEY = "key";

    private static final String MESSAGE_DEFAULT_KEY = null;

    private static final String NO_VALIDATE = "noValidate";

    protected Comparator getPropertyDescComparator(BeanDesc beanDesc) {
        return new ConstantPropertyDescComparator(beanDesc);
    }

    protected boolean noValidate(BeanDesc beanDesc, PropertyDesc propDesc) {
        List parameters = getValidatorParameters(beanDesc, propDesc);
        for (Iterator it = parameters.iterator(); it.hasNext();) {
            Map parameter = (Map) it.next();
            String type = (String) parameter.get(VALIDATOR_DEFAULT_KEY);
            if (NO_VALIDATE.equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    protected String getDepends(BeanDesc beanDesc, PropertyDesc propDesc) {
        List parameters = getValidatorParameters(beanDesc, propDesc);
        StringBuffer depends = new StringBuffer("");

        String autoTypeValidatorName = getAutoTypeValidatorName(propDesc);
        if (!StringUtil.isEmpty(autoTypeValidatorName)) {
            depends.append(autoTypeValidatorName).append(",");
        }

        for (Iterator it = parameters.iterator(); it.hasNext();) {
            Map parameter = (Map) it.next();
            depends.append(parameter.get(VALIDATOR_DEFAULT_KEY)).append(",");
        }
        if (depends.length() < 1) {
            return null;
        }
        depends.setLength(depends.length() - 1);
        return depends.toString();
    }

    protected void registerMessage(Field field, BeanDesc beanDesc, PropertyDesc propDesc) {
        List parameters = getMessageParameters(beanDesc, propDesc);
        for (Iterator it = parameters.iterator(); it.hasNext();) {
            Map parameter = (Map) it.next();
            executeMessageConfigRegister(field, parameter);
        }
    }

    protected boolean hasArgsAnnotation(BeanDesc beanDesc, PropertyDesc propDesc) {
        String fieldName = propDesc.getPropertyName() + ARGS_SUFFIX;
        return hasAnnotation(beanDesc, fieldName);
    }

    protected void registerArgs(Field field, BeanDesc beanDesc, PropertyDesc propDesc) {
        Map parameter = null;
        String fieldName = propDesc.getPropertyName() + ARGS_SUFFIX;
        if (hasAnnotation(beanDesc, fieldName)) {
            String value = (String) beanDesc.getFieldValue(fieldName, null);
            parameter = ConstantValueUtil.toMap(value, ARGS_DEFAULT_KEY);

            executeArgsConfigRegister(field, parameter);
        }
    }

    protected boolean hasArgAnnotation(BeanDesc beanDesc, PropertyDesc propDesc) {
        String fieldName = propDesc.getPropertyName() + ARG_SUFFIX;
        if (hasAnnotation(beanDesc, fieldName)) {
            return true;
        }

        return hasIndexedAnnotation(beanDesc, fieldName, 0);
    }

    protected void registerArg(Field field, BeanDesc beanDesc, PropertyDesc propDesc) {
        List parameters = getArgParameters(beanDesc, propDesc);
        for (Iterator it = parameters.iterator(); it.hasNext();) {
            Map parameter = (Map) it.next();
            executeArgConfigRegister(field, parameter);
        }
    }

    protected void registerConfig(Field field, BeanDesc beanDesc, PropertyDesc propDesc) {
        registerAutoTypeValidatorConfig(field, propDesc);

        List parameters = getValidatorParameters(beanDesc, propDesc);
        for (Iterator it = parameters.iterator(); it.hasNext();) {
            Map parameter = (Map) it.next();
            String type = (String) parameter.get(VALIDATOR_DEFAULT_KEY);
            if (hasConfigRegister(type)) {
                executeConfigRegister(field, type, parameter);
            }
        }
    }

    // -----------------------------------------------------------------------

    private List getValidatorParameters(BeanDesc beanDesc, PropertyDesc propDesc) {
        List result = new ArrayList();

        String fieldName = propDesc.getPropertyName() + VALIDATOR_SUFFIX;
        Map parameter = getParameter(beanDesc, fieldName, VALIDATOR_DEFAULT_KEY);
        if (parameter != null) {
            result.add(parameter);
        }

        for (int i = 0; hasIndexedAnnotation(beanDesc, fieldName, i); i++) {
            parameter = getIndexedParameter(beanDesc, fieldName, VALIDATOR_DEFAULT_KEY, i);
            if (parameter != null) {
                result.add(parameter);
            }
        }

        return result;
    }

    private List getArgParameters(BeanDesc beanDesc, PropertyDesc propDesc) {
        List result = new ArrayList();

        String fieldName = propDesc.getPropertyName() + ARG_SUFFIX;
        Map parameter = getParameter(beanDesc, fieldName, ARG_DEFAULT_KEY);
        if (parameter != null) {
            result.add(parameter);
        }

        for (int i = 0; hasIndexedAnnotation(beanDesc, fieldName, i); i++) {
            parameter = getIndexedParameter(beanDesc, fieldName, ARG_DEFAULT_KEY, i);
            if (parameter != null) {
                result.add(parameter);
            }
        }

        return result;
    }

    private List getMessageParameters(BeanDesc beanDesc, PropertyDesc propDesc) {
        List result = new ArrayList();

        String fieldName = propDesc.getPropertyName() + MESSAGE_SUFFIX;
        Map parameter = getParameter(beanDesc, fieldName, MESSAGE_DEFAULT_KEY);
        if (parameter != null) {
            result.add(parameter);
        }

        for (int i = 0; hasIndexedAnnotation(beanDesc, fieldName, i); i++) {
            parameter = getIndexedParameter(beanDesc, fieldName, MESSAGE_DEFAULT_KEY, i);
            if (parameter != null) {
                result.add(parameter);
            }
        }

        return result;
    }

    private boolean hasAnnotation(BeanDesc beanDesc, String fieldName) {
        if (beanDesc.hasField(fieldName)) {
            return ConstantAnnotationUtil.isConstantAnnotationStringField(beanDesc.getField(fieldName));
        }
        return false;
    }

    private Map getParameter(BeanDesc beanDesc, String fieldName, String defaultKey) {
        if (!beanDesc.hasField(fieldName)) {
            return null;
        }
        if (!ConstantAnnotationUtil.isConstantAnnotationStringField(beanDesc.getField(fieldName))) {
            return null;
        }
        String value = (String) beanDesc.getFieldValue(fieldName, null);
        return ConstantValueUtil.toMap(value, defaultKey);
    }

    private boolean hasIndexedAnnotation(BeanDesc beanDesc, String fieldName, int index) {
        return hasAnnotation(beanDesc, fieldName + "_" + Integer.toString(index));
    }

    private Map getIndexedParameter(BeanDesc beanDesc, String fieldName, String defaultKey, int index) {
        return getParameter(beanDesc, fieldName + "_" + Integer.toString(index), defaultKey);
    }

}
