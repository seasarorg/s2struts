package org.seasar.struts.annotation;

/**
 * @author Katsuhiko Nagashima
 */
public interface StrutsActionForm {

    String name();

    /**
     * @org.codehaus.backport175.DefaultValue (false)
     */
    boolean restricted();

}
