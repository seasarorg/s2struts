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

import org.seasar.struts.annotation.tiger.ScopeType;
import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForward;
import org.seasar.struts.examples.web.CrudType;

/**
 * @author taedium
 * 
 */
@StrutsAction(scope = ScopeType.REQUEST)
public class ListAction {

    @StrutsActionForward(path = Paths.EDIT)
    public static String EDIT = "edit";

    @StrutsActionForward(path = Paths.CONFIRM)
    public static String CONFIRM = "confirm";

    private String crudType;

    public String getCrudType() {
        return crudType;
    }

    public String goDelete() {
        crudType = CrudType.DELETE;
        return CONFIRM;
    }

    public String goEdit() {
        crudType = CrudType.UPDATE;
        return EDIT;
    }

    public String goInquire() {
        crudType = CrudType.READ;
        return CONFIRM;
    }
}