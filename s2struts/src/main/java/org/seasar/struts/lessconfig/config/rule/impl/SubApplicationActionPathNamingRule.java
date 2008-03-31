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

import org.seasar.framework.util.StringUtil;

/**
 * パスの区切り文字<code>/</code>とS2Struts内のパッケージ区切り文字である<code>_</code>を相互に変換します。
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class SubApplicationActionPathNamingRule extends DefaultActionPathNamingRule {

    protected static final char PATH_SEPARATOR = '/';

    protected String fromPathToActionName(String path) {
        String componentName = super.fromPathToActionName(path);
        componentName = componentName.replace(PATH_SEPARATOR, PACKAGE_SEPARATOR);
        int pos = componentName.lastIndexOf(PACKAGE_SEPARATOR);
        if (pos == -1) {
            return StringUtil.decapitalize(componentName);
        }
        return componentName.substring(0, pos + 1) + StringUtil.decapitalize(componentName.substring(pos + 1));
    }

    protected String fromActionNameToPath(String actionName) {
        String name = super.fromActionNameToPath(actionName);
        return name.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
    }

}
