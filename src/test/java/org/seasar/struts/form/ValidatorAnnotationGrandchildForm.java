package org.seasar.struts.form;

import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.Required;

public class ValidatorAnnotationGrandchildForm {

    @Required
    @Args(keys = "GrandchildRequired", resource = false)
    public void setRequired(String required) {
    }

    public String getRequired() {
        return null;
    }

    @Args(keys = "GrandchildInteger", resource = false)
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

}
