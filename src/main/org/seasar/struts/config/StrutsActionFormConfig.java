package org.seasar.struts.config;

/**
 * @author Satoshi Kimura
 */
public interface StrutsActionFormConfig {
    String DEFAULT_NAME = "";
    String name();

    boolean DEFAULT_RESTRICTED = false;
    boolean restricted();
}
