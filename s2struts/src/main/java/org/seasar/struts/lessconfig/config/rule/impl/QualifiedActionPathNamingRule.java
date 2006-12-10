/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.struts.lessconfig.config.rule.impl;

import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.convention.NamingConvention;
import org.seasar.struts.lessconfig.config.rule.ActionPathNamingRule;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class QualifiedActionPathNamingRule implements ActionPathNamingRule {

    private NamingConvention namingConvention;

    public void setNamingConvention(NamingConvention namingConvention) {
        this.namingConvention = namingConvention;
    }

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

        if (path.startsWith(this.namingConvention.getViewRootPath())
                && path.endsWith(this.namingConvention.getViewExtension())) {

            componentName = this.namingConvention.fromPathToActionName(path);
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
        }
        return null;
    }

    public String toComponentName(Class actionClass) {
        S2Container container = getContainer();

        if (!container.hasComponentDef(actionClass)) {
            return null;
        }

        return container.getComponentDef(actionClass).getComponentName();
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

        if (componentName.endsWith(this.namingConvention.getActionSuffix())) {
            String result = this.namingConvention.fromActionNameToPath(componentName);
            return result;
        }
        return null;
    }

}