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
package org.seasar.struts.processor.support;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;
import org.seasar.struts.lessconfig.config.rule.ActionPathNamingRule;
import org.seasar.struts.pojo.MethodBinding;
import org.seasar.struts.pojo.MethodBindingFactory;
import org.seasar.struts.pojo.factory.ActionAnnotationHandler;
import org.seasar.struts.pojo.factory.ActionAnnotationHandlerFactory;

/**
 * Actionのパスを解決します。
 * 
 * @author taedium
 */
public class ActionPathResolver {

    private static final Logger log = Logger.getLogger(ActionPathResolver.class);

    /**
     * Actionのパスを解決します。
     * 
     * @param request
     * @param path
     * @return
     */
    public static String resolve(HttpServletRequest request, String path) {
        MethodBinding methodBinding = MethodBindingFactory.getMethodBinding(request);
        if (methodBinding == null) {
            return path;
        }
        Method method = methodBinding.getMethod();
        ActionAnnotationHandler handler = ActionAnnotationHandlerFactory.getAnnotationHandler();
        String actualPath = handler.getPath(method);
        if (actualPath == null) {
            ActionPathNamingRule namingRule = getActionPathNamingRule();
            actualPath = namingRule.toActionPathName(methodBinding.getComponentClass());
        }
        log.log("DSTR0001", new Object[] { methodBinding.getExpression(), actualPath });
        return actualPath;
    }

    protected static ActionPathNamingRule getActionPathNamingRule() {
        return (ActionPathNamingRule) SingletonS2ContainerFactory.getContainer().getComponent(
                ActionPathNamingRule.class);
    }
}
