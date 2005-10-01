package org.seasar.struts.validator.annotation;

/**
 * @author Satoshi Kimura
 */
public interface Range extends CommonValidator {
    /**
     * @org.codehaus.backport175.DefaultValue ("doubleRange")
     */
    String type();
    
    double min();

    double max();
}
