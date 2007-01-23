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

import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.commands.ActionCommandBase;
import org.apache.struts.chain.contexts.ActionContext;
import org.seasar.struts.pojo.processor.ActionMappingWrapper;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * S2StrutsContextに従ってinputを設定する。
 * 
 * ActionConfigのinput属性を無設定化するため。
 * 
 * ActionConfigのinput属性が設定されていなくS2StrutsContextに戻り先のinputPathが登録されている場合、
 * ActionConfigのinput属性をS2StrutsContextのinputPathで上書する。
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class SetActionConfigInput extends ActionCommandBase {

    public boolean execute(ActionContext actionContext) throws Exception {
        ActionMapping mapping = (ActionMapping) actionContext.getActionConfig();

        String input = mapping.getInput();
        if (input == null && S2StrutsContextUtil.getPreviousInputPath() != null) {
            ActionMappingWrapper wrapper = new ActionMappingWrapper(mapping);
            input = S2StrutsContextUtil.getPreviousInputPath();
            wrapper.setInput(input);
            actionContext.setActionConfig(wrapper);
        }
        return false;
    }

}
