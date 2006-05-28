package org.seasar.struts.pojo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.struts.mock.MockActionMapping;

public class PojoProcessActionTest extends S2TestCase {

    private PojoProcessAction action;

    protected void setUp() throws Exception {
        super.setUp();
        include("PojoProcessActionTest.dicon");
    }

    public void testExecute() throws Exception {
        action.addPojoCommnad(new PojoCommand() {
            public String execute(PojoInvocation invocation) {
                return "success";
            }
        });

        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionForm form = null;
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(TestPojoAction.class.getName());

        ActionForward forward = action.execute(mapping, form, request, response);
        assertNotNull(forward);
        assertEquals("success", forward.getName());
    }

    public void testExecuteDownload() throws Exception {
        action.addPojoCommnad(new PojoCommand() {
            public String execute(PojoInvocation invocation) {
                return null;
            }
        });

        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionForm form = null;
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(TestPojoAction.class.getName());

        ActionForward forward = action.execute(mapping, form, request, response);
        assertNull(forward);
    }

    public void testNotExecuteBecauseNotRegisteredPojoCommand() throws Exception {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionForm form = null;
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(TestPojoAction.class.getName());

        try {
            action.execute(mapping, form, request, response);
            fail();
        } catch (Exception e) {
            // success
        }
    }

    public void testNotExecuteBecauseNotCalledAction() throws Exception {
        action.addPojoCommnad(new PojoCommand() {
            public String execute(PojoInvocation invocation) {
                return invocation.execute();
            }
        });

        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionForm form = null;
        ActionMapping mapping = new MockActionMapping();
        mapping.setType(TestPojoAction.class.getName());

        try {
            action.execute(mapping, form, request, response);
            fail();
        } catch (Exception e) {
            // success
        }
    }

    public void testNotExecuteBecauseNotFoundComponent() throws Exception {
        action.addPojoCommnad(new PojoCommand() {
            public String execute(PojoInvocation invocation) {
                return "success";
            }
        });

        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ActionForm form = null;
        ActionMapping mapping = new MockActionMapping();
        mapping.setType("org.seasar.struts.pojo.NotRegisteredAction");

        try {
            action.execute(mapping, form, request, response);
            fail();
        } catch (Exception e) {
            // success
        }
    }

}
