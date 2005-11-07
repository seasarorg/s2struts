package org.seasar.struts.config;


/**
 * @author Satoshi Kimura
 */
public class NullStrutsActionFormConfig implements StrutsActionFormConfig {
    public String name() {
        return DEFAULT_NAME;
    }

    public boolean restricted() {
        return DEFAULT_RESTRICTED;
    }
}
