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

import org.seasar.struts.annotation.tiger.StrutsActionForward;

/**
 * 
 * @author taedium
 * 
 */
public abstract class AbstractEmployeeAction {

    @StrutsActionForward
    public static final String CONFIRM = "/pages/employee/confirm.jsp";

    @StrutsActionForward
    public static final String EDIT = "/pages/employee/edit.jsp";

    @StrutsActionForward
    public static final String SEARCH = "/pages/employee/search.jsp";

    @StrutsActionForward
    public static final String LIST = "/pages/employee/list.jsp";

    protected String crudType;

    public String getCrudType() {
        return crudType;
    }

    public void setCrudType(String crudType) {
        this.crudType = crudType;
    }
}
