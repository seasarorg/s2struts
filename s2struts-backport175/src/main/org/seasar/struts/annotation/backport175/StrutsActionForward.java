package org.seasar.struts.annotation.backport175;

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
