package org.seasar.struts.lessconfig.config.rule;

public interface ActionPathNamingRule {
    
    Class toComponentClass(String path);
    
    String toActionPathName(Class actionClass);

}
