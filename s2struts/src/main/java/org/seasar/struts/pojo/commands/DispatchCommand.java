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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.util.MethodUtil;
import org.seasar.struts.pojo.PojoCommand;
import org.seasar.struts.pojo.PojoInvocation;

/**
 * {@link ActionMapping#getParameter()}で返される値をキーとして取得できるリクエストパラメータと同じ名前のメソッドを実行します。
 * <p>
 * 適切なメソッドが存在しない場合、{@link PojoInvocation#execute()}を実行し、別の{@link PojoCommand}に処理を任せます。
 * </p>
 * 
 * @author Katsuhiko Nagashima
 */
public class DispatchCommand implements PojoCommand {

    public String execute(PojoInvocation invocation) {
        HttpServletRequest request = invocation.getRequest();
        ActionMapping mapping = invocation.getActionMapping();
        Class actionInterface = invocation.getActionInterface();
        Object action = invocation.getActionInstance();

        String param = mapping.getParameter();
        if (param == null) {
            return invocation.execute();
        }

        Method[] methods = actionInterface.getMethods();
        if (methods.length < 2) {
            return invocation.execute();
        }

        String methodName = request.getParameter(param);
        Method method = getMethod(methods, methodName);
        if (method == null) {
            return invocation.execute();
        }
        return (String) MethodUtil.invoke(method, action, null);
    }

    private Method getMethod(Method[] methods, String methodName) {
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().equals(methodName) && method.getParameterTypes().length == 0) {
                return method;
            }
        }
        return null;
    }

}
