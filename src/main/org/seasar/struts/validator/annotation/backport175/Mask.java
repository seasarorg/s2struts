package org.seasar.struts.validator.annotation.backport175;

/**
 * @author Satoshi Kimura
 * @org.seasar.struts.validator.annotation.backport175.ValidatorTarget
 */
public interface Mask {
    String pattern();

    String messageKey();

    /**
     * @org.codehaus.backport175.DefaultValue (true)
     */
    boolean resource();
}
