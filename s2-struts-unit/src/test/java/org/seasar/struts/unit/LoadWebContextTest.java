package org.seasar.struts.unit;

/**
 * @author Satoshi Kimura
 */
public class LoadWebContextTest extends S2StrutsTestCase {

    public LoadWebContextTest(String name) {
        super(name);
    }

    protected void setUp() {
        include("HttpMockObject.dicon");
        setDocBase("org/seasar/struts/unit");
    }

    public void testExecuteString() throws Exception {
        String actual = execute("/login?id=id&pass=pass");

        assertEquals("success", actual);
        assertEquals(LoginAction.class, getExecutedAction().getClass());
        assertNull(getExecutedActionForm());
    }

    public void testExecuteFail() throws Exception {
        try {
            execute("/login?id=idd&pass=passs");
            fail();
        } catch (RuntimeException e) {
            // success
        }
    }
}