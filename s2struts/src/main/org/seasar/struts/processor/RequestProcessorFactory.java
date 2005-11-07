package org.seasar.struts.processor;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ModuleConfig;

/**
 * @author Satoshi Kimura
 */
public interface RequestProcessorFactory {
    /**
     * @param config
     * @param context
     * @param actionServlet
     * @return RequestProcessor
     * @throws ServletException
     */
    public RequestProcessor getRequestProcessor(ModuleConfig config, ServletContext context, ActionServlet actionServlet)
            throws ServletException;

}