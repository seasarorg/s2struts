package org.seasar.struts.lessconfig.factory;

import org.seasar.struts.lessconfig.factory.TestValidatorAnnotationGrandchildForm;

public class TestValidatorAnnotationChildForm {
    
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
    
    public void setGrandchild(TestValidatorAnnotationGrandchildForm grandchild) {
    }
    
    public void setGrandchildren(TestValidatorAnnotationGrandchildForm[] grandchildren) {
    }

}
