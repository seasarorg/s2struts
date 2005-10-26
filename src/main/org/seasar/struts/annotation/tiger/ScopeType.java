package org.seasar.struts.annotation.tiger;

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
