package org.seasar.struts.config;

import org.seasar.struts.Constants;

/**
 * @author Katsuhiko Nagashima
 */
public class ActionPropertyConfigImpl implements ActionPropertyConfig {
    
    private String scope;
    
    public ActionPropertyConfigImpl() {
    }
    
    public ActionPropertyConfigImpl(String scope) {
        this.scope = scope;
    }
    
    public boolean isSessionScope() {
        return Constants.SESSION.equalsIgnoreCase(this.scope);
    }
    
}
