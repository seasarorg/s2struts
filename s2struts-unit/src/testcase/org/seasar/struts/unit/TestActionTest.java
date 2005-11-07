package org.seasar.struts.unit;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * @author Satoshi Kimura
 */
public class TestActionTest extends S2StrutsTestCase {

    public TestActionTest(String name) {
        super(name);
    }

    protected void setUp() {
        include("HttpMockObject.dicon");
    }

    public void setUpExecuteTrue() {
        include("TestServiceReturnTrue.dicon");
    }

    public void testExecuteTrue() throws Exception {
        String actual = execute(TestAction.class);

        assertEquals("success", actual);

        String[] values = {"val1", "val2", "val3", "val4", "val5"};
        ActionMessages expectedMessages = new ActionMessages();
        expectedMessages.add("message", new ActionMessage("messageKey", values));
        assertEquals(expectedMessages, getActionMessages());
    }

    public void setUpExecuteFalse() {
        include("TestServiceReturnFalse.dicon");
    }

    public void testExecuteFalse() throws Exception {
        String actual = execute(TestAction.class);

        assertEquals("fail", actual);

        String[] values = {"val1", "val2", "val3", "val4", "val5"};
        ActionErrors expectedErrors = new ActionErrors();
        expectedErrors.add("error", new ActionMessage("errorKey", values));
        assertEquals(expectedErrors, getActionErrors());
    }
}