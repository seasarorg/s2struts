package org.seasar.struts.examples.employee.web.logon.impl;

import javax.servlet.http.HttpSession;

import org.seasar.struts.examples.employee.Constants;
import org.seasar.struts.examples.employee.web.logon.LogoffAction;

public class LogoffActionImpl implements LogoffAction {

    private HttpSession session;

    public void setSession(HttpSession session) {
        this.session = session;
    }

    //
    //
    //

    public String doLogoff() {
        this.session.removeAttribute(Constants.LOGON_KEY);
        return LogoffAction.SUCCESS;
    }

}
