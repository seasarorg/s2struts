package org.seasar.struts.lessconfig.config.rule.impl;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.ClassUtil;
import org.seasar.struts.lessconfig.config.rule.ActionFormNamingRule;
import org.seasar.struts.lessconfig.config.rule.CommonNamingRule;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class DefaultActionFormNamingRule implements ActionFormNamingRule {

    // private S2Container container;
    //
    // public void setContainer(S2Container container) {
    // this.container = container;
    // }

    private S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }

    public Class toComponentClass(String name) {
        S2Container container = getContainer();
        if (container.hasComponentDef(name)) {
            return container.getComponentDef(name).getComponentClass();
        }
        return null;
    }

    public String toActionFormName(Class formClass) {
        S2Container container = getContainer();
        if (!container.hasComponentDef(formClass)) {
            String name = ClassUtil.getShortClassName(formClass);
            return CommonNamingRule.decapitalizeName(name);
        }

        return container.getComponentDef(formClass).getComponentName();
    }

}
