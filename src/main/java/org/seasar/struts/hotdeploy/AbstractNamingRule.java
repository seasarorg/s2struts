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
package org.seasar.struts.hotdeploy;

import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public abstract class AbstractNamingRule implements NamingRule {

    private AutoStrutsConfigRegisterRule registerRule;

    public void setRegisterRule(AutoStrutsConfigRegisterRule registerRule) {
        this.registerRule = registerRule;
    }

    protected boolean isTargetClass(String middlePackageName, Class clazz) {
        String name = clazz.getName();
        int pos = name.indexOf(getPackageName(middlePackageName));
        return (pos > -1);
    }

    protected String defineName(String middlePackageName, Class clazz) {
        String packageName = getPackageName(middlePackageName);
        String name = clazz.getName();
        int pos = name.indexOf(packageName);
        if (pos < 0) {
            throw new IllegalArgumentException(name);
        }
        name = name.substring(pos + packageName.length() + 1);
        String[] names = StringUtil.split(name, ".");
        String lastName = names[names.length - 1];
        lastName = StringUtil.decapitalize(lastName);

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < names.length - 1; i++) {
            result.append(names[i]).append("_");
        }
        result.append(lastName);

        return result.toString();
    }

    protected Class defineClass(String middlePackageName, String name) {
        String packageName = getPackageName(middlePackageName);
        StringBuffer className = new StringBuffer();
        className.append(packageName).append(".");

        String[] names = StringUtil.split(name, "_");
        String lastName = names[names.length - 1];
        lastName = StringUtil.capitalize(lastName);

        for (int i = 0; i < names.length - 1; i++) {
            className.append(names[i]).append(".");
        }
        className.append(lastName);

        return ClassUtil.forName(className.toString());
    }

    private String getPackageName(String middlePackageName) {
        return this.registerRule.getRootPackageName() + "." + middlePackageName;
    }

}
