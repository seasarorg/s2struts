package org.seasar.struts.pojo.form;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class TestResetPojoForm {
    
    private String message;
    
    public String getMessage() {
        return message;
    }
    
    public void reset() {
        message  = "calledReset";
    }

}
