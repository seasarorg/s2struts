package org.seasar.struts.action;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author Katsuhiko Nagashima
 */
public class ExportForm extends ValidatorForm {
    
    private String message;
    
    public ExportForm() {
        
    }
    
    public ExportForm(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }

}
