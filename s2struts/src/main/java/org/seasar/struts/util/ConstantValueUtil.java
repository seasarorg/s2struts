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
package org.seasar.struts.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Katsuhiko Nagashima
 */
public class ConstantValueUtil {

    private static final char[] ENCLOSE_CHAR = { '\'', '"' };

    private static final char SEP_ITEM = ',';

    private static final char SEP_KEYVALUE = '=';

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

        String key = "";
        String value = "";
        String next = parameters.trim();
        while (next.length() != 0) {
            if (!isNextValue(next)) {
                key = getKey(next);
                next = skipAndTrim(next, key.length() + 1);
            } else {
                key = "";
            }
            value = getValue(next);
            next = skipAndTrim(next, value.length() + 1);

            if (key.length() != 0) {
                result.put(key.trim(), convertValue(value.trim()));
            } else {
                if (defaultKey == null) {
                    throw new IllegalArgumentException(parameters);
                } else {
                    result.put(defaultKey, convertValue(value.trim()));
                }
            }
        }

        return result;
    }

    private static String getKey(String parameter) {
        int pos = parameter.indexOf(SEP_KEYVALUE);
        return parameter.substring(0, pos);
    }

    private static String getValue(String parameter) {
        // When last value is empty.
        if (parameter.length() == 0) {
            return "";
        }

        for (int i = 0; i < ENCLOSE_CHAR.length; i++) {
            if (parameter.charAt(0) == ENCLOSE_CHAR[i]) {
                return getEnclosedValue(parameter, ENCLOSE_CHAR[i]);
            }
        }

        int pos = parameter.indexOf(SEP_ITEM);
        if (pos >= 0) {
            return parameter.substring(0, pos);
        } else {
            return parameter;
        }
    }

    private static String getEnclosedValue(String parameter, char encloseChar) {
        int fromPos = parameter.indexOf(encloseChar, 1);
        if (fromPos < 0) {
            throw new IllegalArgumentException("Not closed value. " + parameter);
        }

        String result;
        int pos = parameter.indexOf(SEP_ITEM, fromPos);
        if (pos >= 0) {
            result = parameter.substring(0, pos);
        } else {
            result = parameter;
        }
        if (result.trim().charAt(result.trim().length() - 1) != encloseChar) {
            throw new IllegalArgumentException("Illegal separator value. " + parameter);
        }
        return result;
    }

    private static String skipAndTrim(String parameter, int count) {
        if (parameter.length() > count) {
            return parameter.substring(count).trim();
        } else {
            return "";
        }
    }

    private static boolean isNextValue(String parameter) {
        for (int i = 0; i < ENCLOSE_CHAR.length; i++) {
            if (parameter.charAt(0) == ENCLOSE_CHAR[i]) {
                return true;
            }
        }

        int itemPos = parameter.indexOf(SEP_ITEM);
        int keyValuePos = parameter.indexOf(SEP_KEYVALUE);
        if (itemPos < 0 && keyValuePos < 0) {
            // last value.
            return true;
        }
        if (itemPos < 0) {
            // last key.
            return false;
        }
        return itemPos < keyValuePos;
    }

    private static String convertValue(String value) {
        if (value.length() == 0) {
            return value;
        }

        for (int i = 0; i < ENCLOSE_CHAR.length; i++) {
            if (value.charAt(0) == ENCLOSE_CHAR[i]) {
                // The enclosed value is not replaced('\n' -> ',').
                return value.substring(1, value.length() - 1);
            }
        }
        return value.replace('\n', ',');
    }

}