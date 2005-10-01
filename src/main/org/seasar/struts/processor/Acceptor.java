package org.seasar.struts.processor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;

/**
 * @author Satoshi Kimura
 */
public interface Acceptor {
    void process(ExternalRequestProcessor processor, HttpServletRequest request, HttpServletResponse response, Log log)
            throws IOException, ServletException;
}