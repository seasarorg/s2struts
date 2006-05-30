package org.seasar.struts.pojo.processor;

/**
 * @author Katsuhiko Nagashima
 */
public class TestPojoForm {
    
    private String message = "new";
    
    public TestPojoForm() {
    }
    
    public TestPojoForm(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
