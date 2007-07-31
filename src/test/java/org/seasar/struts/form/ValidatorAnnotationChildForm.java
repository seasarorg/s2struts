package org.seasar.struts.form;

public class ValidatorAnnotationChildForm {

    public static final String required_VALIDATOR = "required";

    public static final String required_VALIDATOR_ARGS = "ChildRequired, resource=false";

    public void setRequired(String required) {
    }

    public String getRequired() {
        return null;
    }

    public static final String integer_VALIDATOR_ARGS = "ChildInteger, resource=false";

    public void setInteger(int integer) {
    }

    public int getInteger() {
        return 1;
    }

    public void setNoValidate(String noValidate) {
    }

    public String getNoValidate() {
        return null;
    }

    public void setGrandchild(ValidatorAnnotationGrandchildForm grandchild) {
    }

    public ValidatorAnnotationGrandchildForm getGrandchild() {
        return null;
    }

    public void setGrandchildren(
            ValidatorAnnotationGrandchildForm[] grandchildren) {
    }

    public ValidatorAnnotationGrandchildForm[] getGrandchildren() {
        return null;
    }

}
