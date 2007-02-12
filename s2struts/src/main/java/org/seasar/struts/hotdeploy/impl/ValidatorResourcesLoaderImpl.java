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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.validator.ValidatorResources;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.PlugInConfig;
import org.apache.struts.util.ModuleUtils;
import org.apache.struts.validator.ValidatorPlugIn;
import org.seasar.framework.log.Logger;
import org.seasar.struts.hotdeploy.ValidatorResourcesLoader;
import org.seasar.struts.util.S2StrutsContextUtil;
import org.xml.sax.SAXException;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class ValidatorResourcesLoaderImpl implements ValidatorResourcesLoader {

    private static final Logger log = Logger.getLogger(ValidatorResourcesLoaderImpl.class);

    public ValidatorResources load() throws IOException, ServletException {
        PlugInConfig plugInConfig = getPlugInConfig();
        if (plugInConfig == null) {
            return null;
        }

        String pathnames = (String) plugInConfig.getProperties().get("pathnames");
        ValidatorResources resources = initResources(pathnames);
        return resources;
    }

    public PlugInConfig getPlugInConfig() {
        ModuleConfig config = ModuleUtils.getInstance().getModuleConfig(S2StrutsContextUtil.getRequest());

        PlugInConfig[] plugInConfigs = config.findPlugInConfigs();
        for (int i = 0; i < plugInConfigs.length; i++) {
            if (plugInConfigs[i].getClassName().equals(ValidatorPlugIn.class.getName())) {
                return plugInConfigs[i];
            }
        }
        return null;
    }

    //
    //
    //

    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private ServletContext getServletContext() {
        return this.servletContext;
    }

    //
    // Copy from ValidatorPlugIn
    //

    /**
     * Delimitter for Validator resources.
     */
    private static final String RESOURCE_DELIM = ",";

    /**
     * Initialize the validator resources for this module.
     * 
     * @throws IOException
     *             if an input/output error is encountered
     * @throws ServletException
     *             if we cannot initialize these resources
     */
    protected ValidatorResources initResources(String pathnames) throws IOException, ServletException {
        if ((pathnames == null) || (pathnames.length() <= 0)) {
            return null;
        }

        StringTokenizer st = new StringTokenizer(pathnames, RESOURCE_DELIM);

        List urlList = new ArrayList();

        try {
            while (st.hasMoreTokens()) {
                String validatorRules = st.nextToken().trim();

                if (log.isInfoEnabled()) {
                    log.info("Loading validation rules file from '" + validatorRules + "'");
                }

                URL input = getServletContext().getResource(validatorRules);

                // If the config isn't in the servlet context, try the class
                // loader which allows the config files to be stored in a jar
                if (input == null) {
                    input = getClass().getResource(validatorRules);
                }

                if (input != null) {
                    urlList.add(input);
                } else {
                    throw new ServletException("Skipping validation rules file from '" + validatorRules
                            + "'.  No url could be located.");
                }
            }

            int urlSize = urlList.size();
            String[] urlArray = new String[urlSize];

            for (int urlIndex = 0; urlIndex < urlSize; urlIndex++) {
                URL url = (URL) urlList.get(urlIndex);

                urlArray[urlIndex] = url.toExternalForm();
            }

            return new ValidatorResources(urlArray);
        } catch (SAXException sex) {
            log.error("Skipping all validation", sex);
            throw new ServletException(sex);
        }
    }

}
