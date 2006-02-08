package org.seasar.struts.processor;

import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.action.MultiMethodPojoAction;
import org.seasar.struts.action.MultiMethodPojoActionImpl;
import org.seasar.struts.action.SingleMethodPojoAction;
import org.seasar.struts.action.SingleMethodPojoActionImpl;
import org.seasar.struts.mock.MockActionMapping;

public class DispatchPojoActionCommandTest extends S2TestCase {

    private PojoActionCommand command = new DispatchPojoActionCommand();

    Class actionInterface = MultiMethodPojoAction.class;

    Object action = new MultiMethodPojoActionImpl();

    private Object form = null;

    private ActionMapping mapping = new MockActionMapping();

    public void testExecute() {
        mapping.setParameter("method");
        getRequest().setParameter("method", "exe");
        
        String forward = command.execute(getRequest(), getResponse(),
                actionInterface, action, form, mapping);
        assertEquals("success", forward);
    }

    public void testNotExecute1() {
        String forward = command.execute(getRequest(), getResponse(),
                actionInterface, action, form, mapping);
        assertEquals(PojoActionCommand.NOT_EXECUTE, forward);
    }

    public void testNotExecute2() {
        mapping.setParameter("method");
        getRequest().setParameter("method", "exe");
        
        Class actionInterface = SingleMethodPojoAction.class;
        Object action = new SingleMethodPojoActionImpl();

        String forward = command.execute(getRequest(), getResponse(),
                actionInterface, action, form, mapping);
        assertEquals(PojoActionCommand.NOT_EXECUTE, forward);
    }

    public void testNotExecute3() {
        mapping.setParameter("method");
        getRequest().setParameter("method", "notExe");
        
        String forward = command.execute(getRequest(), getResponse(),
                actionInterface, action, form, mapping);
        assertEquals(PojoActionCommand.NOT_EXECUTE, forward);
    }

}
