package org.seasar.struts.servlet;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.S2StrutsInitializer;
import org.seasar.struts.processor.RequestProcessorFactory;

/**
 * @author Satoshi Kimura
 */
public class S2ActionServlet extends ActionServlet {

    private static final long serialVersionUID = S2ActionServlet.class.hashCode();

    /**
     * 
     */
    public S2ActionServlet() {
        super();
    }

    /**
     * <p>
     * Look up and return the {@link RequestProcessor}responsible for the specified module, creating a new one if
     * necessary.
     * </p>
     * 
     * @param config The module configuration for which to acquire and return a RequestProcessor.
     * @exception ServletException if we cannot instantiate a RequestProcessor instance
     */
    protected synchronized RequestProcessor getRequestProcessor(ModuleConfig config) throws ServletException {
        return getRequestProcessorFactory().getRequestProcessor(config, getServletContext(), this);
    }

    /**
     * @see ActionServlet#init()
     * @see S2StrutsInitializer#registActionClass(ActionServlet)
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
        return (RequestProcessorFactory) container.getComponent(RequestProcessorFactory.class);
    }
}