package org.seasar.struts.form;

import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.Required;

public class ValidatorAnnotationChildForm {
    
    @Required
    @Args(keys = "ChildRequired", resource = false)
    public void setRequired(String required) {
    }

    @Args(keys = "ChildInteger", resource = false)
    public void setInteger(int integer) {
    }
    
    public void setNoValidate(String noValidate) {
    }
    
    public void setGrandchild(ValidatorAnnotationGrandchildForm grandchild) {
    }
    
    public void setGrandchildren(ValidatorAnnotationGrandchildForm[] grandchildren) {
    }

}
