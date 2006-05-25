package org.seasar.struts.pojo.processor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class DelegateRequestProcessor extends RequestProcessor {

    private RequestProcessor processor;

    public DelegateRequestProcessor() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        this.processor = (RequestProcessor) container.getComponent(RequestProcessor.class);
    }

    public void destroy() {
        this.processor.destroy();
    }

    public boolean equals(Object obj) {
        return this.processor.equals(obj);
    }

    public int hashCode() {
        return this.processor.hashCode();
    }

    public void init(ActionServlet arg0, ModuleConfig arg1) throws ServletException {
        this.processor.init(arg0, arg1);
    }

    public void process(HttpServletRequest arg0, HttpServletResponse arg1) throws IOException,
            ServletException {
        this.processor.process(arg0, arg1);
    }

    public String toString() {
        return this.processor.toString();
    }

}
