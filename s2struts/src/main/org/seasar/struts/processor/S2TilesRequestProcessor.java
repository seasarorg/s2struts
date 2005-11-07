package org.seasar.struts.processor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.tiles.TilesRequestProcessor;
import org.seasar.struts.action.ActionFactory;
import org.seasar.struts.util.S2StrutsContextUtil;

/**
 * @author Satoshi Kimura
 * @author higa
 */
public class S2TilesRequestProcessor extends TilesRequestProcessor implements ExternalRequestProcessor {
    private ActionExecuteProcessor executeProcessor;
    private ActionFactory actionFactory;
    private Acceptor acceptor;
    private ValidateProcessor validateProcessor;
    private InputValueFormProcessor inputValueFormCreator;

    /**
     * <p>
     * Process an <code>HttpServletRequest</code> and create the corresponding <code>HttpServletResponse</code> or
     * dispatch to another resource.
     * </p>
     * 
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a processing exception occurs
     */
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.acceptor.process(this, request, response, log);
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

    public ActionMapping processMapping(HttpServletRequest request, HttpServletResponse response, String path)
            throws IOException {
        return super.processMapping(request, response, path);
    }

    public boolean processRoles(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException, ServletException {
        return super.processRoles(request, response, mapping);
    }

    public ActionForm processActionForm(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) {
        return super.processActionForm(request, response, mapping);
    }

    public boolean processS2Validate(HttpServletRequest request, HttpServletResponse response, ActionForm form,
            ActionMapping mapping) throws IOException, ServletException {
        return this.validateProcessor.processValidate(request, response, form, mapping, this);
    }
    
    public boolean processValidate(HttpServletRequest request,
            HttpServletResponse response,
            ActionForm form,
            ActionMapping mapping) throws IOException, ServletException {
        return super.processValidate(request, response, form, mapping);
    }

    public ActionForward processActionPerform(HttpServletRequest request, HttpServletResponse response, Action action,
            ActionForm form, ActionMapping mapping) throws IOException, ServletException {
        return super.processActionPerform(request, response, action, form, mapping);
    }

    public void processPopulate(HttpServletRequest request, HttpServletResponse response, ActionForm form,
            ActionMapping mapping) throws ServletException {
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

    public ActionForward processException(HttpServletRequest request, HttpServletResponse response,
            Exception exception, ActionForm form, ActionMapping mapping) throws IOException, ServletException {
        return super.processException(request, response, exception, form, mapping);
    }

    /**
     * Return an <code>Action</code> instance that will be used to process the current request, creating a new one if
     * necessary.
     * 
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param mapping The mapping we are using
     * @exception IOException if an input/output error occurs
     */
    public Action processActionCreate(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException {
        return this.actionFactory.processActionCreate(request, response, mapping, log, getInternal(), this.servlet);
    }

    public Object getActionInstance(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException {
        return this.actionFactory.getActionInstance(request, response, mapping, log, getInternal(), this.servlet);

    }

    public ActionForward processActionExecute(HttpServletRequest request, HttpServletResponse response, Object action,
            ActionForm form, ActionMapping mapping) throws IOException, ServletException {
        return this.executeProcessor.processActionExecute(request, response, action, form, mapping);
    }
    
    public ActionForm processInputValueFormCreate(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) throws ServletException {
        return this.inputValueFormCreator.create(request, response, mapping, this);
    }
    
    public void processInputValueFormDelete(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) {
        this.inputValueFormCreator.delete(request, response, mapping);
    }

    /**
     * @return Returns the executeProcessor.
     */
    public ActionExecuteProcessor getExecuteProcessor() {
        return this.executeProcessor;
    }

    /**
     * @param executeProcessor The executeProcessor to set.
     */
    public void setExecuteProcessor(ActionExecuteProcessor executeProcessor) {
        this.executeProcessor = executeProcessor;
    }
    /**
     * @param actionFactory The actionFactory to set.
     */
    public void setActionFactory(ActionFactory actionFactory) {
        this.actionFactory = actionFactory;
    }
    /**
     * @param acceptor The acceptor to set.
     */
    public void setAcceptor(Acceptor acceptor) {
        this.acceptor = acceptor;
    }
    
    /**
     * @param validateProcessor The validateProcessor to set.
     */
    public void setValidateProcessor(ValidateProcessor validateProcessor) {
        this.validateProcessor = validateProcessor;
    }
    
    /**
     * @param inputValueFormCreator The inputValueFormCreator to set.
     */
    public void setInputValueFormCreator(InputValueFormProcessor inputValueFormCreator) {
        this.inputValueFormCreator = inputValueFormCreator;
    }
    
    public ModuleConfig getModuleConfig(){
        return super.moduleConfig;
    }

    public ActionServlet getActionServlet() {
        return super.servlet;
    }
    
    public void processSetPath(ForwardConfig forward) {
        S2StrutsContextUtil.setPath(forward);
    }
    
    public void doForward(String uri, HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        
        S2StrutsContextUtil.setPath(uri);
        super.doForward(uri, request, response);
    }

}