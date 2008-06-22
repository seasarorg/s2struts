package org.seasar.struts.glue;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;

public interface ActionFactory {

    Action getAction(ActionServlet servlet, Object component, String methodName);
}
