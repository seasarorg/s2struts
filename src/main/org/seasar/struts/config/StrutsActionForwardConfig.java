package org.seasar.struts.config;

/**
 * @author Satoshi Kimura
 */
public interface StrutsActionForwardConfig {
    String path();

    boolean DEFAULT_REDIRECT = false;
    boolean redirect();
}
