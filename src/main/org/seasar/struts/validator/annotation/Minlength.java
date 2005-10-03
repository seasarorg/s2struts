package org.seasar.struts.validator.annotation;

/**
 * @author Satoshi Kimura
 */
public interface Minlength extends CommonValidator {
    int value();

    /**
     * "minlength" or "minbytelength".
     * 
     * @org.codehaus.backport175.DefaultValue ("minlength")
     */
    String type();
    
    String charset();
}
