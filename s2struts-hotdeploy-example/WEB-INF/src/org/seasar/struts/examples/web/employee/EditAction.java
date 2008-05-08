/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.struts.examples.web.employee;

import org.seasar.struts.annotation.tiger.BoolType;
import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForward;

/**
 * @author taedium
 * 
 */
@StrutsAction(name = "editForm")
public interface EditAction {

    @StrutsActionForward(path = "/pages/employee/error.jsp")
    public String ERROR = "error";

    @StrutsActionForward(path = "/pages/employee/confirm.jsp")
    public String CONFIRM = "confirm";

    @StrutsActionForward(path = "/pages/employee/search.jsp", redirect = BoolType.TRUE)
    public String SEARCH = "search";

    @StrutsActionForward(path = "/pages/employee/list.jsp", redirect = BoolType.TRUE)
    public String LIST = "list";

    public String goConfirm();

    public String goPrevious();
}
