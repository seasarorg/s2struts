package org.seasar.struts.validator.annotation;

/**
 * @author Katsuhiko Nagashima
 */
public interface Validator {

    String name();

    /**
     * @org.codehaus.backport175.DefaultValue ("")
     */
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

    /**
     * @org.codehaus.backport175.DefaultValue ()
     */
    Variable[] vars();

}