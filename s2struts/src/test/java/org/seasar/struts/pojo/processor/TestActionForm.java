package org.seasar.struts.pojo.processor;

import org.apache.struts.action.ActionForm;

/**
 * @author Katsuhiko Nagashima
 */
public class TestActionForm extends ActionForm {

    private static final long serialVersionUID = 1L;
    
    private String message = "new";

    public TestActionForm() {
    }

    public TestActionForm(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}