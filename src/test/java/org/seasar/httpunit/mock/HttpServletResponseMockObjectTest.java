package org.seasar.httpunit.mock;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;

import org.seasar.httpunit.S2HttpTestCase;

/**
 * @author Satoshi Kimura
 */
public class HttpServletResponseMockObjectTest extends S2HttpTestCase {

    private MockHttpServletResponse response;

    public HttpServletResponseMockObjectTest(String name) {
        super(name);
    }

    protected void setUp() {
        include("HttpMockObject.dicon");
    }

    public void testAddCookie() {
        response.addCookie(new Cookie("key", "value"));
        // todo assert
    }

    public void testContainsHeader() {
        setResponseReturnValue("containsHeader", "true", Boolean.TRUE);

        assertFalse(response.containsHeader("false"));
        assertTrue(response.containsHeader("true"));
    }

    public void testEncodeURL() {
        response.encodeURL("/foo");
        // todo assert
    }

    public void testEncodeRedirectURL() {
        response.encodeRedirectURL("/foo");
        // todo assert
    }

    public void testSendErrorintString() throws Exception {
        response.sendError(500, "foo");
        // todo assert
    }

    public void testSendErrorint() throws Exception {
        response.sendError(500);
        // todo assert
    }

    public void testSendRedirect() throws Exception {
        response.sendRedirect("foo");
        // todo assert
    }

    public void testSetDateHeader() {
        response.setDateHeader("name", new Date().getTime());
        // todo assert
    }

    public void testAddDateHeader() {
        response.addDateHeader("name1", new Date().getTime());
        response.addDateHeader("name2", new Date().getTime());
        // todo assert
    }

    public void testSetHeader() {
        response.setHeader("name", "value");
        // todo assert
    }

    public void testAddHeader() {
        response.addHeader("name", "value");
        response.addHeader("name1", "value2");
        // todo assert
    }

    public void testSetIntHeader() {
        response.setIntHeader("name", 100);
        // todo assert
    }

    public void testAddIntHeader() {
        response.addIntHeader("name1", 100);
        response.addIntHeader("name2", 100);
        // todo assert
    }

    public void testSetStatusint() {
        response.setStatus(500);
        // todo assert
    }

    public void testGetOutputStream() throws IOException {
        ServletOutputStream stream = response.getOutputStream();

        assertNotNull(stream);
    }

    public void testGetWriter() throws IOException {
        PrintWriter writer = response.getWriter();

        assertNotNull(writer);
    }

    public void testGetSetCharacterEncoding() {
        response.setCharacterEncoding("foo");

        assertEquals("foo", response.getCharacterEncoding());
    }

    public void testSetContentLength() {
        response.setContentLength(100);
        // todo assert
    }

    public void testGetSetContentType() {
        response.setContentType("foo");

        assertEquals("foo", response.getContentType());
    }

    public void testGetSetBufferSize() {
        response.setBufferSize(100);

        assertEquals(100, response.getBufferSize());
    }

    public void testFlushBuffer() throws Exception {
        response.flushBuffer();
        // todo assert
    }

    public void testResetBuffer() {
        response.resetBuffer();
        // todo assert
    }

    public void testIsCommitted() {
        assertFalse(response.isCommitted());
    }

    public void testReset() {
        response.reset();
        // todo assert
    }

    public void testGetSetLocale() {
        response.setLocale(Locale.JAPAN);

        assertEquals(Locale.JAPAN, response.getLocale());
    }

}