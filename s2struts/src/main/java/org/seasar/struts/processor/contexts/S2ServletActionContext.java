/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.struts.processor.contexts;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.chain.contexts.ServletActionContext;

/**
 * {@link S2ServletWebContext}のラッパーです。
 * 
 * @author taedium
 */
public class S2ServletActionContext extends ServletActionContext {

    /**
     * @param context
     * @param request
     * @param response
     */
    public S2ServletActionContext(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        super(new S2ServletWebContext(context, request, response));
    }

}
