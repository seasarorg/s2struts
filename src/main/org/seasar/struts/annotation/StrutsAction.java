package org.seasar.struts.annotation;


/**
 * @author Katsuhiko Nagashima
 */
public interface StrutsAction {

    String path();

    String name();

    /**
     * @org.codehaus.backport175.DefaultValue ("request")
     */
    String scope();

    /**
     * @org.codehaus.backport175.DefaultValue (true)
     */
    boolean validate();

    String input();

    String parameter();

    String attribute();

    String forward();

    String include();

    String prefix();

    String suffix();

    /**
     * @org.codehaus.backport175.DefaultValue (false)
     */
    boolean unknown();

    String roles();

}
