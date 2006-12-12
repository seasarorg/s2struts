package org.seasar.struts.examples.employee.web.logon.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.seasar.struts.examples.employee.Constants;
import org.seasar.struts.examples.employee.web.logon.LogonAction;
import org.seasar.struts.examples.employee.web.logon.LogonForm;
import org.seasar.struts.examples.employee.web.logon.LogonLogic;
import org.seasar.struts.pojo.MessageManager;

public class LogonActionImpl implements LogonAction {

    private HttpServletResponse response;

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    private HttpSession session;

    public void setSession(HttpSession session) {
        this.session = session;
    }

    private LogonLogic logonLogic;

    public void setLogonLogic(LogonLogic logonLogic) {
        this.logonLogic = logonLogic;
    }

    private LogonForm logonForm;

    public void setLogonForm(LogonForm logonForm) {
        this.logonForm = logonForm;
    }

    //
    //
    //

    public String doLogon() {

        if (!this.logonLogic.logon(this.logonForm.getUserName(), this.logonForm
                .getPassword())) {
            MessageManager.addGlobalError("errors.logon");
            MessageManager.saveErrors();
            return LogonAction.ERROR;
        }

        this.session.setAttribute(Constants.LOGON_KEY, this.logonForm
                .getUserName());

        String requestUri = (String) this.session
                .getAttribute(Constants.LOGON_REQUEST_URI_KEY);
        if (requestUri != null) {
            this.session.removeAttribute(Constants.LOGON_REQUEST_URI_KEY);
            sendRedirect(requestUri);
            return null;
        }

        return LogonAction.SUCCESS;
    }

    private void sendRedirect(String requestUri) {
        try {
            this.response.sendRedirect(this.response
                    .encodeRedirectURL(requestUri));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
