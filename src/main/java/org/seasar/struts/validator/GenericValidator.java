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
package org.seasar.struts.validator;

import java.io.UnsupportedEncodingException;

import org.seasar.framework.util.StringUtil;

/**
 * @author Satoshi Kimura
 */
public class GenericValidator extends org.apache.commons.validator.GenericValidator {

    /**
     * Checks if the value's length of byte is greater than or equal to the min.
     * 
     * @param value The value validation is being performed on.
     * @param min The minimum length.
     */
    public static boolean minByteLength(String value, int min, String charset) {
        return (getBytes(value, charset).length >= min);
    }

    /**
     * Checks if the value's length of byte is less than or equal to the max.
     * 
     * @param value The value validation is being performed on.
     * @param max The maximum length.
     */
    public static boolean maxByteLength(String value, int max, String charset) {
        return (getBytes(value, charset).length <= max);
    }
    
    private static byte[] getBytes(String str, String charset) {
        if (StringUtil.isEmpty(charset)) {
            return str.getBytes();
        } else {
            try {
                return str.getBytes(charset);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
