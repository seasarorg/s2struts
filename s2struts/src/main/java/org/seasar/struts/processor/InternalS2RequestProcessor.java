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
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.InvalidCancelException;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;

/**
 * @author Satoshi Kimura
 * @author higa
 * @author Katsuhiko Nagashima
 */
public class InternalS2RequestProcessor extends RequestProcessor implements ExternalRequestProcessor {
    protected Log log = LogFactory.getLog(InternalS2RequestProcessor.class);

    /**
     * <p>
     * Process an <code>HttpServletRequest</code> and create the corresponding <code>HttpServletResponse</code> or dispatch to
     * another resource.
     * </p>
     * 
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * @exception IOException
     *                if an input/output error occurs
     * @exception ServletException
     *                if a processing exception occurs
     */
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.process(request, response);
    }

    public HttpServletRequest processMultipart(HttpServletRequest request) {
        return super.processMultipart(request);
    }

    public String processPath(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return super.processPath(request, response);
    }

    public void processLocale(HttpServletRequest request, HttpServletResponse response) {
        super.processLocale(request, response);
    }

    public void processContent(HttpServletRequest request, HttpServletResponse response) {
        super.processContent(request, response);
    }

    public void processNoCache(HttpServletRequest request, HttpServletResponse response) {
        super.processNoCache(request, response);
    }

    public boolean processPreprocess(HttpServletRequest request, HttpServletResponse response) {
        return super.processPreprocess(request, response);
    }

    public void processCachedMessages(HttpServletRequest request, HttpServletResponse response) {
        super.processCachedMessages(request, response);
    }

    public ActionMapping processMapping(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        return super.processMapping(request, response, path);
    }

    public boolean processRoles(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException, ServletException {
        return super.processRoles(request, response, mapping);
    }

    public ActionForm processActionForm(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) {
        return super.processActionForm(request, response, mapping);
    }

    public boolean processValidate(HttpServletRequest request, HttpServletResponse response, ActionForm form,
            ActionMapping mapping) throws IOException, ServletException, InvalidCancelException {
        return super.processValidate(request, response, form, mapping);
    }

    public ActionForward processActionPerform(HttpServletRequest request, HttpServletResponse response, Action action,
            ActionForm form, ActionMapping mapping) throws IOException, ServletException {
        return super.processActionPerform(request, response, action, form, mapping);
    }

    public void processPopulate(HttpServletRequest request, HttpServletResponse response, ActionForm form, ActionMapping mapping)
            throws ServletException {
        super.processPopulate(request, response, form, mapping);
    }

    public boolean processForward(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException, ServletException {
        return super.processForward(request, response, mapping);
    }

    public boolean processInclude(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException, ServletException {
        return super.processInclude(request, response, mapping);
    }

    public void processForwardConfig(HttpServletRequest request, HttpServletResponse response, ForwardConfig forward)
            throws IOException, ServletException {
        super.processForwardConfig(request, response, forward);
    }

    /**
     * Return an <code>Action</code> instance that will be used to process the current request, creating a new one if necessary.
     * 
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * @param mapping
     *            The mapping we are using
     * @exception IOException
     *                if an input/output error occurs
     */
    public Action processActionCreate(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException {
        return super.processActionCreate(request, response, mapping);
    }

    public ActionForward processException(HttpServletRequest request, HttpServletResponse response, Exception exception,
            ActionForm form, ActionMapping mapping) throws IOException, ServletException {
        return super.processException(request, response, exception, form, mapping);
    }

    public void doForward(String uri, HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        super.doForward(uri, request, response);
    }

    public void doInclude(String uri, HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        super.doInclude(uri, request, response);
    }

    public ModuleConfig getModuleConfig() {
        return super.moduleConfig;
    }

    public ActionServlet getActionServlet() {
        return super.servlet;
    }

    public MessageResources getInternal() {
        return super.getInternal();
    }

    public Log getLog() {
        return this.log;
    }

}