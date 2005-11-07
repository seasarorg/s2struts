package org.seasar.struts.util;

import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.util.StringUtil;

/**
 * @author Katsuhiko Nagashima
 */
public class ConstantValueUtil {

    private ConstantValueUtil() {
    }

    public static Map toMap(String parameters) {
        return toMap(parameters, null);
    }

    public static Map toMap(String parameters, String defaultKey) {
        Map result = new HashMap();
        if (parameters == null) {
            return result;
        }

        String[] keyValues = StringUtil.split(parameters, ",");
        for (int i = 0; i < keyValues.length; i++) {
            String[] keyValue = StringUtil.split(keyValues[i], "=");
            if (keyValue.length == 1) {
                if (defaultKey == null) {
                    throw new IllegalArgumentException(parameters);
                } else {
                    result.put(defaultKey, keyValue[0].trim());
                }
            } else if (keyValue.length == 2) {
                result.put(keyValue[0].trim(), keyValue[1].trim());
            } else {
                throw new IllegalArgumentException(parameters);
            }
        }

        return result;
    }

}
