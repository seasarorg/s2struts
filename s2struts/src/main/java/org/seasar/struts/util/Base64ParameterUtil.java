package org.seasar.struts.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.seasar.framework.log.Logger;
import org.seasar.framework.util.Base64Util;
import org.seasar.framework.util.URLUtil;
import org.seasar.struts.Constants;

/**
 * Base64でフォーマットするパラメタを扱うユーティリティクラスです。
 * 
 * @author taedium
 */
public class Base64ParameterUtil {

    protected static Logger logger = Logger.getLogger(Base64ParameterUtil.class);

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
            String key = (String) e.getKey();
            String value = (String) e.getValue();
            String urlEncoded = URLUtil.encode(value, "UTF-8");
            logger.log("DSTR0002", new Object[] { key, value, urlEncoded });
            buf.append(key);
            buf.append('=');
            buf.append(urlEncoded);
            buf.append('&');
        }
        if (buf.length() > 1) {
            buf.setLength(buf.length() - 1);
        }
        String value = buf.toString();
        String base64Encoded = Base64Util.encode(value.getBytes());
        logger.log("DSTR0004", new Object[] { value, base64Encoded });
        return base64Encoded;
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
            String base64Decoded = new String(Base64Util.decode(s));
            logger.log("DSTR0005", new Object[] { s, base64Decoded });
            if (base64Decoded.startsWith(Constants.BASE64_FORMAT_PREFIX)) {
                String value = base64Decoded.substring(Constants.BASE64_FORMAT_PREFIX.length());
                String[] pairs = value.split("&");
                for (int i = 0; i < pairs.length; i++) {
                    String[] pair = pairs[i].split("=");
                    if (pair.length == 2) {
                        String key = pair[0];
                        String urlEncoded = pair[1];
                        String urlDecoded = null;
                        try {
                            urlDecoded = URLUtil.decode(urlEncoded, "UTF-8");
                        } catch (Exception e) {
                            throw new URLDecodeFailedRuntimeException(key, urlEncoded, e);
                        }
                        logger.log("DSTR0003", new Object[] { key, urlEncoded, urlDecoded });
                        params.put(key, urlDecoded);
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
