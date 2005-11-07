package org.seasar.struts.validator.annotation.backport175;

/**
 * @author Katsuhiko Nagashima
 */
public interface Variable {

    String name();

    String value();

    /**
     * @org.codehaus.backport175.DefaultValue ("")
     */
    String key();

    /**
     * @org.codehaus.backport175.DefaultValue (true)
     */
    boolean resource();

    /**
     * @org.codehaus.backport175.DefaultValue (true)
     */
    boolean arg();

}