package org.seasar.struts.examples.employee.web.logon;

import java.io.Serializable;

public class LogonForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName = "";

    private String password = "";

    public String getUserName() {
        return userName;
    }

    public static final String userName_VALIDATOR = "required";

    public static final String userName_VALIDATOR_ARGS = "form.logon.userName";

    public static final int userName_VALIDATOR_ORDER = 1;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public static final String password_VALIDATOR = "required";

    public static final String password_VALIDATOR_ARGS = "form.logon.password";

    public static final int password_VALIDATOR_ORDER = 2;

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("[");
        buf.append(this.userName).append(", ");
        buf.append(this.password);
        buf.append("]");
        return buf.toString();
    }

}
