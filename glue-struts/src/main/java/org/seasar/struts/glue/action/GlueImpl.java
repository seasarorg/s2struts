package org.seasar.struts.glue.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.seasar.struts.glue.ActionFactory;
import org.seasar.struts.glue.Glue;
import org.seasar.struts.glue.MethodNameExtracter;
import org.seasar.struts.glue.Registry;
import org.seasar.struts.glue.RegistryLocator;
import org.seasar.struts.glue.exception.ActionComponentNotFoundException;
import org.seasar.struts.glue.exception.NoMethodNameParameterException;

public class GlueImpl implements Glue {

    protected ActionFactory actionFactory;

    protected MethodNameExtracter methodNameExtracter;

    public void setActionFactory(final ActionFactory actionFactory) {
        this.actionFactory = actionFactory;
    }

    public void setMethodNameExtracter(
            final MethodNameExtracter methodNameExtracter) {
        this.methodNameExtracter = methodNameExtracter;
    }

    public GlueImpl() {
    }

    public boolean isTarget(final ActionMapping mapping) {
        final Registry registry = RegistryLocator.getInstance();
        return registry.hasComponent(mapping.getPath());
    }

    public Action getAction(final ActionServlet servlet,
            final HttpServletRequest request,
            final HttpServletResponse response, final ActionMapping mapping)
            throws IOException {

        final String componentName = mapping.getPath();
        final String methodName = methodNameExtracter.extract(request);
        if (methodName == null) {
            throw new NoMethodNameParameterException();
        }
        return getActionInternal(servlet, componentName, methodName);
    }

    public Action getAction(final ActionServlet servlet,
            final String componentName, final String methodName) {
        return getActionInternal(servlet, componentName, methodName);
    }

    protected Action getActionInternal(final ActionServlet servlet,
            final String componentName, final String methodName) {
        final Registry registry = RegistryLocator.getInstance();
        if (!registry.hasComponent(componentName)) {
            throw new ActionComponentNotFoundException(componentName);
        }
        final Object component = registry.getComponent(componentName);
        return actionFactory.getAction(servlet, component, methodName);
    }

}
