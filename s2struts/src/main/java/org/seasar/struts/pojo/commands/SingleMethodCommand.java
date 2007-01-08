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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.util.MethodUtil;
import org.seasar.struts.pojo.PojoCommand;
import org.seasar.struts.pojo.PojoInvocation;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class SingleMethodCommand implements PojoCommand {

    public String execute(PojoInvocation invocation) {
        Class actionInterface = invocation.getActionInterface();
        Object action = invocation.getActionInstance();

        Method[] methods = getMethods(actionInterface);
        if (methods.length == 1) {
            return (String) MethodUtil.invoke(methods[0], action, null);
        }

        return invocation.execute();
    }

    private Method[] getMethods(Class actionInterface) {
        if (actionInterface.isInterface()) {
            return actionInterface.getMethods();
        }

        List result = new ArrayList();
        Method[] methods = actionInterface.getMethods();
        for (int i = 0; i < methods.length; i++) {
            String methodName = methods[i].getName();
            if (methodName.startsWith("do") || methodName.startsWith("go") || methodName.startsWith("execute")) {
                result.add(methods[i]);
            }
        }

        return (Method[]) result.toArray(new Method[result.size()]);
    }

}
