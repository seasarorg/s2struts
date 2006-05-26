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
public class ActionFormNamingRule extends AbstractNamingRule {

    private AutoActionFormRule formRule;

    public AutoActionFormRule getFormRule() {
        return formRule;
    }

    public void setFormRule(AutoActionFormRule formRule) {
        this.formRule = formRule;
    }

    public boolean isTargetClass(Class clazz) {
        boolean result = super.isTargetClass(getFormRule().getActionFormMiddlePackageName(), clazz);
        if (result) {
            String className = clazz.getName();
            return className.endsWith(getFormRule().getActionFormSuffix());
        } else {
            return false;
        }
    }

    public String defineName(Class clazz) {
        return super.defineName(getFormRule().getActionFormMiddlePackageName(), clazz);
    }

    public Class defineClass(String name) {
        return super.defineClass(getFormRule().getActionFormMiddlePackageName(), name);
    }

}
