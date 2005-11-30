package org.seasar.struts.form;

public class ValidatorAnnotationChildForm {
    
    /**
     * @org.seasar.struts.validator.annotation.backport175.Required
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="ChildRequired", resource=false)
     */
    public void setRequired(String required) {
    }

    /**
     * @org.seasar.struts.validator.annotation.backport175.Args(keys="ChildInteger", resource=false)
     */
    public void setInteger(int integer) {
    }
    
    public void setNoValidate(String noValidate) {
    }
    
    public void setGrandchild(ValidatorAnnotationGrandchildForm grandchild) {
    }
    
    public void setGrandchildren(ValidatorAnnotationGrandchildForm[] grandchildren) {
    }

}
