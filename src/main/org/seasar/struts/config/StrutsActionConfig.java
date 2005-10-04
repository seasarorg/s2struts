package org.seasar.struts.config;

import org.seasar.struts.Constants;

/**
 * @author Satoshi Kimura
 */
public interface StrutsActionConfig {
    String DEFAULT_PATH = "";
    String path();

    String DEFAULT_NAME = "";
    String name();

    String DEFAULT_SCOPE = Constants.REQUEST;
    String scope();

    boolean DEFAULT_VALIDATE = true;
    boolean validate();

    String DEFAULT_INPUT = "";
    String input();

    String DEFAULT_PARAMETER = "";
    String parameter();

    String DEFAULT_ATTRIBUTE = "";
    String attribute();

    String DEFAULT_FORWARD = "";
    String forward();

    String DEFAULT_INCLUDE = "";
    String include();

    String DEFAULT_PREFIX = "";
    String prefix();

    String DEFAULT_SUFFIX = "";
    String suffix();

    boolean DEFAULT_UNKNOWN = false;
    boolean unknown();

    String DEFAULT_ROLES = "";
    String roles();

}
