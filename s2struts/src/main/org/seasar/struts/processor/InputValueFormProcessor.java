package org.seasar.struts.processor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Satoshi Kimura
 */
public interface InputValueFormProcessor {
    ActionForm create(HttpServletRequest request,
            HttpServletResponse response,
            ActionMapping mapping,
            ExternalRequestProcessor processor) throws ServletException;

    void delete(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping);

}
