package org.seasar.struts.form;

public class ValidatorAnnotationChildForm {
    
    public static final String required_VALIDATOR = "required";
    public static final String required_VALIDATOR_ARGS = "ChildRequired, resource=false";

    public void setRequired(String required) {
    }
    
    public static final String integer_VALIDATOR_ARGS = "ChildInteger, resource=false";
    
    public void setInteger(int integer) {
    }
    
    public void setNoValidate(String noValidate) {
    }
    
    public void setGrandchild(ValidatorAnnotationGrandchildForm grandchild) {
    }
    
    public void setGrandchildren(ValidatorAnnotationGrandchildForm[] grandchildren) {
    }

}
