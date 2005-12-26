package org.seasar.httpunit.mock;

import java.util.List;

import javax.servlet.ServletContext;

import org.seasar.httpunit.S2HttpTestCase;

/**
 * @author Satoshi Kimura
 */
public class HttpSessionMockObjectTest extends S2HttpTestCase {
    private MockHttpSession session;

    public HttpSessionMockObjectTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        include("HttpMockObject.dicon");
    }

    public void testGetCreationTime() {
        assertEquals(520009200000L, session.getCreationTime());
        //todo リクエストから作成したときのテスト
    }

    public void testGetId() {
        assertEquals("sessionId", session.getId());
    }

    public void testGetLastAccessedTime() {
        session.getLastAccessedTime();
        // todo 実装＆テスト
    }

    public void testGetServletContext() {
        ServletContext context = session.getServletContext();
        assertNotNull(context);
        // todo 実装＆テスト
        // todo StandardContextがあるときのテスト
    }

    public void testGetSetMaxInactiveInterval() {
        session.setMaxInactiveInterval(1000);

        assertEquals(1000, session.getMaxInactiveInterval());
    }

    /**
     * @deprecated
     */
    public void testGetSessionContext() {
        javax.servlet.http.HttpSessionContext context = session.getSessionContext();
        //todo 実装＆テスト
    }

    public void testGetSetAttribute() {
        assertNull(session.getAttribute("foo"));

        session.setAttribute("foo", "bar");

        assertEquals("bar", session.getAttribute("foo"));
    }

    public void testGetAttributeNames() {
        session.setAttribute("fooName", "foo");
        session.setAttribute("bazName", "baz");
        session.setAttribute("barName", "bar");

        List actual = toSortedList(session.getAttributeNames());

        assertEquals(3, actual.size());
        assertEquals("barName", actual.get(0));
        assertEquals("bazName", actual.get(1));
        assertEquals("fooName", actual.get(2));
    }

    /**
     * @deprecated
     */
    public void testGetValueNames() {
        session.putValue("fooName", "foo");
        session.putValue("bazName", "baz");
        session.putValue("barName", "bar");

        String[] actual = session.getValueNames();
        sort(actual);

        assertEquals(3, actual.length);
        assertEquals("barName", actual[0]);
        assertEquals("bazName", actual[1]);
        assertEquals("fooName", actual[2]);
    }

    /**
     * @deprecated
     */
    public void testGetPutValue() {
        session.putValue("foo", "bar");

        assertEquals("bar", session.getValue("foo"));
    }

    public void testRemoveAttribute() {
        session.setAttribute("foo", "foo");

        assertNotNull(session.getAttribute("foo"));

        session.removeAttribute("foo");

        assertNull(session.getAttribute("foo"));
    }

    /**
     * @deprecated
     */
    public void testRemoveValue() {
        session.putValue("foo", "foo");

        assertNotNull(session.getValue("foo"));

        session.removeValue("foo");

        assertNull(session.getValue("foo"));
    }

    public void testInvalidate() {
        session.setAttribute("foo", "bar");
        assertEquals("bar", session.getAttribute("foo"));

        session.invalidate();

        try {
            session.getAttribute("foo");
            fail();
        } catch (IllegalStateException e) {
            // success
        }
    }

    public void testIsNew() {
        session.isNew();
        //todo 実装＆テスト
    }

    public void testSetisValid() {
        assertFalse(session.isValid());
        session.setValid(true);
        assertTrue(session.isValid());
    }

}