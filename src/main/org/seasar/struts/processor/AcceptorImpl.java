package org.seasar.struts.processor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Satoshi Kimura
 */
public class AcceptorImpl implements Acceptor {

    public AcceptorImpl() {
    }

    public void process(ExternalRequestProcessor processor, HttpServletRequest request, HttpServletResponse response,
            Log log) throws IOException, ServletException {

        // Wrap multipart requests with a special wrapper
        request = processor.processMultipart(request);

        // Identify the path component we will use to select a mapping
        String path = processor.processPath(request, response);
        if (path == null) {
            return;
        }

        if (log.isDebugEnabled()) {
            log.debug("Processing a '" + request.getMethod() + "' for path '" + path + "'");
        }

        // Select a Locale for the current user if requested
        processor.processLocale(request, response);

        // Set the content type and no-caching headers if requested
        processor.processContent(request, response);
        processor.processNoCache(request, response);

        // General purpose preprocessing hook
        if (!processor.processPreprocess(request, response)) {
            return;
        }

        processor.processCachedMessages(request, response);

        // Identify the mapping for this request
        ActionMapping mapping = processor.processMapping(request, response, path);
        if (mapping == null) {
            return;
        }

        // Check for any role required to perform this action
        if (!processor.processRoles(request, response, mapping)) {
            return;
        }

        // Validate Form for this request
        ActionForm form = processor.processInputValueFormCreate(request, response, mapping);
        if (!processor.processValidate(request, response, form, mapping)) {
            return;
        }
        processor.processInputValueFormDelete(request, response, mapping);
        
        // Process any ActionForm bean related to this request
        form = processor.processActionForm(request, response, mapping);
        processor.processPopulate(request, response, form, mapping);

        // Process a forward or include specified by this mapping
        if (!processor.processForward(request, response, mapping)) {
            return;
        }

        if (!processor.processInclude(request, response, mapping)) {
            return;
        }

        // Create or acquire the Action instance to process this request
        //Action action = processor.processActionCreate(request, response, mapping);
        Object action = processor.getActionInstance(request, response, mapping);
        if (action == null) {
            return;
        }

        // Call the Action instance itself
        ActionForward forward = null;
        try {
            if (action instanceof Action) {
                forward = processor.processActionPerform(request, response, (Action) action, form, mapping);
            } else {
                forward = processor.processActionExecute(request, response, action, form, mapping);
            }
        } catch (Exception e) {
            log.error("Execute action", e);
            forward = processor.processException(request, response, e, form, mapping);
        }

        processor.processSetPath(forward);
        
        // Process the returned ActionForward instance
        processor.processForwardConfig(request, response, forward);
    }

}