/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.struts.processor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.seasar.struts.Constants;
import org.seasar.struts.form.InputValueForm;

/**
 * @author Satoshi Kimura
 */
public class InputValueFormProcessorImpl implements InputValueFormProcessor {

    public ActionForm create(HttpServletRequest request,
            HttpServletResponse response,
            ActionMapping mapping,
            ExternalRequestProcessor processor) throws ServletException {

        String attribute = mapping.getAttribute();
        if(attribute == null) {
            return null;
        }

        InputValueForm actionForm = new InputValueForm();

        if (Constants.REQUEST.equals(mapping.getScope())) {
            request.setAttribute(attribute, actionForm);
        } else {
            HttpSession session = request.getSession();
            backupSessionForm(session, attribute);
            session.setAttribute(attribute, actionForm);
        }

        processor.processPopulate(request, response, actionForm, mapping);

        actionForm.freeze();
        
        return actionForm;
    }

    public void delete(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) {
        if (Constants.REQUEST.equals(mapping.getScope())) {
            request.removeAttribute(mapping.getAttribute());
        } else {
            HttpSession session = request.getSession();
            session.removeAttribute(mapping.getAttribute());
            turnbackSessionForm(session, mapping.getAttribute());
        }
    }
    
    private void backupSessionForm(HttpSession session, String attribute) {
        Object form = session.getAttribute(attribute);
        if (form == null) {
            return;
        }
        if (form instanceof InputValueForm) {
            return;
        }

        session.setAttribute(getBackupSessionFormAttribute(attribute), form);
    }

    private void turnbackSessionForm(HttpSession session, String attribute) {
        Object form = session.getAttribute(getBackupSessionFormAttribute(attribute));
        session.removeAttribute(getBackupSessionFormAttribute(attribute));
        session.setAttribute(attribute, form);
    }

    private String getBackupSessionFormAttribute(String attribute) {
        return Constants.BACKUP_SESSION_FORM_KEY + "." + attribute;
    }

}
