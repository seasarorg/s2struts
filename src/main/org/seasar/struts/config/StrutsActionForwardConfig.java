package org.seasar.struts.config;

/**
 * @author Satoshi Kimura
 */
public interface StrutsActionForwardConfig {
    String path();

    boolean DEFALUT_REDIRECT = false;
    boolean redirect();
}
