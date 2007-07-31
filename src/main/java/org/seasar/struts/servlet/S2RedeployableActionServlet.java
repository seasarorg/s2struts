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
package org.seasar.struts.servlet;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.actions.RedeployableActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.S2StrutsInitializer;
import org.seasar.struts.processor.RequestProcessorFactory;

/**
 * @author Satoshi Kimura
 */
public class S2RedeployableActionServlet extends RedeployableActionServlet {

    private static final long serialVersionUID = S2RedeployableActionServlet.class
            .hashCode();

    /**
     * 
     */
    public S2RedeployableActionServlet() {
        super();
    }

    /**
     * <p>
     * Look up and return the {@link RequestProcessor}responsible for the
     * specified module, creating a new one if necessary.
     * </p>
     * 
     * @param config
     *            The module configuration for which to acquire and return a
     *            RequestProcessor.
     * @exception ServletException
     *                if we cannot instantiate a RequestProcessor instance
     */
    protected synchronized RequestProcessor getRequestProcessor(
            ModuleConfig moduleConfig) throws ServletException {
        return getRequestProcessorFactory().getRequestProcessor(moduleConfig,
                getServletContext(), this);
    }

    /**
     * @see ActionServlet#init()
     * @see S2StrutsInitializer#registerActionClass(ActionServlet)
     */
    public void init() throws ServletException {
        try {
            super.init();
            S2StrutsInitializer.initServlet(this);
        } catch (ServletException e) {
            e.printStackTrace();
            throw e;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static RequestProcessorFactory getRequestProcessorFactory() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (RequestProcessorFactory) container
                .getComponent(RequestProcessorFactory.class);
    }

}