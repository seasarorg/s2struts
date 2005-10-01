package org.seasar.struts.annotation;

/**
 * @author Katsuhiko Nagashima
 */
public interface StrutsActionForward {

    String path();

    /**
     * @org.codehaus.backport175.DefaultValue (false)
     */
    boolean redirect();

}
