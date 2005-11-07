package org.seasar.struts.examples.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Katsuhiko Nagashima
 */
public class DateUtil {

    private DateUtil() {
    }

    public static SimpleDateFormat getDefaultDateFormat() {
        return new SimpleDateFormat("yyyy/MM/dd");
    }

    public static Date toDate(String str) {
        SimpleDateFormat format;
        format = getDefaultDateFormat();

        try {
            return format.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

}