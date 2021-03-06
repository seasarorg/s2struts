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
package org.seasar.struts.hotdeploy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.hotdeploy.ChainConfigLoader;

/**
 * S2StrutsでHOT deployを実現するために必要な{@link Filter}です。
 * <p>
 * リクエストの度にCommons Chainの設定の再ロードを実行します。
 * </p>
 * 
 * @author Katsuhiko Nagashima
 */
public class S2StrutsHotdeployFilter implements Filter {

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        initChain();
        chain.doFilter(request, response);
    }

    private void initChain() throws ServletException {
        S2Container container = getContainer();
        if (!container.hasComponentDef(ChainConfigLoader.class)) {
            return;
        }

        ChainConfigLoader configLoader = (ChainConfigLoader) container.getComponent(ChainConfigLoader.class);
        configLoader.load();
    }

    private S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }

}
