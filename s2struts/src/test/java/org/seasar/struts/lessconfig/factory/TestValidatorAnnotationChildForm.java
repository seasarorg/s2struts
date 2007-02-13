package org.seasar.struts.lessconfig.factory;

public class TestValidatorAnnotationChildForm {

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

    public void setGrandchild(TestValidatorAnnotationGrandchildForm grandchild) {
    }

    public TestValidatorAnnotationGrandchildForm getGrandchild() {
        return null;
    }

    public void setGrandchildren(TestValidatorAnnotationGrandchildForm[] grandchildren) {
    }

    public TestValidatorAnnotationGrandchildForm[] getGrandchildren() {
        return null;
    }

}
