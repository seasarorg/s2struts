package org.seasar.struts.lessconfig.config.rule;

public interface ActionFormNamingRule {

    Class toComponentClass(String name);

    String toActionFormName(Class formClass);

}
