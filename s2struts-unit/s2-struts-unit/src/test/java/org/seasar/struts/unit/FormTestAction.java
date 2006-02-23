package org.seasar.struts.unit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Satoshi Kimura
 */
public class FormTestAction extends Action {

    public FormTestAction() {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        if (form == null) {
            throw new NullPointerException();
        }

        TestActionForm testForm = (TestActionForm) form;

        return mapping.findForward(testForm.getVal());
    }

}