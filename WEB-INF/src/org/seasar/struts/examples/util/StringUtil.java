package org.seasar.struts.examples.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Katsuhiko Nagashima
 */
public class StringUtil {
    
    private StringUtil() {
    }
    
    public static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }
    
    public static String toNumericString(String value) {
        if (isEmpty(value)) {
            return null;
        }
        return value;
    }

    public static String toString(Date date) {
        SimpleDateFormat format = DateUtil.getDefaultDateFormat();
        try {
            return format.format(date);
        } catch (RuntimeException e) {
            return "";
        }
    }

}
