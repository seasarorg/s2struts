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
package org.seasar.struts.lessconfig.factory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.util.MethodUtil;

/**
 * @author Katsuhiko Nagashima
 */
public abstract class AbstractAnnotationConverter implements AnnotationConverter {

    public Map toMap(Object obj) {
        Map result = new HashMap();
        Method[] methods = getAnnotationMethods(obj);
        for (int i = 0; i < methods.length; i++) {
            String name = methods[i].getName();
            Object value = MethodUtil.invoke(methods[i], obj, null);
            if (value == null) {
                continue;
            }

            if (value.getClass().isArray()) {
                List valueList = new ArrayList();
                Object[] array = (Object[]) value;
                if (array.length != 0) {
                    if (isInstanceOfAnnotation(array[0])) {
                        for (int j = 0; j < array.length; j++) {
                            Map valueMap = toMap(array[j]);
                            valueList.add(valueMap);
                        }
                    } else {
                        valueList = Arrays.asList(array);
                    }
                }
                result.put(name, valueList);
            } else if (isInstanceOfAnnotation(value)) {
                Map valueMap = toMap(value);
                result.put(name, valueMap);
            } else {
                result.put(name, value.toString());
            }
        }
        return result;
    }

    abstract protected Method[] getAnnotationMethods(Object obj);

    abstract protected boolean isInstanceOfAnnotation(Object obj);

}