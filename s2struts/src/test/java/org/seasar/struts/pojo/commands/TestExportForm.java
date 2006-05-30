package org.seasar.struts.pojo.commands;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author Katsuhiko Nagashima
 */
public class TestExportForm extends ValidatorForm {

    private static final long serialVersionUID = 1L;

    private String message;

    public TestExportForm() {

    }

    public TestExportForm(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
