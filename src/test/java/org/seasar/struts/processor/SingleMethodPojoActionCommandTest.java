package org.seasar.struts.processor;

import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.action.MultiMethodPojoAction;
import org.seasar.struts.action.MultiMethodPojoActionImpl;
import org.seasar.struts.action.SingleMethodPojoAction;
import org.seasar.struts.action.SingleMethodPojoActionImpl;
import org.seasar.struts.mock.MockActionMapping;

public class SingleMethodPojoActionCommandTest extends S2TestCase {

    private PojoActionCommand command = new SingleMethodPojoActionCommand();

    private Object form = null;

    private ActionMapping mapping = new MockActionMapping();

    public void testExecute() {
        Class actionInterface = SingleMethodPojoAction.class;
        Object action = new SingleMethodPojoActionImpl();

        String forward = command.execute(getRequest(), getResponse(),
                actionInterface, action, form, mapping);
        assertEquals("success", forward);
    }

    public void testNotExecute() {
        Class actionInterface = MultiMethodPojoAction.class;
        Object action = new MultiMethodPojoActionImpl();

        String forward = command.execute(getRequest(), getResponse(),
                actionInterface, action, form, mapping);
        assertEquals(PojoActionCommand.NOT_EXECUTE, forward);
    }

}
