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
package org.seasar.struts.processor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.tiles.TilesRequestProcessor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * S2Struts用に{@link TilesRequestProcessor}を拡張したクラスです。
 * 
 * @author Satoshi Kimura
 * @author higa
 */
public class S2TilesRequestProcessor extends TilesRequestProcessor {

    private ExternalRequestProcessor processor;

    /**
     * インスタンスを構築します。
     */
    public S2TilesRequestProcessor() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        this.processor = (ExternalRequestProcessor) container.getComponent(ExternalRequestProcessor.class);
    }

    public void init(ActionServlet arg0, ModuleConfig arg1) throws ServletException {
        this.processor.init(arg0, arg1);
    }

    public void destroy() {
        this.processor.destroy();
    }

    public void process(HttpServletRequest arg0, HttpServletResponse arg1) throws IOException, ServletException {
        this.processor.process(arg0, arg1);
    }

    public String toString() {
        return this.processor.toString();
    }

    public boolean equals(Object obj) {
        return this.processor.equals(obj);
    }

    public int hashCode() {
        return this.processor.hashCode();
    }

}