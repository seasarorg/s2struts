package org.seasar.struts.validator.annotation;

/**
 * @author Satoshi Kimura
 */
public interface Maxlength extends CommonValidator {
    int value();
    
    String charset();
}
