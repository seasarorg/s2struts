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
package org.seasar.struts.pojo.commands;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.pojo.PojoCommand;
import org.seasar.struts.pojo.PojoInvocation;
import org.seasar.struts.pojo.util.BindingUtil;

/**
 * POJO Actionの実行前に外部コンテキストから値をプロパティにインポートし、実行後にプロパティの値を外部コンテキストにエクスポートします。
 * 
 * @author Katsuhiko Nagashima
 */
public class PropertyBindingCommand implements PojoCommand {

    public String execute(PojoInvocation invocation) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ActionMapping mapping = invocation.getActionMapping();
        Object action = invocation.getActionInstance();

        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(action.getClass());
        BindingUtil.importProperties(action, container, beanDesc, mapping);
        String forward = invocation.execute();
        BindingUtil.exportProperties(action, container, beanDesc, mapping);

        return forward;
    }

}
