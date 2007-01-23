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
package org.seasar.struts.processor.commands;

import org.apache.struts.chain.commands.ActionCommandBase;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * S2ContainerにHttpServletRequestを設定しなおす。
 * 
 * Struts内でHttpServletRequestをMultipartRequestWrapperにラップしている場合があるため。
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class SetHttpServletRequest extends ActionCommandBase {

    public boolean execute(ActionContext actionContext) throws Exception {
        ServletActionContext saContext = (ServletActionContext) actionContext;
        S2StrutsContextUtil.setRequest(saContext.getRequest());
        return false;
    }

}
