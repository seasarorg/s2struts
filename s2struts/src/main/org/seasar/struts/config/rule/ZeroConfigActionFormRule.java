package org.seasar.struts.config.rule;

import org.apache.struts.config.ModuleConfig;

/**
 * @author Satoshi Kimura
 */
public interface ZeroConfigActionFormRule {
    String getName(Class formClass, ModuleConfig config);
    
    boolean getRestricted(Class formClass, ModuleConfig config);
    
}
