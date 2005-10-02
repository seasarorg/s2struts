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
public class LoginAction extends Action {
    private LoginService loginService;

    public LoginAction() {
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        String id = req.getParameter("id");
        String pass = req.getParameter("pass");

        loginService.login(id, pass);

        return mapping.findForward("success");
    }
}