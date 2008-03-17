package org.seasar.struts.processor;

import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.struts.Constants;
import org.seasar.struts.mock.MockActionMapping;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class MethodBindingActionCommandTest extends S2TestCase {

    private ActionCommand command = new MethodBindingActionCommand();

    private Object action = null;

    private Object form = null;

    private ActionMapping mapping = new MockActionMapping();

    protected void setUp() throws Exception {
        super.setUp();
        include("s2struts.dicon");
        include("MethodBindingActionCommandTest.dicon");
    }

    public void testExecute() {
        getRequest().setAttribute(Constants.ORIGINAL_PATH_KEY, "/path");
        getRequest().setParameter("1234567890", "TEST");
        S2StrutsContextUtil.setMethodBindingExpression("/path", "1234567890",
                "TEST", "#{bindingAction.exe}");

        String forward = command.execute(getRequest(), getResponse(), action,
                form, mapping);
        assertEquals("success", forward);
    }

    public void testMethodBindingDownload() {
        getRequest().setAttribute(Constants.ORIGINAL_PATH_KEY, "/path");
        getRequest().setParameter("1234567890", "TEST");
        S2StrutsContextUtil.setMethodBindingExpression("/path", "1234567890",
                "TEST", "#{bindingAction.download}");

        String forward = command.execute(getRequest(), getResponse(), action,
                form, mapping);
        assertNull(forward);
    }

    public void testNoRegisteredComponent() {
        getRequest().setAttribute(Constants.ORIGINAL_PATH_KEY, "/path");
        getRequest().setParameter("1234567890", "TEST");
        S2StrutsContextUtil.setMethodBindingExpression("/path", "1234567890",
                "TEST", "#{noregisteredAction.exe}");

        try {
            command.execute(getRequest(), getResponse(), action, form, mapping);
            fail();
        } catch (ComponentNotFoundRuntimeException e) {
            // success
        }
    }

    public void testNotExecute() {
        getRequest().setAttribute(Constants.ORIGINAL_PATH_KEY, "/path");
        S2StrutsContextUtil.setMethodBindingExpression("/path", "1234567890",
                "TEST", "#{bindingAction.exe}");

        String forward = command.execute(getRequest(), getResponse(), action,
                form, mapping);
        assertEquals(ActionCommand.NOT_EXECUTE, forward);
    }

    public void testIndexedExecute() {
        getRequest().setAttribute(Constants.ORIGINAL_PATH_KEY, "/path");
        getRequest().setParameter("1234567890[10]", "TEST");
        S2StrutsContextUtil.setMethodBindingExpression("/path", "1234567890",
                "TEST", "#{bindingAction.exe}");

        String forward = command.execute(getRequest(), getResponse(), action,
                form, mapping);
        assertEquals("success10", forward);
    }

    public void testOtherActionMappingExecute() {
        getRequest().setAttribute(Constants.ORIGINAL_PATH_KEY, "/first");
        getRequest().setParameter("1234567890", "TEST");
        S2StrutsContextUtil.setMethodBindingExpression("/first",
                "1234567890", "TEST", "#{bindingAction.exe}");

        String forward = command.execute(getRequest(), getResponse(), action,
                form, mapping);
        assertEquals("success", forward);

        getRequest().setAttribute(Constants.ORIGINAL_PATH_KEY, "/second");
        forward = command.execute(getRequest(), getResponse(), action, form,
                mapping);
        assertEquals(ActionCommand.NOT_EXECUTE, forward);

        getRequest().setAttribute(Constants.ORIGINAL_PATH_KEY, "/first");
        forward = command.execute(getRequest(), getResponse(), action, form,
                mapping);
        assertEquals("success", forward);
    }

}
