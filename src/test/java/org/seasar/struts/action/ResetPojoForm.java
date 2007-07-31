package org.seasar.struts.action;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class ResetPojoForm {

    private String message;

    public String getMessage() {
        return message;
    }

    public void reset() {
        message = "calledReset";
    }

}
