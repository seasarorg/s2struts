package org.seasar.struts.validator.annotation;

/**
 * @author Satoshi Kimura
 */
public interface Args {
    String keys();
    
    /**
     * @org.codehaus.backport175.DefaultValue (true)
     */
    boolean resource();
}
