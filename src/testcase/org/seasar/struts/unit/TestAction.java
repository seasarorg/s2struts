package org.seasar.struts.unit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * @author Satoshi Kimura
 */
public class TestAction extends Action {
    private TestService service;

    public TestAction(TestService service) {
        this.service = service;
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        boolean isSuccess = service.service();

        if (isSuccess) {
            String[] values = {"val1", "val2", "val3", "val4", "val5"};
            ActionMessages messages = new ActionMessages();
            messages.add("message", new ActionMessage("messageKey", values));
            saveMessages(req, messages);
            return mapping.findForward("success");
        } else {
            String[] values = {"val1", "val2", "val3", "val4", "val5"};
            ActionMessages errors = new ActionMessages();
            errors.add("error", new ActionMessage("errorKey", values));
            saveErrors(req, errors);
            return mapping.findForward("fail");
        }
    }

}