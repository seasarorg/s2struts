package org.seasar.struts.examples.employee.web.logon;

public interface LogonAction {

    String SUCCESS_FORWARD = "path = /, redirect = true";

    String SUCCESS = "success";
    
    String ERROR_FORWARD = "path = /pages/logon/logon.html";
    
    String ERROR = "error";

    String doLogon();

}
