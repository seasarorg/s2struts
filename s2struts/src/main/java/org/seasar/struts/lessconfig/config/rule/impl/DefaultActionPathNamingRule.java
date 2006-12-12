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
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.lessconfig.config.rule.ActionPathNamingRule;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class DefaultActionPathNamingRule implements ActionPathNamingRule {

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

            //
            // 複数のActionインターフェースが実装されている場合の対応
            // pathから求めたコンポーネント名と一致したインターフェースか
            // packageを含まないコンポーネント名と一致したインターフェースを
            // Actionインターフェースとする
            //
            Class[] interfaces = clazz.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                String interfaceName = StringUtil.decapitalize(ClassUtil.getShortClassName(interfaces[i]));
                if (componentName.equals(interfaceName)) {
                    return interfaces[i];
                }
                if (componentName.endsWith("_" + interfaceName)) {
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
