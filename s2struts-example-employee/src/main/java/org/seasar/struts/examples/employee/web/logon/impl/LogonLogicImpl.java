package org.seasar.struts.examples.employee.web.logon.impl;

import org.seasar.struts.examples.employee.web.logon.LogonLogic;

public class LogonLogicImpl implements LogonLogic {

    public boolean logon(String userName, String password) {
        return userName.equals(password);
    }

}
