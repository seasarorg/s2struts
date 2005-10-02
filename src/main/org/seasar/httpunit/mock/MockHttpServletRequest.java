package org.seasar.httpunit.mock;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
/**
 * @author Satoshi Kimura
 */
public interface MockHttpServletRequest extends HttpServletRequest {
    void addParameter(String name, String value);

    void addParameter(String name, String[] values);

    void addCookie(Cookie cookie);

    void addHeader(String name, String value);
}