package org.seasar.struts.lessconfig.factory;

import org.seasar.struts.annotation.tiger.BoolType;
import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.Required;

public class TestValidatorAnnotationChildForm {

    @Required
    @Args(keys = "ChildRequired", resource = BoolType.FALSE)
    public void setRequired(String required) {
    }

    public String getRequired() {
        return null;
    }

    @Args(keys = "ChildInteger", resource = BoolType.FALSE)
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
