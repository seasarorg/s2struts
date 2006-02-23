package org.seasar.httpunit.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.seasar.httpunit.S2HttpTestCase;

/**
 * @author Satoshi Kimura
 */
public class HttpServletRequestMockObjectTest extends S2HttpTestCase {
    private MockHttpServletRequest request;

    /**
     * @param name
     */
    public HttpServletRequestMockObjectTest(String name) {
        super(name);
    }

    protected void setUp() {
        include("HttpMockObject.dicon");
    }

    public void testGetSessionTrue() {
        HttpSession actual = request.getSession(true);
        assertNotNull(actual);
    }

    public void testGetSession() {
        HttpSession session = request.getSession(true);
        assertNotNull(session);

        session.invalidate();

        try {
            session.isNew();
            fail();
        } catch (IllegalStateException e) {
            //success
        }

        session = request.getSession();
        assertNotNull(session);

    }

    public void testGetSessionFalse() {
        HttpSession actual = request.getSession(false);
        assertNull(actual);
    }

    public void testGetAuthType() {
        assertEquals("AuthType is Foo", request.getAuthType());
    }

    public void testGetCookies() {
        assertNull(request.getCookies());

        request.addCookie(new Cookie("key1", "value1"));

        Cookie[] expected = {new Cookie("key1", "value1")};
        assertEquals(1, request.getCookies().length);
        assertEquals(expected[0].getName(), request.getCookies()[0].getName());
        assertEquals(expected[0].getValue(), request.getCookies()[0].getValue());
    }

