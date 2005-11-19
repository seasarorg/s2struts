package org.seasar.struts.unit;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.seasar.struts.unit.util.MethodUtil;
/**
 * @author Satoshi Kimura
 */
public class RequestProcessorWrapper extends RequestProcessor {
    private RequestProcessor requestProcessor;
    private Action action;
    private ActionForm form;
    private ActionForward forward;

    public RequestProcessorWrapper(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    public void destroy() {
        requestProcessor.destroy();
    }

    public void init(ActionServlet servlet, ModuleConfig moduleConfig) throws ServletException {
        requestProcessor.init(servlet, moduleConfig);

        super.servlet = servlet;
        super.moduleConfig = moduleConfig;
    }

    protected Action processActionCreate(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class, ActionMapping.class};
        Object[] args = {request, response, mapping};
        action = (Action) MethodUtil.invoke(requestProcessor, "processActionCreate", parameterTypes, args);
        return action;
    }

    protected ActionForm processActionForm(HttpServletRequest request, HttpServletResponse response,
            ActionMapping mapping) {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class, ActionMapping.class};
        Object[] args = {request, response, mapping};
        form = (ActionForm) MethodUtil.invoke(requestProcessor, "processActionForm", parameterTypes, args);
        return form;
    }

    protected void processForwardConfig(HttpServletRequest request, HttpServletResponse response, ForwardConfig forward)
            throws IOException, ServletException {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class, ForwardConfig.class};
        Object[] args = {request, response, forward};
        MethodUtil.invoke(requestProcessor, "processForwardConfig", parameterTypes, args);
    }

    protected ActionForward processActionPerform(HttpServletRequest request, HttpServletResponse response,
            Action action, ActionForm form, ActionMapping mapping) throws IOException, ServletException {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class, Action.class, ActionForm.class,
                ActionMapping.class};
        Object[] args = {request, response, action, form, mapping};
        forward = (ActionForward) MethodUtil.invoke(requestProcessor, "processActionPerform", parameterTypes, args);
        return forward;
    }

    protected void processCachedMessages(HttpServletRequest request, HttpServletResponse response) {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class};
        Object[] args = {request, response};
        MethodUtil.invoke(requestProcessor, "processCachedMessages", parameterTypes, args);
    }

    protected void processContent(HttpServletRequest request, HttpServletResponse response) {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class};
        Object[] args = {request, response};
        MethodUtil.invoke(requestProcessor, "processContent", parameterTypes, args);
    }

    protected ActionForward processException(HttpServletRequest request, HttpServletResponse response,
            Exception exception, ActionForm form, ActionMapping mapping) throws IOException, ServletException {
        exception.printStackTrace();

        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class, Exception.class,
                ActionForm.class, ActionMapping.class};
        Object[] args = {request, response, exception, form, mapping};
        return (ActionForward) MethodUtil.invoke(requestProcessor, "processException", parameterTypes, args);
    }

    protected boolean processForward(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException, ServletException {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class, ActionMapping.class};
        Object[] args = {request, response, mapping};
        Boolean ret = (Boolean) MethodUtil.invoke(requestProcessor, "processForward", parameterTypes, args);
        return ret.booleanValue();
    }

    protected boolean processInclude(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException, ServletException {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class, ActionMapping.class};
        Object[] args = {request, response, mapping};
        Boolean ret = (Boolean) MethodUtil.invoke(requestProcessor, "processInclude", parameterTypes, args);
        return ret.booleanValue();
    }

    protected void processLocale(HttpServletRequest request, HttpServletResponse response) {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class};
        Object[] args = {request, response};
        MethodUtil.invoke(requestProcessor, "processLocale", parameterTypes, args);
    }

    protected ActionMapping processMapping(HttpServletRequest request, HttpServletResponse response, String path)
            throws IOException {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class, String.class};
        Object[] args = {request, response, path};
        return (ActionMapping) MethodUtil.invoke(requestProcessor, "processMapping", parameterTypes, args);
    }

    protected HttpServletRequest processMultipart(HttpServletRequest request) {
        Class[] parameterTypes = {HttpServletRequest.class};
        Object[] args = {request};
        return (HttpServletRequest) MethodUtil.invoke(requestProcessor, "processMultipart", parameterTypes, args);
    }

    protected void processNoCache(HttpServletRequest request, HttpServletResponse response) {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class};
        Object[] args = {request, response};
        MethodUtil.invoke(requestProcessor, "processNoCache", parameterTypes, args);
    }

    protected String processPath(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class};
        Object[] args = {request, response};
        return (String) MethodUtil.invoke(requestProcessor, "processPath", parameterTypes, args);
    }

    protected void processPopulate(HttpServletRequest request, HttpServletResponse response, ActionForm form,
            ActionMapping mapping) throws ServletException {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class, ActionForm.class,
                ActionMapping.class};
        Object[] args = {request, response, form, mapping};
        MethodUtil.invoke(requestProcessor, "processPopulate", parameterTypes, args);
    }

    protected boolean processPreprocess(HttpServletRequest request, HttpServletResponse response) {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class};
        Object[] args = {request, response};
        Boolean ret = (Boolean) MethodUtil.invoke(requestProcessor, "processPreprocess", parameterTypes, args);
        return ret.booleanValue();
    }

    protected boolean processRoles(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException, ServletException {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class, ActionMapping.class};
        Object[] args = {request, response, mapping};
        Boolean ret = (Boolean) MethodUtil.invoke(requestProcessor, "processRoles", parameterTypes, args);
        return ret.booleanValue();
    }

    protected boolean processValidate(HttpServletRequest request, HttpServletResponse response, ActionForm form,
            ActionMapping mapping) throws IOException, ServletException {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class, ActionForm.class,
                ActionMapping.class};
        Object[] args = {request, response, form, mapping};
        Boolean ret = (Boolean) MethodUtil.invoke(requestProcessor, "processValidate", parameterTypes, args);
        return ret.booleanValue();
    }

    protected void internalModuleRelativeForward(String uri, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Class[] parameterTypes = {String.class, HttpServletRequest.class, HttpServletResponse.class};
        Object[] args = {uri, request, response};
        MethodUtil.invoke(requestProcessor, "internalModuleRelativeForward", parameterTypes, args);
    }

    protected void internalModuleRelativeInclude(String uri, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Class[] parameterTypes = {String.class, HttpServletRequest.class, HttpServletResponse.class};
        Object[] args = {uri, request, response};
        MethodUtil.invoke(requestProcessor, "internalModuleRelativeInclude", parameterTypes, args);
    }

    protected void doForward(String uri, HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        Class[] parameterTypes = {String.class, HttpServletRequest.class, HttpServletResponse.class};
        Object[] args = {uri, request, response};
        MethodUtil.invoke(requestProcessor, "doForward", parameterTypes, args);
    }

    protected void doInclude(String uri, HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        Class[] parameterTypes = {String.class, HttpServletRequest.class, HttpServletResponse.class};
        Object[] args = {uri, request, response};
        MethodUtil.invoke(requestProcessor, "doInclude", parameterTypes, args);
    }

    protected MessageResources getInternal() {
        return (MessageResources) MethodUtil.invoke(requestProcessor, "getInternal", null, null);
    }

    protected ServletContext getServletContext() {
        return (ServletContext) MethodUtil.invoke(requestProcessor, "getServletContext", null, null);
    }

    protected ActionForward getActionForward() {
        return forward;
    }

    protected Action getExecutedAction() {
        return action;
    }

    protected ActionForm getExecutedActionForm() {
        return form;
    }
}