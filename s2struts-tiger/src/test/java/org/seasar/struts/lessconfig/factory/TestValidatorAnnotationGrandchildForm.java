package org.seasar.struts.lessconfig.factory;

import org.seasar.struts.annotation.tiger.BoolType;
import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.Required;

public class TestValidatorAnnotationGrandchildForm {

    @Required
    @Args(keys = "GrandchildRequired", resource = BoolType.FALSE)
    public void setRequired(String required) {
    }

    @Args(keys = "GrandchildInteger", resource = BoolType.FALSE)
    public void setInteger(int integer) {
    }

    public void setNoValidate(String noValidate) {
    }

}
