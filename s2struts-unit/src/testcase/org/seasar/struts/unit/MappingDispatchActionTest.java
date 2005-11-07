package org.seasar.struts.unit;

/**
 * @author Satoshi Kimura
 */
public class MappingDispatchActionTest extends S2StrutsTestCase {

    /**
     * @param name
     */
    public MappingDispatchActionTest(String name) {
        super(name);
    }

    protected void setUp() {
        include("HttpMockObject.dicon");
    }

    public void testFooMethod() throws Exception {
        String actual = execute(DispatchTestAction.class, "foo");
        assertEquals("success", actual);
    }

    public void testNoneMethod() throws Exception {
        try {
            execute(DispatchTestAction.class, "none");
            fail();
        } catch (NoSuchMethodException e) {
            // success
        }
    }
}