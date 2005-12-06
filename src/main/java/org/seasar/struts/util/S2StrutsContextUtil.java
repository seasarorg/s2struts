/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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
package org.seasar.struts.util;

import org.apache.struts.config.ForwardConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.context.ContentsType;
import org.seasar.struts.context.S2StrutsContext;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public abstract class S2StrutsContextUtil {

    public static void clear(ContentsType type) {
        getContext().clear(type);
    }

    public static void setPath(ForwardConfig forward) {
        getContext().setPath(forward.getPath());
    }

    public static void setPath(String path) {
        getContext().setPath(path);
    }

    public static String getCurrentInputPath() {
        return getContext().getCurrentInputPath();
    }
    
    public static String getPreviousInputPath() {
        return getContext().getPreviousInputPath();
    }
    
    public static void setMethodBindingExpression(String key, String value, String methodBindingExpression) {
        getContext().setMethodBindingExpression(key, value, methodBindingExpression);
    }
    
    public static String getMethodBindingExpression() {
        return getContext().getMethodBindingExpression();
    }
    
    public static boolean existMethodBindingExpression() {
        return getContext().existMethodBindingExpression();
    }

    private static S2StrutsContext getContext() {
        return (S2StrutsContext) getContainer().getComponent(S2StrutsContext.class);
    }

    private static S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }
}
