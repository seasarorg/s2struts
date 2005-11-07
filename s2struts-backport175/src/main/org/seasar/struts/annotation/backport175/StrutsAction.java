package org.seasar.struts.annotation.backport175;

/**
 * @author Katsuhiko Nagashima
 */
public interface StrutsAction {

    /**
     * @org.codehaus.backport175.DefaultValue ("")
     */
    String path();

    /**
     * @org.codehaus.backport175.DefaultValue ("")
     */
    String name();

    /**
     * @org.codehaus.backport175.DefaultValue ("request")
     */
    String scope();

    /**
     * @org.codehaus.backport175.DefaultValue (true)
     */
    boolean validate();

    /**
     * @org.codehaus.backport175.DefaultValue ("")
     */
    String input();

    /**
     * @org.codehaus.backport175.DefaultValue ("")
     */
    String parameter();

    /**
     * @org.codehaus.backport175.DefaultValue ("")
     */
    String attribute();

    /**
     * @org.codehaus.backport175.DefaultValue ("")
     */
    String forward();

    /**
     * @org.codehaus.backport175.DefaultValue ("")
     */
    String include();

    /**
     * @org.codehaus.backport175.DefaultValue ("")
     */
    String prefix();

    /**
     * @org.codehaus.backport175.DefaultValue ("")
     */
    String suffix();

    /**
     * @org.codehaus.backport175.DefaultValue (false)
     */
    boolean unknown();

    /**
     * @org.codehaus.backport175.DefaultValue ("")
     */
    String roles();

}
