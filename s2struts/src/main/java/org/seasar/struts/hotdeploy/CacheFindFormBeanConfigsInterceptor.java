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
package org.seasar.struts.hotdeploy;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class CacheFindFormBeanConfigsInterceptor extends AbstractRequestCacheInterceptor {

    private static final long serialVersionUID = 8204183944777561362L;

    private static final String REQUEST_KEY = "org.seasar.struts.hotdeploy.CacheFindFormBeanConfigsInterceptor.REQUEST_KEY";

    private static final String CACHE_KEY = "CACHE_KEY";

    public Object invoke(MethodInvocation invocation) throws Throwable {
        return this.invokeAndCache(invocation, REQUEST_KEY, CACHE_KEY);
    }

}
