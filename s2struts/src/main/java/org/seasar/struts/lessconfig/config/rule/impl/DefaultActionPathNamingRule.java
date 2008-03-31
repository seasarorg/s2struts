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
 * {@link ActionPathNamingRule}の実装クラスです。
 * 
 * @author Katsuhiko Nagashima
 */
public class DefaultActionPathNamingRule implements ActionPathNamingRule {

    protected static final String ACTION_SUFFIX = "Action";

    protected static final String IMPL_SUFFIX = "Impl";

    protected static final String VIEW_PREFIX = "/";

    protected static final String VIEW_ROOT_PATH = "" + VIEW_PREFIX;

    protected static final char PACKAGE_SEPARATOR = '_';

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

        if (path.startsWith(VIEW_ROOT_PATH)) {
            componentName = fromPathToActionName(path);
            if (container.hasComponentDef(componentName)) {
                Class clazz = container.getComponentDef(componentName).getComponentClass();
                if (!clazz.getName().endsWith(IMPL_SUFFIX)) {
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
                    if (componentName.endsWith(PACKAGE_SEPARATOR + interfaceName)) {
                        return interfaces[i];
                    }
                }
            }
        }
        return null;
    }

    protected String fromPathToActionName(String path) {
        if (!path.startsWith(VIEW_ROOT_PATH)) {
            throw new IllegalArgumentException(path);
        }
        return path.substring(VIEW_ROOT_PATH.length()) + ACTION_SUFFIX;
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

        if (componentName.startsWith(VIEW_PREFIX)) {
            return componentName;
        }

        if (componentName.endsWith(ACTION_SUFFIX)) {
            return fromActionNameToPath(componentName);
        }
        return VIEW_PREFIX + componentName;
    }

    protected String fromActionNameToPath(String actionName) {
        if (!actionName.endsWith(ACTION_SUFFIX)) {
            throw new IllegalArgumentException(actionName);
        }
        String name = actionName.substring(0, actionName.length() - ACTION_SUFFIX.length());
        return VIEW_ROOT_PATH + name;
    }

}
