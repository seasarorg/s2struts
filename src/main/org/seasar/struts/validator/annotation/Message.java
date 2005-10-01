package org.seasar.struts.validator.annotation;

/**
 * @author Satoshi Kimura
 */
public interface Message {
    String key();

    String name();

    String bundle();

    /**
     * @org.codehaus.backport175.DefaultValue (true)
     */
    boolean resource();
}
