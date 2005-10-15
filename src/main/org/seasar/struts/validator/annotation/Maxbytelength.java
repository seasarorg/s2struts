package org.seasar.struts.validator.annotation;

/**
 * @author Katsuhiko Nagashima
 */
public interface Maxbytelength extends CommonValidator {

    int value();
    
    String charset();
}
