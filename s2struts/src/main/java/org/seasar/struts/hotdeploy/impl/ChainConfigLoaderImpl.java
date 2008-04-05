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
package org.seasar.struts.hotdeploy.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;

import org.apache.commons.chain.config.ConfigParser;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;
import org.seasar.framework.log.Logger;
import org.seasar.struts.hotdeploy.ChainConfigLoader;

/**
 * {@link ChainConfigLoader}の実装クラスです。
 * 
 * @author Katsuhiko Nagashima
 */
public class ChainConfigLoaderImpl implements ChainConfigLoader {

    private static final Logger log = Logger.getLogger(ChainConfigLoaderImpl.class);

    public void load() throws ServletException {
        initChain();
    }

    //
    //
    //

    private ServletContext servletContext;

    /**
     * {@link ServletContext}を設定します。
     * 
     * @param servletContext
     */
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private ServletContext getServletContext() {
        return this.servletContext;
    }

    private ActionServlet getActionServlet() {
        return (ActionServlet) this.servletContext.getAttribute(Globals.ACTION_SERVLET_KEY);
    }

    private ServletConfig getServletConfig() {
        return getActionServlet().getServletConfig();
    }

    //
    // copy from ActionServlet
    //

    protected String chainConfig = "org/apache/struts/chain/chain-config.xml";

    protected void initChain() throws ServletException {
        // Parse the configuration file specified by path or resource
        try {
            String value;

            value = getServletConfig().getInitParameter("chainConfig");

            if (value != null) {
                chainConfig = value;
            }

            ConfigParser parser = new ConfigParser();
            List urls = splitAndResolvePaths(chainConfig);
            URL resource;

            for (Iterator i = urls.iterator(); i.hasNext();) {
                resource = (URL) i.next();
                log.info("Loading chain catalog from " + resource);
                parser.parse(resource);
            }
        } catch (Exception e) {
            log.error("Exception loading resources", e);
            throw new ServletException(e);
        }
    }

    protected List splitAndResolvePaths(String paths) throws ServletException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        if (loader == null) {
            loader = this.getClass().getClassLoader();
        }

        ArrayList resolvedUrls = new ArrayList();

        URL resource;
        String path = null;

        try {
            // Process each specified resource path
            while (paths.length() > 0) {
                resource = null;

                int comma = paths.indexOf(',');

                if (comma >= 0) {
                    path = paths.substring(0, comma).trim();
                    paths = paths.substring(comma + 1);
                } else {
                    path = paths.trim();
                    paths = "";
                }

                if (path.length() < 1) {
                    break;
                }

                if (path.charAt(0) == '/') {
                    resource = getServletContext().getResource(path);
                }

                if (resource == null) {
                    if (log.isDebugEnabled()) {
                        log.debug("Unable to locate " + path + " in the servlet context, " + "trying classloader.");
                    }

                    Enumeration e = loader.getResources(path);

                    if (!e.hasMoreElements()) {
                        String msg = getActionServlet().getInternal().getMessage("configMissing", path);

                        log.error(msg);
                        throw new UnavailableException(msg);
                    } else {
                        while (e.hasMoreElements()) {
                            resolvedUrls.add(e.nextElement());
                        }
                    }
                } else {
                    resolvedUrls.add(resource);
                }
            }
        } catch (MalformedURLException e) {
            throw new ServletException(path, e);
        } catch (IOException e) {
            throw new ServletException(path, e);
        }

        return resolvedUrls;
    }

}
