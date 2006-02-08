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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public interface ActionCommand {
    
    String NOT_EXECUTE = "org.seasar.struts.processor.ActionCommand.NOT_EXECUTE";

    String execute(HttpServletRequest request, HttpServletResponse response,
            Object action, Object form, ActionMapping mapping);
    
}
