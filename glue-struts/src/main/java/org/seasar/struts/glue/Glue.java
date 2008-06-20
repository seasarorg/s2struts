package org.seasar.struts.glue;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.seasar.struts.glue.exception.ActionComponentNotFoundException;
import org.seasar.struts.glue.exception.NoMethodNameParameterException;

public class Glue {

    public Action getAction(HttpServletRequest request,
            HttpServletResponse response, ActionMapping mapping)
            throws IOException {
        Registry registry = RegistryLocator.getInstance();
        if (registry.hasComponent(mapping.getPath())) {
            Object component = registry.getComponent(mapping.getPath());
            NameExtracter nameExtracter = registry
                    .getComponent("nameExtracter");
            ActionFactory actionFactory = registry
                    .getComponent("actionFactory");
            String methodName = nameExtracter.extractMethodName(request);
            if (methodName == null) {
                throw new NoMethodNameParameterException();
            }
            return actionFactory.getAction(component, methodName);
        } else {
            throw new ActionComponentNotFoundException(mapping);
        }
    }
}
