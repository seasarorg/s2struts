package org.seasar.struts.lessconfig.factory;

import org.seasar.struts.validator.annotation.tiger.Required;
import org.seasar.struts.validator.annotation.tiger.ValidateOrder;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class TestValidatorAnnotationOrderForm {

    @ValidateOrder(1)
    @Required
    public void setClassType(String classType) {
    }

    public String getClassType() {
        return null;
    }

    public static final int className_VALIDATOR_ORDER = 2;

    @Required
    public void setClassName(String className) {
    }

    public String getClassName() {
        return null;
    }

    @ValidateOrder(3)
    @Required
    public void setArg(String arg) {
    }

    public String getArg() {
        return null;
    }

}
