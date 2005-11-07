package org.seasar.struts.validator.factory;

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
                result.put(name, value);
            }
        }
        return result;
    }

    abstract protected Method[] getAnnotationMethods(Object obj);

    abstract protected boolean isInstanceOfAnnotation(Object obj);

}