    public void testGetDateHeader() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, 1986);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        calendar.set(Calendar.DATE, 25);
        assertEquals(calendar.getTimeInMillis(), request.getDateHeader("getDateHeader"));
    }

    public void testGetHeader() {
        request.addHeader("accept-language", "ja");

        assertEquals("ja", request.getHeader("accept-language"));
    }

    public void testGetHeaders() {
        request.addHeader("name", "val1,val2,val3");

        List expected = new ArrayList();
        expected.add("val1");
        expected.add("val2");
        expected.add("val3");
        Enumeration actual = request.getHeaders("name");
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.nextElement());
        }
        assertFalse(actual.hasMoreElements());
    }

    public void testGetHeaderNames() {
        request.addHeader("accept-language", "");
        request.addHeader("content-type", "");
        request.addHeader("accept-encoding", "");
        request.addHeader("content-length", "");
        request.addHeader("connection", "");
        request.addHeader("cache-control", "");

        List actual = toSortedList(request.getHeaderNames());
        List expected = new ArrayList();
        expected.add("accept-encoding");
        expected.add("accept-language");
        expected.add("cache-control");
        expected.add("connection");
        expected.add("content-length");
        expected.add("content-type");

        assertEquals(expected, actual);
    }

    public void testGetIntHeader() {
        request.addHeader("getIntHeader", "1");

        assertEquals(1, request.getIntHeader("getIntHeader"));
        assertEquals(-1, request.getIntHeader("getIntHeaderNull"));
    }

    public void testGetMethod() {
        assertEquals("POST", request.getMethod());
    }

    public void testGetContextPath() {
        setWebappsName("webappsName");
        setPath("/path?a=b&c=d&e");

        assertEquals("/webappsName", request.getContextPath());
    }

    public void testGetQueryString() {
        setPath("/path?a=b&c=d&e");

        assertEquals("a=b&c=d&e", request.getQueryString());
    }

    public void testGetRemoteUser() {
        assertEquals("seasar", request.getRemoteUser());
    }

    public void testIsUserInRole() {
        assertFalse(request.isUserInRole("seaser"));
        assertTrue(request.isUserInRole("seasar"));
    }

    public void testGetUserPrincipal() {
        assertEquals("seasar", request.getUserPrincipal().getName());
    }

    public void testGetRequestedSessionId() {
        assertEquals("RequestedSessionId", request.getRequestedSessionId());
    }

    public void testGetRequestURI() {
        setWebappsName("webappsName");
        setPath("/path.do?a=b&c=d&e");

        assertEquals("/webappsName/path.do", request.getRequestURI());
    }

    public void testGetRequestURL() {
        setPath("/path?a=b&c=d&e");

        assertEquals("http://www.seasar.org/path", request.getRequestURL().toString());
    }

    public void testGetServletPath() {
        setWebappsName("webappsName");
        setPath("/path?a=b&c=d&e");

        assertEquals("/path", request.getServletPath());
    }

    public void testGetPathInfo() {
        //setPath("/path?a=b&c=d&e");

        //assertEquals("", request.getPathInfo());
        // todo 実装＆テスト
    }

    public void testGetPathTranslated() {
        //setPath("/path?a=b&c=d&e");

        //assertEquals("", request.getPathTranslated());
        // todo テスト(実装済み)
    }

    public void testIsRequestedSessionIdValid() {
        // todo 実装＆テスト

    }

    public void testIsRequestedSessionIdFromCookie() {
        // todo 実装＆テスト

    }

    public void testIsRequestedSessionIdFromURL() {
        // todo 実装＆テスト

    }

    public void testIsRequestedSessionIdFromUrl() {
        // todo 実装＆テスト

    }

    public void testSetGetCharacterEncoding() throws Exception {
        String characterEncoding = "UTF-8";
        request.setCharacterEncoding(characterEncoding);
        assertEquals(characterEncoding, request.getCharacterEncoding());
    }

    public void testGetContentLength() {
        assertEquals(-1, request.getContentLength());
    }

    public void testGetContentType() {
        assertEquals("text/HTML", request.getContentType());
    }

    public void testGetInputStream() throws IOException {
        ServletInputStream actual = request.getInputStream();
        assertTrue(ServletInputStream.class.isAssignableFrom(actual.getClass()));
    }

    public void testGetParameterNoParam() {
        assertNull(request.getParameter("a"));
    }

    public void testGetParameter() {
        setPath("/path?a=b&c=d&e");

        String[] arrayVal = {"bar", "baz"};
        request.addParameter("x", "foo");
        request.addParameter("y", arrayVal);

        assertEquals("b", request.getParameter("a"));
        assertEquals("d", request.getParameter("c"));
        assertNull(request.getParameter("e"));
        assertNull(request.getParameter("f"));
        assertEquals("foo", request.getParameter("x"));
        String[] actual = request.getParameterValues("y");
        assertEquals(2, actual.length);
        assertEquals("bar", actual[0]);
        assertEquals("baz", actual[1]);
    }

    public void testGetParameterMap() {
        setPath("/path?a=b&c=d&e");

        Map expected = new HashMap();
        String[] aValue = new String[1];
        aValue[0] = "b";
        String[] cValue = new String[1];
        cValue[0] = "d";
        expected.put("a", aValue);
        expected.put("c", cValue);
        assertEquals(expected.keySet(), request.getParameterMap().keySet());
    }

    public void testGetParameterNames() {
        setPath("/path?a=b&c=d&e");

        List expected = new ArrayList(2);
        expected.add("a");
        expected.add("c");
        List actual = toSortedList(request.getParameterNames());
        assertEquals(expected, actual);
    }

    public void testGetParameterValues() throws Exception {
        request.setCharacterEncoding("UTF-8");
        setPath("/path?a=s+d&b=%E3%82%B7%E3%83%BC%E3%82%B5%E3%83%BC");

        String[] actualParamA = request.getParameterValues("a");
        String[] actualParamB = request.getParameterValues("b");
        assertEquals("s d", actualParamA[0]);
        assertEquals("シーサー", actualParamB[0]);
    }

    public void testGetProtocol() {
        assertEquals("HTTP/1.1", request.getProtocol());
    }

    public void testGetScheme() {
        assertEquals("http", request.getScheme());
    }

    public void testGetServerName() {
        assertEquals("www.seasar.org", request.getServerName());
    }

    public void testGetServerPort() {
        assertEquals(80, request.getServerPort());
    }

    public void testGetReader() throws IOException {
        assertNull(request.getReader());
    }

    public void testGetRemoteAddr() {
        assertEquals("61.215.208.29", request.getRemoteAddr());
    }

    public void testGetRemoteHost() {
        assertEquals("www.seasar.org", request.getRemoteHost());
    }

    public void testGetAttributeNames() {
        request.setAttribute("attribute1", "dummy1");
        request.setAttribute("attribute2", "dummy2");

        List expected = new ArrayList();
        expected.add("attribute1");
        expected.add("attribute2");

        List actual = toSortedList(request.getAttributeNames());

        assertEquals(expected, actual);
    }

    public void testSetGetAttribute() {
        String setValue = "dummy";
        request.setAttribute("setAttribute", setValue);
        assertEquals(setValue, request.getAttribute("setAttribute"));
    }

    public void testRemoveAttribute() {
        String setValue = "dummy";
        request.setAttribute("removeAttribute", setValue);
        assertNotNull(request.getAttribute("removeAttribute"));
        request.removeAttribute("removeAttribute");
        assertNull(request.getAttribute("removeAttribute"));
    }

    public void testGetLocale() {
        assertEquals(Locale.JAPANESE, request.getLocale());
    }

    public void testGetLocales() {
        Enumeration e = request.getLocales();
        assertEquals(Locale.JAPANESE, e.nextElement());
        assertEquals(Locale.ENGLISH, e.nextElement());
        assertFalse(e.hasMoreElements());
    }

    public void testIsSecure() {
        assertFalse(request.isSecure());
    }

    public void testGetRequestDispatcher() {
        RequestDispatcher actual = request.getRequestDispatcher("getRequestDispatcher");
        assertTrue(RequestDispatcher.class.isAssignableFrom(actual.getClass()));
    }

    public void testGetRemotePort() {
        assertEquals(8080, request.getRemotePort());
    }

    public void testGetLocalName() {
        assertEquals("LocalName", request.getLocalName());
    }

    public void testGetLocalAddr() {
        assertEquals("61.215.208.29", request.getLocalAddr());
    }

    public void testGetLocalPort() {
        assertEquals(8080, request.getLocalPort());
    }

}