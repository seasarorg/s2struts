package org.seasar.struts.validator.annotation;

/**
 * @author Katsuhiko Nagashima
 */
public interface ValidatorField extends CommonValidator {

    /**
     * @org.codehaus.backport175.DefaultValue ()
     */
    Validator[] validators();

}