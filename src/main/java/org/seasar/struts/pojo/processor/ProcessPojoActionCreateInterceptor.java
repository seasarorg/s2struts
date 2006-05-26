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
package org.seasar.struts.pojo.processor;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.struts.action.ActionMapping;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.pojo.PojoProcessAction;
import org.seasar.struts.util.ClassRegister;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class ProcessPojoActionCreateInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -8477606812838214606L;

    private ClassRegister classRegister;

    public void setClassRegister(ClassRegister classRegister) {
        this.classRegister = classRegister;
    }

    private PojoProcessAction pojoProcessAction;

    public void setPojoProcessAction(PojoProcessAction pojoProcessAction) {
        this.pojoProcessAction = pojoProcessAction;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        ActionMapping mapping = (ActionMapping) invocation.getArguments()[2];
        
        String type = mapping.getType();
        if (type != null) {
            Class action = this.classRegister.getClass(type);
            if (action.isInterface()) {
                // TODO pojoProcessAction.setServlet()の実行についてどうするか。
                // 今は利用してないので実行しなくてもOKだけど、将来必要になった場合、
                // どうするか考える必要がある。
                return this.pojoProcessAction;
            }
        }

        return invocation.proceed();
    }

}
