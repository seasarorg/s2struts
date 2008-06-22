package org.seasar.struts.glue;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.TilesRequestProcessor;

public class GlueTilesRequestProcessor extends TilesRequestProcessor {

    @Override
    protected Action processActionCreate(final HttpServletRequest request,
            final HttpServletResponse response, final ActionMapping mapping)
            throws IOException {
        final Glue glue = RegistryLocator.getInstance().getGlue();
        if (glue.isTarget(mapping)) {
            return glue.getAction(servlet, request, response, mapping);
        }
        return super.processActionCreate(request, response, mapping);
    }
}
