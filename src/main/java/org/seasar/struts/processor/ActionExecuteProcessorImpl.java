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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Satoshi Kimura
 * @author Katsuhiko Nagashima
 */
public class ActionExecuteProcessorImpl implements ActionExecuteProcessor {

    private List commandList = new ArrayList();
    
    public void addActionCommand(ActionCommand actionCommand) {
        commandList.add(actionCommand);
    }

    public ActionForward processActionExecute(HttpServletRequest request,
            HttpServletResponse response, Object action, Object form,
            ActionMapping mapping) throws IOException, ServletException {

        for (Iterator it = commandList.iterator(); it.hasNext();) {
            ActionCommand command = (ActionCommand) it.next();
            String forward = command.execute(request, response, action, form, mapping);
            if (!ActionCommand.NOT_EXECUTE.equals(forward)) {
                if (forward != null) {
                    return mapping.findForward(forward);
                } else {
                    return null;
                }
            }
        }
        return null;
    }

}