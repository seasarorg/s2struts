package org.seasar.struts.config;

import org.seasar.struts.Constants;

/**
 * @author Katsuhiko Nagashima
 */
public class StrutsActionPropertyConfigImpl implements StrutsActionPropertyConfig {
    
    private String scope;
    
    public StrutsActionPropertyConfigImpl() {
    }
    
    public StrutsActionPropertyConfigImpl(String scope) {
        this.scope = scope;
    }
    
    public boolean isSessionScope() {
        return Constants.SESSION.equalsIgnoreCase(this.scope);
    }
    
}
