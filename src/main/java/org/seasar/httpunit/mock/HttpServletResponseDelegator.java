package org.seasar.httpunit.mock;

/**
 * @author Satoshi Kimura
 */
public class HttpServletResponseDelegator {
    /**
     * @see javax.servlet.http.HttpServletResponse#encodeRedirectURL(java.lang.String)
     */
    public String encodeRedirectURL(String url) {
        return null; //new CoyoteResponse().encodeRedirectURL(url);
    }

    /**
     * @see javax.servlet.http.HttpServletResponse#encodeRedirectUrl(java.lang.String)
     * @deprecated
     */
    public String encodeRedirectUrl(String url) {
        return encodeRedirectURL(url);
    }

    /**
     * @see javax.servlet.http.HttpServletResponse#encodeURL(java.lang.String)
     */
    public String encodeURL(String url) {
        return null; //new CoyoteResponse().encodeURL(url);
    }

    /**
     * @see javax.servlet.http.HttpServletResponse#encodeUrl(java.lang.String)
     * @deprecated
     */
    public String encodeUrl(String url) {
        return encodeURL(url);
    }
}