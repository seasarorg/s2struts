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
package org.seasar.httpunit.mock;

import javax.servlet.ServletContext;

/**
 * @author Satoshi Kimura
 */
public class HttpSessionDelegator extends AttributeFacade {
    private MockHttpSession session;
    private ServletContext context;

    public HttpSessionDelegator() {
    }

    public void setExtendHttpSession(MockHttpSession session) {
        this.session = session;
    }

    public ServletContext getServletContext() {
        return context;
    }

    public void setServletContext(ServletContext context) {
        this.context = context;
    }

    public void putValue(String name, Object value) {
        setAttribute(name, value);
    }

    public Object getValue(String name) {
        return getAttribute(name);
    }

    public void removeValue(String name) {
        removeAttribute(name);
    }

    public String[] getValueNames() {
        return ((String[]) attributes.keySet().toArray(new String[0]));
    }

    public void invalidate() {
        session.setValid(true);
    }
}