package org.seasar.struts.processor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Satoshi Kimura
 */
public interface ValidateProcessor {

    boolean processValidate(HttpServletRequest request,
            HttpServletResponse response,
            ActionForm form,
            ActionMapping mapping,
            ExternalRequestProcessor requestProcessor) throws IOException, ServletException;

}
