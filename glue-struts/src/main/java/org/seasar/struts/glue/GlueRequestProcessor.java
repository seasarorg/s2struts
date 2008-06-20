package org.seasar.struts.glue;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RequestProcessor;

public class GlueRequestProcessor extends RequestProcessor {

    protected Glue glue = new Glue();

    protected Action processActionCreate(HttpServletRequest request,
            HttpServletResponse response, ActionMapping mapping)
            throws IOException {
        if (mapping.getType() != null) {
            return super.processActionCreate(request, response, mapping);
        }
        return glue.getAction(request, response, mapping);
    }

}
