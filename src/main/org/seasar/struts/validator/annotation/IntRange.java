package org.seasar.struts.validator.annotation;

/**
 * @author Katsuhiko Nagashima
 */
public interface IntRange extends CommonValidator {
    
    int min();

    int max();
}
