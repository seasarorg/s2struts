package org.seasar.struts.form;

public class ValidatorAnnotationChildForm {

    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="ChildRequired", resource=false)
     */
    public void setRequired(String required) {
    }

    public String getRequired() {
        return null;
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="ChildInteger", resource=false)
     */
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

    public void setGrandchildren(ValidatorAnnotationGrandchildForm[] grandchildren) {
    }

    public ValidatorAnnotationGrandchildForm[] getGrandchildren() {
        return null;
    }

}
