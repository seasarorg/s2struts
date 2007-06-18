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
import org.apache.struts.validator.BeanValidatorForm;
import org.seasar.struts.pojo.form.S2BeanValidatorForm;

/**
 * BeanValidatorFormがrequest又はsessionに格納されていた場合は、S2BeanValidatorFormにラップし格納しなおす。
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class WrapS2BeanValidatorForm extends ActionCommandBase {

    public boolean execute(ActionContext actionContext) throws Exception {
        ActionForm form = actionContext.getActionForm();

        if (form instanceof S2BeanValidatorForm) {
            return false;
        }

        if (!(form instanceof BeanValidatorForm)) {
            return false;
        }

        form = new S2BeanValidatorForm((BeanValidatorForm) form);

        ActionConfig actionConfig = actionContext.getActionConfig();
        Map scope = actionContext.getScope(actionConfig.getScope());

        actionContext.setActionForm(form);
        scope.put(actionConfig.getAttribute(), form);

        return false;
    }

}
