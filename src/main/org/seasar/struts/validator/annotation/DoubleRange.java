package org.seasar.struts.validator.annotation;

/**
 * @author Katsuhiko Nagashima
 */
public interface DoubleRange extends CommonValidator {
    
    double min();

    double max();
}
