package org.seasar.struts.annotation;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public enum ScopeType {
    
    REQUEST,
    SESSION;
    
    public String getScopeMode() {
        return toString().toLowerCase();
    }

}
