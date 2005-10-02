package org.seasar.struts.unit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

/**
 * @author Satoshi Kimura
 */
public class DispatchTestAction extends MappingDispatchAction {
    public ActionForward foo(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) {
        return mapping.findForward("success");
    }
}