package org.seasar.httpunit;

import javax.servlet.http.HttpServlet;

import org.apache.struts.action.ActionServlet;
import org.seasar.framework.container.servlet.S2ContainerServlet;
import org.seasar.httpunit.exception.TooManyRegistrationRuntimeException;
import org.seasar.struts.servlet.S2ActionServlet;

/**
 * @author Satoshi Kimura
 */
public class S2HttpTestCaseTest extends S2HttpTestCase {

    public S2HttpTestCaseTest(String name) {
        super(name);
    }

    protected void setUp() {
        setDocBase("");
        include("HttpMockObject.dicon");
    }

    public void testGetHttpServletString() {
        HttpServlet actual = getHttpServlet("action");
        assertEquals(S2ActionServlet.class, actual.getClass());
        actual = getHttpServlet("s2container");
        assertEquals(S2ContainerServlet.class, actual.getClass());

        actual = getHttpServlet("test1");
        assertEquals(TestServlet.class, actual.getClass());
        actual = getHttpServlet("test2");
        assertEquals(TestServlet.class, actual.getClass());

        assertNull(getHttpServlet("test3"));
    }

    public void testGetHttpServletClass() {
        HttpServlet actual = getHttpServlet(ActionServlet.class);
        assertEquals(S2ActionServlet.class, actual.getClass());
        actual = getHttpServlet(S2ContainerServlet.class);
        assertEquals(S2ContainerServlet.class, actual.getClass());

        try {
            actual = getHttpServlet(TestServlet.class);
            fail();
        } catch (TooManyRegistrationRuntimeException e) {
            //success
        }

        assertNull(getHttpServlet(TestServlet2.class));
    }

    public void setUpSetRequestURL() {
        setWebappsName("s2struts-example");
    }
    public void testSetRequestURL() {
        setPath("/add.do?aa=bb&cc=dd&ee");

        assertEquals("/s2struts-example/add.do", getMockReq().getRequestURI());
        assertEquals(new StringBuffer("http://www.seasar.org/s2struts-example/add.do").toString(), getMockReq()
                .getRequestURL().toString());
        assertEquals("/add.do", getMockReq().getServletPath());
        assertEquals("aa=bb&cc=dd&ee", getMockReq().getQueryString());
    }

    public void setUpSetRequestURLNoWebappsName() {
        setWebappsName(null);
    }
    public void testSetRequestURLNoWebappsName() {
        setPath("/add.do?aa=bb&cc=dd&ee");

        assertEquals("/add.do", getMockReq().getRequestURI());
        assertEquals(new StringBuffer("http://www.seasar.org/add.do").toString(), getMockReq().getRequestURL()
                .toString());
        assertEquals("/add.do", getMockReq().getServletPath());
        assertEquals("aa=bb&cc=dd&ee", getMockReq().getQueryString());
    }
    public void setUpSetRequestURLPort() {
        setWebappsName("s2struts-example");

        setRequestReturnValue("getServerPort", new Integer(9999));
    }
    public void testSetRequestURLPort() {
        setPath("/add.do?aa=bb&cc=dd&ee");

        assertEquals("/s2struts-example/add.do", getMockReq().getRequestURI());
        assertEquals(new StringBuffer("http://www.seasar.org:9999/s2struts-example/add.do").toString(), getMockReq()
                .getRequestURL().toString());
        assertEquals("/add.do", getMockReq().getServletPath());
        assertEquals("aa=bb&cc=dd&ee", getMockReq().getQueryString());
    }

}