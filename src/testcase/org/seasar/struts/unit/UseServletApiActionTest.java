package org.seasar.struts.unit;

/**
 * @author Satoshi Kimura
 */
public class UseServletApiActionTest extends S2StrutsTestCase {

    public UseServletApiActionTest(String name) {
        super(name);
    }

    protected void setUp() {
        include("HttpMockObject.dicon");
    }

    public void testExecute() throws Exception {
        getHttpServletRequest().setAttribute("a", "a");
        getHttpServletRequest().getSession().setAttribute("b", "b");

        execute(UseServletApiAction.class);

        assertEquals("a", getHttpServletRequest().getAttribute("a"));
        assertEquals("b", getHttpServletRequest().getSession().getAttribute("b"));
        assertEquals("c", getHttpServletRequest().getAttribute("c"));
        assertEquals("d", getHttpServletRequest().getSession().getAttribute("d"));

    }
}