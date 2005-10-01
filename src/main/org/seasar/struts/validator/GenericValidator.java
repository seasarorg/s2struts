package org.seasar.struts.validator;

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
    public static boolean minByteLength(String value, int min) {
        return (value.getBytes().length >= min);
    }

    /**
     * Checks if the value's length of byte is less than or equal to the max.
     * 
     * @param value The value validation is being performed on.
     * @param max The maximum length.
     */
    public static boolean maxByteLength(String value, int max) {
        return (value.getBytes().length <= max);
    }

}
