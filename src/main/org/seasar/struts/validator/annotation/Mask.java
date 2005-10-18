package org.seasar.struts.validator.annotation;

/**
 * @author Satoshi Kimura
 * @org.seasar.struts.validator.annotation.ValidatorTarget
 */
public interface Mask {
    String pattern();

    String messageKey();

    /**
     * @org.codehaus.backport175.DefaultValue (true)
     */
    boolean resource();
}
