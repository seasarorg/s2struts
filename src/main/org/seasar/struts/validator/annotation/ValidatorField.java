package org.seasar.struts.validator.annotation;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.validator.annotation.ValidatorTarget
 */
public interface ValidatorField {

    /**
     * @org.codehaus.backport175.DefaultValue ()
     */
    Validator[] validators();

}