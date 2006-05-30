package org.seasar.struts.pojo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;

public class MethodBindingActionTest extends S2TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        include("MethodBindingActionTest.dicon");
    }

    public void testExecute() throws Exception {
        MethodBinding methodBinding = new MethodBinding("#{bindingAction.exe}");
        MethodBindingAction action = new MethodBindingAction(methodBinding);

        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionForm form = null;
        ActionMapping mapping = new MockActionMapping();

        ActionForward forward = action.execute(mapping, form, request, response);
        assertNotNull(forward);
        assertEquals("success", forward.getName());
    }

    public void testExecuteDownload() throws Exception {
        MethodBinding methodBinding = new MethodBinding("#{bindingAction.download}");
        MethodBindingAction action = new MethodBindingAction(methodBinding);

        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionForm form = null;
        ActionMapping mapping = new MockActionMapping();

        ActionForward forward = action.execute(mapping, form, request, response);
        assertNull(forward);
    }

}
