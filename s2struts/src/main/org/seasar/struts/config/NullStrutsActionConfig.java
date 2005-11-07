package org.seasar.struts.config;



/**
 * @author Satoshi Kimura
 */
public class NullStrutsActionConfig implements StrutsActionConfig {
    public String path() {
        return DEFAULT_PATH;
    }

    public String name() {
        return DEFAULT_NAME;
    }

    public String scope() {
        return DEFAULT_SCOPE;
    }

    public boolean validate() {
        return DEFAULT_VALIDATE;
    }

    public String input() {
        return DEFAULT_INPUT;
    }

    public String parameter() {
        return DEFAULT_PARAMETER;
    }

    public String attribute() {
        return DEFAULT_ATTRIBUTE;
    }

    public String forward() {
        return DEFAULT_FORWARD;
    }

    public String include() {
        return DEFAULT_INCLUDE;
    }

    public String prefix() {
        return DEFAULT_PREFIX;
    }

    public String suffix() {
        return DEFAULT_SUFFIX;
    }

    public boolean unknown() {
        return DEFAULT_UNKNOWN;
    }

    public String roles() {
        return DEFAULT_ROLES;
    }
}
