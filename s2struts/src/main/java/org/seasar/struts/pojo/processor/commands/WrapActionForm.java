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
package org.seasar.struts.pojo.processor.commands;

import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.apache.struts.chain.commands.ActionCommandBase;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.struts.pojo.util.BeanValidatorFormUtil;

/**
 * POJO FormがBeanValidatorFormにラップされずにrequest又はsessionに格納されていた場合は、BeanValidatorFormにラップし格納しなおす。
 * 
 * request、sessionにPOJO Formをそのまま格納しても動作するようにしPOJO Formを使いやすくするため。
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class WrapActionForm extends ActionCommandBase {

    public boolean execute(ActionContext actionContext) throws Exception {
        ActionConfig actionConfig = actionContext.getActionConfig();
        String name = actionConfig.getName();
        if (name == null) {
            return false;
        }

        FormBeanConfig formBeanConfig = actionConfig.getModuleConfig().findFormBeanConfig(name);
        if (formBeanConfig == null) {
            return false;
        }

        Map scope = actionContext.getScope(actionConfig.getScope());

        Object instance = scope.get(actionConfig.getAttribute());
        if (instance == null) {
            return false;
        }
        if (instance instanceof ActionForm) {
            return false;
        }

        BeanValidatorForm form = BeanValidatorFormUtil.toBeanValidatorForm(null, instance);
        scope.put(actionConfig.getAttribute(), form);
        return false;
    }

}
