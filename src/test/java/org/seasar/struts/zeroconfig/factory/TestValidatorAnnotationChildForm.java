package org.seasar.struts.zeroconfig.factory;

public class TestValidatorAnnotationChildForm {
    
    public static final String required_VALIDATOR = "required";
    public static final String required_VALIDATOR_ARGS = "ChildRequired, resource=false";

    public void setRequired(String required) {
    }
    
    public static final String integer_VALIDATOR_ARGS = "ChildInteger, resource=false";
    
    public void setInteger(int integer) {
    }
    
    public void setNoValidate(String noValidate) {
    }
    
    public void setGrandchild(TestValidatorAnnotationGrandchildForm grandchild) {
    }
    
    public void setGrandchildren(TestValidatorAnnotationGrandchildForm[] grandchildren) {
    }

}
