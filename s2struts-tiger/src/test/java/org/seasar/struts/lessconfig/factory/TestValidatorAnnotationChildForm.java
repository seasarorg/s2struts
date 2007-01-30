package org.seasar.struts.lessconfig.factory;

import org.seasar.struts.annotation.tiger.BoolType;
import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.Required;

public class TestValidatorAnnotationChildForm {

    @Required
    @Args(keys = "ChildRequired", resource = BoolType.FALSE)
    public void setRequired(String required) {
    }

    @Args(keys = "ChildInteger", resource = BoolType.FALSE)
    public void setInteger(int integer) {
    }

    public void setNoValidate(String noValidate) {
    }

    public void setGrandchild(TestValidatorAnnotationGrandchildForm grandchild) {
    }

    public void setGrandchildren(TestValidatorAnnotationGrandchildForm[] grandchildren) {
    }

}
