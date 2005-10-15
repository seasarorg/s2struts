package org.seasar.struts.validator.annotation;

/**
 * @author Katsuhiko Nagashima
 */
public interface FloatRange extends CommonValidator {
    
    float min();

    float max();
}
