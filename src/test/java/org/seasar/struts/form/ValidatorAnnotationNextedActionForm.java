package org.seasar.struts.form;

import org.apache.struts.validator.ValidatorForm;

public class ValidatorAnnotationNextedActionForm extends ValidatorForm {

    private static final long serialVersionUID = 1L;

    private String value = null;

    public String getValue() {
        return value;
    }

    public static final String value_VALIDATOR = "required";

    public static final String value_VALIDATOR_ARGS = "Value, resource=false";

    public void setValue(String value) {
        this.value = value;
    }

}
