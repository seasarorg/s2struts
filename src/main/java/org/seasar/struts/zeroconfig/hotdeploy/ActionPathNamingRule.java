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
package org.seasar.struts.zeroconfig.hotdeploy;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class ActionPathNamingRule extends AbstractNamingRule {

    private AutoActionRule actionRule;

    public AutoActionRule getActionRule() {
        return actionRule;
    }

    public void setActionRule(AutoActionRule actionRule) {
        this.actionRule = actionRule;
    }

    public boolean isTargetClass(Class clazz) {
        boolean result = super.isTargetClass(getActionRule().getActionMiddlePackageName(), clazz);
        if (result) {
            String className = clazz.getName();
            return className.endsWith(getActionRule().getActionSuffix());
        } else {
            return false;
        }
    }

    public String defineName(Class clazz) {
        String result = super.defineName(getActionRule().getActionMiddlePackageName(), clazz);

        return "/" + result.replaceAll(getActionRule().getActionSuffix() + "$", "");
    }

    public Class defineClass(String name) {
        String className = name.substring(1) + getActionRule().getActionSuffix();
        return super.defineClass(getActionRule().getActionMiddlePackageName(), className);
    }

}
