package org.seasar.struts.unit;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.digester.Digester;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.ModuleUtils;
import org.seasar.struts.unit.util.MethodUtil;
/**
 * @author Satoshi Kimura
 */
public class ActionServletWrapper extends ActionServlet {
    private ActionServlet actionServlet;

    public ActionServletWrapper(ActionServlet actionServlet) {
        this.actionServlet = actionServlet;
    }

    public RequestProcessor getRequestProcessor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        ModuleUtils.getInstance().selectModule(request, getServletContext());

        ModuleConfig config = getModuleConfig(request);

        return getRequestProcessor(config);
    }

    public ServletContext getServletContext() {
        return actionServlet.getServletContext();
    }

    public void destroy() {
        actionServlet.destroy();
    }

    public void init() throws ServletException {
        actionServlet.init();
    }

    protected void initModulePrefixes(ServletContext context) {
        Class[] parameterTypes = {ServletContext.class};
        Object[] args = {context};
        MethodUtil.invoke(actionServlet, "initModulePrefixes", parameterTypes, args);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        actionServlet.doGet(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        actionServlet.doPost(request, response);
    }

    public void addServletMapping(String servletName, String urlPattern) {
        actionServlet.addServletMapping(servletName, urlPattern);
    }

    public MessageResources getInternal() {
        return actionServlet.getInternal();
    }

    protected void destroyModules() {
        MethodUtil.invoke(actionServlet, "destroyModules", null, null);
    }

    protected void destroyConfigDigester() {
        MethodUtil.invoke(actionServlet, "destroyConfigDigester", null, null);
    }

    protected void destroyInternal() {
        MethodUtil.invoke(actionServlet, "destroyInternal", null, null);
    }

    protected ModuleConfig getModuleConfig(HttpServletRequest request) {
        Class[] parameterTypes = {HttpServletRequest.class};
        Object[] args = {request};
        return (ModuleConfig) MethodUtil.invoke(actionServlet, "getModuleConfig", parameterTypes, args);
    }

    protected synchronized RequestProcessor getRequestProcessor(ModuleConfig config) throws ServletException {
        Class[] parameterTypes = {ModuleConfig.class};
        Object[] args = {config};
        return (RequestProcessor) MethodUtil.invoke(actionServlet, "getRequestProcessor", parameterTypes, args);
    }

    protected void initModuleConfigFactory() {
        MethodUtil.invoke(actionServlet, "initModuleConfigFactory", null, null);
    }

    protected ModuleConfig initModuleConfig(String prefix, String paths) throws ServletException {
        Class[] parameterTypes = {String.class, String.class};
        Object[] args = {prefix, paths};
        return (ModuleConfig) MethodUtil.invoke(actionServlet, "initModuleConfig", parameterTypes, args);
    }

    protected void parseModuleConfigFile(Digester digester, String path) throws UnavailableException {
        Class[] parameterTypes = {Digester.class, String.class};
        Object[] args = {digester, path};
        MethodUtil.invoke(actionServlet, "parseModuleConfigFile", parameterTypes, args);
    }

    protected void initModuleDataSources(ModuleConfig config) throws ServletException {
        Class[] parameterTypes = {ModuleConfig.class};
        Object[] args = {config};
        MethodUtil.invoke(actionServlet, "initModuleDataSources", parameterTypes, args);
    }

    protected void initModulePlugIns(ModuleConfig config) throws ServletException {
        Class[] parameterTypes = {ModuleConfig.class};
        Object[] args = {config};
        MethodUtil.invoke(actionServlet, "initModulePlugIns", parameterTypes, args);
    }

    protected void initModuleMessageResources(ModuleConfig config) throws ServletException {
        Class[] parameterTypes = {ModuleConfig.class};
        Object[] args = {config};
        MethodUtil.invoke(actionServlet, "initModuleMessageResources", parameterTypes, args);
    }

    protected Digester initConfigDigester() throws ServletException {
        return (Digester) MethodUtil.invoke(actionServlet, "initConfigDigester", null, null);
    }

    protected void initInternal() throws ServletException {
        MethodUtil.invoke(actionServlet, "initInternal", null, null);
    }

    protected void initOther() throws ServletException {
        MethodUtil.invoke(actionServlet, "initOther", null, null);
    }

    protected void initServlet() throws ServletException {
        MethodUtil.invoke(actionServlet, "initServlet", null, null);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        Class[] parameterTypes = {HttpServletRequest.class, HttpServletResponse.class};
        Object[] args = {request, response};
        MethodUtil.invoke(actionServlet, "process", parameterTypes, args);
    }
}