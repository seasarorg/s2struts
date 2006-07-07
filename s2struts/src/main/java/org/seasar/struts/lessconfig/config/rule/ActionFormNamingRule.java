package org.seasar.struts.lessconfig.config.rule;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public interface ActionFormNamingRule {

    Class toComponentClass(String name);

    String toActionFormName(Class formClass);

}
