package org.seasar.struts.processor;

import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.action.MultiMethodPojoAction;
import org.seasar.struts.action.MultiMethodPojoActionImpl;
import org.seasar.struts.action.SingleMethodPojoAction;
import org.seasar.struts.action.SingleMethodPojoActionImpl;
import org.seasar.struts.mock.MockActionMapping;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class IndexedDispatchPojoActionCommandTest extends S2TestCase {

	private PojoActionCommand command = new IndexedDispatchPojoActionCommand();

    Class actionInterface = MultiMethodPojoAction.class;

    Object action = new MultiMethodPojoActionImpl();

    private Object form = null;

    private ActionMapping mapping = new MockActionMapping();

    public void testExecute() {
        mapping.setParameter("method");
        getRequest().setParameter("method[10]", "exe");
        
        String forward = command.execute(getRequest(), getResponse(),
                actionInterface, action, form, mapping);
        assertEquals("success10", forward);
    }

    public void testNotExecute1() {
        String forward = command.execute(getRequest(), getResponse(),
                actionInterface, action, form, mapping);
        assertEquals(PojoActionCommand.NOT_EXECUTE, forward);
    }

    public void testNotExecute2() {
        mapping.setParameter("method");
        getRequest().setParameter("method[10]", "exe");
        
        Class actionInterface = SingleMethodPojoAction.class;
        Object action = new SingleMethodPojoActionImpl();

        String forward = command.execute(getRequest(), getResponse(),
                actionInterface, action, form, mapping);
        assertEquals(PojoActionCommand.NOT_EXECUTE, forward);
    }

    public void testNotExecute3() {
        mapping.setParameter("method");
        getRequest().setParameter("method[10]", "notExe");
        
        String forward = command.execute(getRequest(), getResponse(),
                actionInterface, action, form, mapping);
        assertEquals(PojoActionCommand.NOT_EXECUTE, forward);
    }

}
