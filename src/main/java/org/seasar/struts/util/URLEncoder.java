package org.seasar.struts.util;

import java.io.UnsupportedEncodingException;

import org.seasar.framework.exception.IORuntimeException;

/**
 * @author Satsohi Kimura
 */
public abstract class URLEncoder {

    public static String encode(String url) {
        return encode(url, "UTF-8");
    }

    public static String encode(String url, String enc) {
        if (url == null) {
            return null;
        }
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < url.length(); i++) {
            char c = url.charAt(i);
            if (String.valueOf(c).getBytes().length != 1) {
                try {
                    buff.append(java.net.URLEncoder.encode(String.valueOf(c), enc));
                } catch (UnsupportedEncodingException e) {
                    throw new IORuntimeException(e);
                }
            } else {
                buff.append(c);
            }
        }
        return buff.toString();
    }

}
