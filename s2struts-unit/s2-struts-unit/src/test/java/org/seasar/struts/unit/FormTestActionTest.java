package org.seasar.struts.unit;

import org.apache.struts.action.ActionForm;

/**
 * @author Satoshi Kimura
 */
public class FormTestActionTest extends S2StrutsTestCase {

    protected void setUp() {
        include("HttpMockObject.dicon");
    }

    public FormTestActionTest(String name) {
        super(name);
    }

    public void testNullForm() throws Exception {
        try {
            execute(FormTestAction.class);
            fail();
        } catch (NullPointerException e) {
            //success
        }

        try {
            execute(FormTestAction.class, (ActionForm) null);
            fail();
        } catch (NullPointerException e) {
            //success
        }
    }

    public void testGetValueFromForm() throws Exception {
        TestActionForm form = new TestActionForm();
        form.setVal("foo");

        String actual = execute(FormTestAction.class, form);

        assertEquals("foo", actual);
    }
}