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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.chain.commands.servlet.ExecuteAction;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * Actionを実行後、Pageに関連付けられている情報をクリアする。
 * 
 * この処理により、Pageタグによりつけられた前画面urlをAction実行後は引き継がないようになり、
 * 
 * 複数の連続した画面でPageタグを記述していた場合、エラー時にすべて最初の画面が表示される問題を解消する
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ExecuteActionAndClearPageValue extends ExecuteAction {

    protected ForwardConfig execute(ActionContext context, Action action, ActionConfig actionConfig,
            ActionForm actionForm) throws Exception {

        ForwardConfig forward = super.execute(context, action, actionConfig, actionForm);
        if (forward != null) {
            S2StrutsContextUtil.clearPageNameElementValue();
        }

        return forward;
    }

}
