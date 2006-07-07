package org.seasar.struts.lessconfig.config.rule;

import org.apache.struts.config.ModuleConfig;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public interface ActionPathNamingRule {

    Class toComponentClass(ModuleConfig config, String path);

    String toActionPathName(Class actionClass);

}
