package org.seasar.struts.validator.annotation;

/**
 * @author Satoshi Kimura
 */
public interface Maxlength extends CommonValidator {
    int value();
    
    /**
     * "maxlength" or "maxbytelength".
     * 
     * @org.codehaus.backport175.DefaultValue ("maxlength")
     */
    String type();
}
