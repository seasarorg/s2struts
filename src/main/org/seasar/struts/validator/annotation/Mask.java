package org.seasar.struts.validator.annotation;

/**
 * @author Satoshi Kimura
 */
public interface Mask extends CommonValidator {
    String pattern();

    String messageKey();

    /**
     * @org.codehaus.backport175.DefaultValue (true)
     */
    boolean resource();
}
