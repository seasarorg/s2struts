package org.seasar.struts.processor;

import javax.servlet.ServletContext;
import javax.servlet.UnavailableException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ControllerConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author Satoshi Kimura
 */
public class RequestProcessorFactoryImplTest extends S2TestCase {
    private RequestProcessorFactory requestProcessorFactory;
    private ServletContext context;

    protected void setUp() throws Exception {
        include("ServletContextMockObject.dicon");
    }


    public RequestProcessorFactoryImplTest(String name) {
        super(name);
    }

    public void setUpGetRequestProcessorFromContainer() {
        try {
            S2Container container = SingletonS2ContainerFactory.getContainer();
            container.register(S2RequestProcessor.class);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    public void testGetRequestProcessorFromContainer() throws Exception {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        S2RequestProcessor expected = (S2RequestProcessor) container.getComponent(S2RequestProcessor.class);

        ModuleConfig config = new ModuleConfigImpl("prefix");
        ActionServlet servlet = null;

        RequestProcessor actual = requestProcessorFactory.getRequestProcessor(config, context, servlet);

        assertSame(expected, actual);
    }
    public void testGetRequestProcessorNormal() throws Exception {
        ModuleConfig config = new ModuleConfigImpl("prefix");
        ControllerConfig controllerConfig = new ControllerConfig();
        controllerConfig.setProcessorClass(RequestProcessor.class.getName());
        config.setControllerConfig(controllerConfig);
        ActionServlet servlet = null;

        RequestProcessor actual = requestProcessorFactory.getRequestProcessor(config, context, servlet);

        assertEquals(RequestProcessor.class, actual.getClass());
    }
    public void testGetRequestProcessorException() throws Exception {
        ModuleConfig config = new ModuleConfigImpl("prefix");
        ControllerConfig controllerConfig = new ControllerConfig();
        controllerConfig.setProcessorClass(String.class.getName());
        config.setControllerConfig(controllerConfig);
        ActionServlet servlet = null;
        try {
            requestProcessorFactory.getRequestProcessor(config, context, servlet);
            fail();
        } catch (UnavailableException e) {
            //success
        }
    }

}
