package org.seasar.struts.examples.employee.web.logon;

public interface LogoffAction {

    String SUCCESS_FORWARD = "path = /pages/logon/logon.html, redirect = true";

    String SUCCESS = "logon";

    String doLogoff();

}
