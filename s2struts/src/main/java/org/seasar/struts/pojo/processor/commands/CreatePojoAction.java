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
import org.apache.struts.chain.commands.AbstractCreateAction;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ActionConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.action.ClassRegister;
import org.seasar.struts.pojo.PojoProcessAction;

/**
 * 実行するActionがPOJO Actionの場合、POJO Actionを実行するためのアダプタとなるPojoProcessActionを生成し、ActionContextに設定する。
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class CreatePojoAction extends AbstractCreateAction {

    protected Action getAction(ActionContext context, String type, ActionConfig actionConfig) throws Exception {
        if (type != null) {
            Class action = getClassRegister().getClass(type);
            if (action.isInterface() || !Action.class.isAssignableFrom(action)) {
                return getPojoProcessAction();
            }
        }

        return null;
    }

    //
    //
    //

    private ClassRegister getClassRegister() {
        return (ClassRegister) getContainer().getComponent(ClassRegister.class);
    }

    private PojoProcessAction getPojoProcessAction() {
        return (PojoProcessAction) getContainer().getComponent(PojoProcessAction.class);
    }

    private S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }

}
