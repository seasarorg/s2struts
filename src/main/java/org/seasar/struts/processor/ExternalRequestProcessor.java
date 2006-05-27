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

import org.apache.commons.logging.Log;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.InvalidCancelException;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;

/**
 * @author Satoshi Kimura
 */
public interface ExternalRequestProcessor {
    
    /**
     * @see org.apache.struts.action.RequestProcessor#init(ActionServlet, ModuleConfig)
     */
    void init(ActionServlet servlet, ModuleConfig config) throws ServletException;
    
    /**
     * @see org.apache.struts.action.RequestProcessor#destroy()
     */
    void destroy();

    /**
     * @see org.apache.struts.action.RequestProcessor#process(HttpServletRequest, HttpServletResponse)
     */
    void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    /**
     * @see org.apache.struts.action.RequestProcessor#processMultipart(HttpServletRequest)
     */
    HttpServletRequest processMultipart(HttpServletRequest request);

    /**
     * @see org.apache.struts.action.RequestProcessor#processPath(HttpServletRequest, HttpServletResponse)
     */
    String processPath(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * @see org.apache.struts.action.RequestProcessor#processLocale(HttpServletRequest, HttpServletResponse)
     */
    void processLocale(HttpServletRequest request, HttpServletResponse response);

    /**
     * @see org.apache.struts.action.RequestProcessor#processContent(HttpServletRequest, HttpServletResponse)
     */
    void processContent(HttpServletRequest request, HttpServletResponse response);

    /**
     * @see org.apache.struts.action.RequestProcessor#processNoCache(HttpServletRequest, HttpServletResponse)
     */
    void processNoCache(HttpServletRequest request, HttpServletResponse response);

    /**
     * @see org.apache.struts.action.RequestProcessor#processPreprocess(HttpServletRequest, HttpServletResponse)
     */
    boolean processPreprocess(HttpServletRequest request, HttpServletResponse response);

    /**
     * @see org.apache.struts.action.RequestProcessor#processCachedMessages(HttpServletRequest, HttpServletResponse)
     */
    void processCachedMessages(HttpServletRequest request, HttpServletResponse response);

    /**
     * @see org.apache.struts.action.RequestProcessor#processMapping(HttpServletRequest, HttpServletResponse, String)
     */
    ActionMapping processMapping(HttpServletRequest request, HttpServletResponse response, String path) throws IOException;

    /**
     * @see org.apache.struts.action.RequestProcessor#processRoles(HttpServletRequest, HttpServletResponse,
     *      ActionMapping)
     */
    boolean processRoles(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) throws IOException,
            ServletException;

    /**
     * @see org.apache.struts.action.RequestProcessor#processActionForm(HttpServletRequest, HttpServletResponse,
     *      ActionMapping)
     */
    ActionForm processActionForm(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping);

    /**
     * @see org.apache.struts.action.RequestProcessor#processValidate(HttpServletRequest, HttpServletResponse,
     *      ActionForm, ActionMapping)
     */
    boolean processValidate(HttpServletRequest request, HttpServletResponse response, ActionForm form, ActionMapping mapping) throws IOException,
            ServletException, InvalidCancelException;

    /**
     * @see org.apache.struts.action.RequestProcessor#processActionPerform(HttpServletRequest, HttpServletResponse,
     *      Action, ActionForm, ActionMapping)
     */
    ActionForward processActionPerform(HttpServletRequest request, HttpServletResponse response, Action action,
            ActionForm form, ActionMapping mapping) throws IOException, ServletException;

    /**
     * @see org.apache.struts.action.RequestProcessor#processPopulate(HttpServletRequest, HttpServletResponse,
     *      ActionForm, ActionMapping)
     */
    void processPopulate(HttpServletRequest request, HttpServletResponse response, ActionForm form,
            ActionMapping mapping) throws ServletException;

    /**
     * @see org.apache.struts.action.RequestProcessor#processForward(HttpServletRequest, HttpServletResponse,
     *      ActionMapping)
     */
    boolean processForward(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException, ServletException;

    /**
     * @see org.apache.struts.action.RequestProcessor#processInclude(HttpServletRequest, HttpServletResponse,
     *      ActionMapping)
     */
    boolean processInclude(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException, ServletException;

    /**
     * @see org.apache.struts.action.RequestProcessor#processForwardConfig(HttpServletRequest, HttpServletResponse,
     *      ForwardConfig)
     */
    void processForwardConfig(HttpServletRequest request, HttpServletResponse response, ForwardConfig forward)
            throws IOException, ServletException;

    /**
     * @see org.apache.struts.action.RequestProcessor#processActionCreate(HttpServletRequest, HttpServletResponse,
     *      ActionMapping)
     */
    Action processActionCreate(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException;

    /**
     * @see org.apache.struts.action.RequestProcessor#processException(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Exception, org.apache.struts.action.ActionForm,
     *      org.apache.struts.action.ActionMapping)
     */
    ActionForward processException(HttpServletRequest request, HttpServletResponse response,
            Exception exception, ActionForm form, ActionMapping mapping) throws IOException, ServletException;
    
    /**
     * @see org.apache.struts.action.RequestProcessor#doForward(java.lang.String, javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    void doForward(String uri, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    /**
     * @see org.apache.struts.action.RequestProcessor#doInclude(java.lang.String, javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    void doInclude(String uri, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    ModuleConfig getModuleConfig();
    
    ActionServlet getActionServlet();
    
    MessageResources getInternal();
    
    Log getLog();
    
}