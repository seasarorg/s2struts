package org.seasar.struts.processor;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.ClassUtil;

/**
 * @author Satoshi Kimura
 */
public class RequestProcessorFactoryImpl implements RequestProcessorFactory {

    public RequestProcessorFactoryImpl() {
    }


    public synchronized RequestProcessor getRequestProcessor(ModuleConfig config, ServletContext context,
            ActionServlet actionServlet) throws ServletException {

        RequestProcessor processor = getProcessorForModule(config, context);

        if (processor == null) {
            String processorClassName = config.getControllerConfig().getProcessorClass();
            Class requestProcessorClazz = ClassUtil.forName(processorClassName);

            S2Container container = SingletonS2ContainerFactory.getContainer();
            if (true == container.hasComponentDef(requestProcessorClazz)) {
                processor = (RequestProcessor) container.getComponent(requestProcessorClazz);
            }

            if (processor == null) {
                try {
                    processor = (RequestProcessor) RequestUtils.applicationInstance(processorClassName);
                } catch (Exception e) {
                    String message = "Cannot initialize RequestProcessor of class " + processorClassName + ": " + e;
                    throw new UnavailableException(message);
                }
            }
            processor.init(actionServlet, config);

            String key = Globals.REQUEST_PROCESSOR_KEY + config.getPrefix();
            context.setAttribute(key, processor);
        }

        return processor;
    }

    private static RequestProcessor getProcessorForModule(ModuleConfig config, ServletContext context) {
        String key = Globals.REQUEST_PROCESSOR_KEY + config.getPrefix();
        return (RequestProcessor) context.getAttribute(key);
    }

}