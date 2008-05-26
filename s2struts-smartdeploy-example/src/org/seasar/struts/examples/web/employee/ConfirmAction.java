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

import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.examples.entity.Employee;
import org.seasar.struts.examples.web.CrudType;

/**
 * @author taedium
 * 
 */
@StrutsAction(input = AbstractEmployeeAction.CONFIRM)
public class ConfirmAction extends AbstractEmployeeAction {

    private EmployeeService employeeService;

    private ConfirmForm confirmForm;

    @Binding(bindingType = BindingType.MUST)
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setConfirmForm(ConfirmForm confirmForm) {
        this.confirmForm = confirmForm;
    }

    public String goStore() {
        Employee employee = Beans.createAndCopy(Employee.class, confirmForm)
                .excludesWhitespace().excludesNull().execute();
        if (CrudType.CREATE.equals(crudType)) {
            employeeService.insert(employee);
            return SEARCH;
        } else if (CrudType.UPDATE.equals(crudType)) {
            employeeService.update(employee);
            return LIST;
        } else if (CrudType.DELETE.equals(crudType)) {
            employeeService.delete(employee);
            return LIST;
        }
        return SEARCH;
    }

    public String goPrevious() {
        if (CrudType.CREATE.equals(crudType)
                || CrudType.UPDATE.equals(crudType)) {
            return EDIT;
        }
        return LIST;
    }
}
