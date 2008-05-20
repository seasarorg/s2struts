package org.seasar.struts.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.seasar.framework.util.Base64Util;
import org.seasar.framework.util.URLUtil;
import org.seasar.struts.Constants;

/**
 * Base64でフォーマットするパラメタを扱うユーティリティクラスです。
 * 
 * @author taedium
 */
public class Base64ParameterUtil {

    protected static int PREFIX_LENGTH = Base64Util.encode(Constants.BASE64_FORMAT_PREFIX.getBytes()).length();

    private Base64ParameterUtil() {
    }

    /**
     * キーと値のマップをBase64で単一の文字列にエンコードします。
     * 
     * @param params
     *            キーと値のマップ
     * @return エンコードされた文字列
     */
    public static String encode(Map params) {
        if (params.isEmpty()) {
            return "";
        }
        StringBuffer buf = new StringBuffer(100);
        buf.append(Constants.BASE64_FORMAT_PREFIX);
        for (Iterator it = params.entrySet().iterator(); it.hasNext();) {
            Map.Entry e = (Map.Entry) it.next();
            buf.append(e.getKey());
            buf.append('=');
            buf.append(URLUtil.encode((String) e.getValue(), "UTF-8"));
            buf.append('&');
        }
        if (buf.length() > 1) {
            buf.setLength(buf.length() - 1);
        }
        return Base64Util.encode(buf.toString().getBytes());
    }

    /**
     * {@link #encode(Map)}でエンコードされた文字列をキーと値のマップにデコードします。
     * 
     * @param s
     *            Base64でエンコードされた文字列
     * @return キーと値のマップ
     */
    public static Map decode(String s) {
        Map params = new HashMap();
        if (s != null && s.length() >= PREFIX_LENGTH) {
            String decoded = new String(Base64Util.decode(s));
            if (decoded.startsWith(Constants.BASE64_FORMAT_PREFIX)) {
                String value = decoded.substring(Constants.BASE64_FORMAT_PREFIX.length());
                String[] pairs = value.split("&");
                for (int i = 0; i < pairs.length; i++) {
                    String[] pair = pairs[i].split("=");
                    if (pair.length == 2) {
                        params.put(pair[0], URLUtil.decode(pair[1], "UTF-8"));
                    } else if (pair.length == 1) {
                        params.put(pair[0], "");
                    } else {
                        throw new IllegalArgumentException(s);
                    }
                }
            }
        }
        return params;
    }
}
