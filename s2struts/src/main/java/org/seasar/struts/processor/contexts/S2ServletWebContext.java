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

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.chain.web.servlet.ServletWebContext;
import org.seasar.framework.container.ExternalContext;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * S2コンテナが管理する{@link ExternalContext 外部コンテキスト}をラップした{@link Context}です。
 * <p>
 * このクラスは{@link #getApplicationScope()}や{@link #getSessionScope()}で、HOT
 * deployに対応した{@link Map}を返すことを目的としています。
 * </p>
 * 
 * @author taedium
 */
public class S2ServletWebContext extends ServletWebContext {

    private static final long serialVersionUID = 1L;

    protected ExternalContext externalContext;

    /**
     * @param context
     * @param request
     * @param response
     */
    public S2ServletWebContext(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        initialize(context, request, response);
    }

    public Map getApplicationScope() {
        return externalContext.getApplicationMap();
    }

    public Map getCookies() {
        return externalContext.getRequestCookieMap();
    }

    public Map getHeader() {
        return externalContext.getRequestHeaderMap();
    }

    public Map getHeaderValues() {
        return externalContext.getRequestHeaderValuesMap();
    }

    public Map getInitParam() {
        return externalContext.getInitParameterMap();
    }

    public Map getParam() {
        return externalContext.getRequestParameterMap();
    }

    public Map getParamValues() {
        return externalContext.getRequestParameterValuesMap();
    }

    public Map getRequestScope() {
        return externalContext.getRequestMap();
    }

    public Map getSessionScope() {
        return externalContext.getSessionMap();
    }

    public ServletContext getContext() {
        return (ServletContext) externalContext.getApplication();
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) externalContext.getRequest();
    }

    public HttpServletResponse getResponse() {
        return (HttpServletResponse) externalContext.getResponse();
    }

    public void initialize(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        externalContext = SingletonS2ContainerFactory.getExternalContext();
        // HttpServletRequestがMultipartRequestWrapperでラップされていることを考慮し設定しなおす。
        externalContext.setRequest(request);
    }

    public void release() {
        externalContext = null;
    }

}
