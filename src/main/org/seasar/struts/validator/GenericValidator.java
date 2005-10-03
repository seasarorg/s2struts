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
