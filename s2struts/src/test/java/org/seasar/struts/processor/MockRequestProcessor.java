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
 * 
 * @author Katsuhiko Nagashima
 *
 */
public class MockRequestProcessor implements ExternalRequestProcessor {

    private ActionServlet argServlet;

    public ActionServlet getArgServlet() {
        return argServlet;
    }

    private ModuleConfig argConfig;

    public ModuleConfig getArgConfig() {
        return argConfig;
    }

    private HttpServletRequest argRequest;

    public HttpServletRequest getArgRequest() {
        return argRequest;
    }

    private HttpServletResponse argResponse;
    
    public HttpServletResponse getArgResponse() {
        return argResponse;
    }

    private ActionMapping argMapping;
    
    public ActionMapping getArgMapping() {
        return argMapping;
    }

    private String argPath;
    
    public String getArgPath() {
        return argPath;
    }

    private Action argAction;
    
    public Action getArgAction() {
        return argAction;
    }

    private ActionForm argForm;
    
    public ActionForm getArgForm() {
        return argForm;
    }

    private ForwardConfig argForward;
    
    public ForwardConfig getArgForward() {
        return argForward;
    }

    private Exception argException;

    public Exception getArgException() {
        return argException;
    }

    //
    //
    //

    public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
        this.argServlet = servlet;
        this.argConfig = config;
    }

    public void destroy() {
    }

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.argRequest = request;
        this.argResponse = response;
    }

    public HttpServletRequest processMultipart(HttpServletRequest request) {
        this.argRequest = request;
        return null;
    }

    public String processPath(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.argRequest = request;
        this.argResponse = response;
        return null;
    }

    public void processLocale(HttpServletRequest request, HttpServletResponse response) {
        this.argRequest = request;
        this.argResponse = response;
    }

    public void processContent(HttpServletRequest request, HttpServletResponse response) {
        this.argRequest = request;
        this.argResponse = response;
    }

    public void processNoCache(HttpServletRequest request, HttpServletResponse response) {
        this.argRequest = request;
        this.argResponse = response;
    }

    public boolean processPreprocess(HttpServletRequest request, HttpServletResponse response) {
        this.argRequest = request;
        this.argResponse = response;
        return false;
    }

    public void processCachedMessages(HttpServletRequest request, HttpServletResponse response) {
        this.argRequest = request;
        this.argResponse = response;
    }

    public ActionMapping processMapping(HttpServletRequest request, HttpServletResponse response,
            String path) throws IOException {
        this.argRequest = request;
        this.argResponse = response;
        this.argPath = path;
        return null;
    }

    public boolean processRoles(HttpServletRequest request, HttpServletResponse response,
            ActionMapping mapping) throws IOException, ServletException {
        this.argRequest = request;
        this.argResponse = response;
        this.argMapping = mapping;
        return false;
    }

    public ActionForm processActionForm(HttpServletRequest request, HttpServletResponse response,
            ActionMapping mapping) {
        this.argRequest = request;
        this.argResponse = response;
        this.argMapping = mapping;
        return null;
    }

    public boolean processValidate(HttpServletRequest request, HttpServletResponse response,
            ActionForm form, ActionMapping mapping) throws IOException, ServletException,
            InvalidCancelException {
        this.argRequest = request;
        this.argResponse = response;
        this.argMapping = mapping;
        this.argForm = form;
        return false;
    }

    public ActionForward processActionPerform(HttpServletRequest request,
            HttpServletResponse response, Action action, ActionForm form, ActionMapping mapping)
            throws IOException, ServletException {
        this.argRequest = request;
        this.argResponse = response;
        this.argMapping = mapping;
        this.argAction = action;
        this.argForm = form;
        return mapping.findForward("result");
    }

    public void processPopulate(HttpServletRequest request, HttpServletResponse response,
            ActionForm form, ActionMapping mapping) throws ServletException {
        this.argRequest = request;
        this.argResponse = response;
        this.argMapping = mapping;
        this.argForm = form;
    }

    public boolean processForward(HttpServletRequest request, HttpServletResponse response,
            ActionMapping mapping) throws IOException, ServletException {
        this.argRequest = request;
        this.argResponse = response;
        this.argMapping = mapping;
        return false;
    }

    public boolean processInclude(HttpServletRequest request, HttpServletResponse response,
            ActionMapping mapping) throws IOException, ServletException {
        this.argRequest = request;
        this.argResponse = response;
        this.argMapping = mapping;
        return false;
    }

    public void processForwardConfig(HttpServletRequest request, HttpServletResponse response,
            ForwardConfig forward) throws IOException, ServletException {
        this.argRequest = request;
        this.argResponse = response;
        this.argForward = forward;
    }

    public Action processActionCreate(HttpServletRequest request, HttpServletResponse response,
            ActionMapping mapping) throws IOException {
        this.argRequest = request;
        this.argResponse = response;
        this.argMapping = mapping;
        return null;
    }

    public ActionForward processException(HttpServletRequest request, HttpServletResponse response,
            Exception exception, ActionForm form, ActionMapping mapping) throws IOException,
            ServletException {
        this.argRequest = request;
        this.argResponse = response;
        this.argMapping = mapping;
        this.argException = exception;
        this.argForm = form;
        return null;
    }

    public void doForward(String uri, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.argRequest = request;
        this.argResponse = response;
    }

    public void doInclude(String uri, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.argRequest = request;
        this.argResponse = response;
    }

    public ModuleConfig getModuleConfig() {
        return null;
    }

    public ActionServlet getActionServlet() {
        return null;
    }

    public MessageResources getInternal() {
        return null;
    }

    public Log getLog() {
        return null;
    }

    public String toString() {
        return "MockRequestProcessor";
    }

}
