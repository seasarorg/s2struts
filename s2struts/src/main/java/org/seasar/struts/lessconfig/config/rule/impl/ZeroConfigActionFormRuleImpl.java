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
import org.seasar.struts.lessconfig.config.StrutsActionFormConfig;
import org.seasar.struts.lessconfig.config.rule.ActionFormNamingRule;
import org.seasar.struts.lessconfig.config.rule.ZeroConfigActionFormRule;

/**
 * @author Satoshi Kimura
 */
public class ZeroConfigActionFormRuleImpl implements ZeroConfigActionFormRule {

    private ActionFormNamingRule namingRule;

    public void setNamingRule(ActionFormNamingRule namingRule) {
        this.namingRule = namingRule;
    }

    public String getName(Class formClass, ModuleConfig config) {
        return this.namingRule.toActionFormName(formClass);
    }

    public boolean getRestricted(Class formClass, ModuleConfig config) {
        return StrutsActionFormConfig.DEFAULT_RESTRICTED;
    }

}
