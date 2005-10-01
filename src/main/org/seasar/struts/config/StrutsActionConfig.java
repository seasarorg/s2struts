package org.seasar.struts.config;

import org.seasar.struts.Constants;

/**
 * @author Satoshi Kimura
 */
public interface StrutsActionConfig {
    String DEFAULT_PATH = null;
    String path();

    String DEFAULT_NAME = null;
    String name();

    String DEFAULT_SCOPE = Constants.REQUEST;
    String scope();

    boolean DEFAULT_VALIDATE = true;
    boolean validate();

    String DEFAULT_INPUT = null;
    String input();

    String DEFAULT_PARAMETER = null;
    String parameter();

    String DEFAULT_ATTRIBUTE = null;
    String attribute();

    String DEFAULT_FORWARD = null;
    String forward();

    String DEFAULT_INCLUDE = null;
    String include();

    String DEFAULT_PREFIX = null;
    String prefix();

    String DEFAULT_SUFFIX = null;
    String suffix();

    boolean DEFAULT_UNKNOWN = false;
    boolean unknown();

    String DEFAULT_ROLES = null;
    String roles();

}
