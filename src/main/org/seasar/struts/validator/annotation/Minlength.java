package org.seasar.struts.validator.annotation;

/**
 * @author Satoshi Kimura
 */
public interface Minlength extends CommonValidator {
    int value();

    String charset();
}