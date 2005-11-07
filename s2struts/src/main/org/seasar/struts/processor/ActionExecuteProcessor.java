package org.seasar.struts.processor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Satoshi Kimura
 */
public interface ActionExecuteProcessor {
    public ActionForward processActionExecute(HttpServletRequest request, HttpServletResponse response, Object action,
            Object form, ActionMapping mapping) throws IOException, ServletException;

}
