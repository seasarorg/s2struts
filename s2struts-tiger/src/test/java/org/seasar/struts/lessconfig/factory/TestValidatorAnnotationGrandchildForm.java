package org.seasar.struts.lessconfig.factory;

import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.Required;

public class TestValidatorAnnotationGrandchildForm {

    @Required
    @Args(keys = "GrandchildRequired", resource = false)
    public void setRequired(String required) {
    }

    @Args(keys = "GrandchildInteger", resource = false)
    public void setInteger(int integer) {
    }

    public void setNoValidate(String noValidate) {
    }

}
