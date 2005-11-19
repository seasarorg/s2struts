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
public class UseServletApiAction extends Action {

    public UseServletApiAction() {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        if ("a".equals(req.getAttribute("a")) && "b".equals(req.getSession().getAttribute("b"))) {
            req.setAttribute("c", "c");
            req.getSession().setAttribute("d", "d");
        } else {
            throw new IllegalStateException();
        }

        return mapping.findForward("success");
    }

}