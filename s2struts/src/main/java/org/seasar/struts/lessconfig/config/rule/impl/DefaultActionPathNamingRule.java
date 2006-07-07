package org.seasar.struts.lessconfig.config.rule.impl;

import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.lessconfig.config.rule.ActionPathNamingRule;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class DefaultActionPathNamingRule implements ActionPathNamingRule {

    // private S2Container container;
    //
    // public void setContainer(S2Container container) {
    // this.container = container;
    // }

    private S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }

    public Class toComponentClass(ModuleConfig config, String path) {
        S2Container container = getContainer();

        String componentName = config.getPrefix() + path;
        if (container.hasComponentDef(componentName)) {
            return container.getComponentDef(componentName).getComponentClass();
        }

        componentName = path;
        if (container.hasComponentDef(componentName)) {
            return container.getComponentDef(componentName).getComponentClass();
        }

        componentName = path.substring(1) + "Action";
        if (container.hasComponentDef(componentName)) {
            Class clazz = container.getComponentDef(componentName).getComponentClass();
            if (!clazz.getName().endsWith("Impl")) {
                return clazz;
            }

            Class[] interfaces = clazz.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                if (interfaces[i].getName().endsWith("Action")) {
                    return interfaces[i];
                }
            }
        }
        return null;
    }

    public String toActionPathName(Class actionClass) {
        S2Container container = getContainer();

        if (!container.hasComponentDef(actionClass)) {
            return null;
        }

        String componentName = container.getComponentDef(actionClass).getComponentName();
        if (componentName == null) {
            return null;
        }

        if (componentName.startsWith("/")) {
            return componentName;
        }

        String result = componentName.replaceFirst("Action$", "");
        return "/" + result;
    }

}
