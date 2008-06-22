package org.seasar.struts.glue;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

public interface Glue {

    boolean isTarget(ActionMapping mapping);

    Action getAction(ActionServlet servlet, HttpServletRequest request,
            HttpServletResponse response, ActionMapping mapping)
            throws IOException;

    Action getAction(ActionServlet servlet, String componentName,
            String methodName);

}