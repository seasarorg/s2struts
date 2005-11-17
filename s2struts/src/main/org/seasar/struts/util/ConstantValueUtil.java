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