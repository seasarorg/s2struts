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
package org.seasar.struts.examples.web.employee.impl;

import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.struts.examples.entity.Employee;
import org.seasar.struts.examples.web.CrudType;
import org.seasar.struts.examples.web.employee.ConfirmAction;
import org.seasar.struts.examples.web.employee.EmployeeLogic;

/**
 * @author taedium
 * 
 */
public class ConfirmActionImpl implements ConfirmAction {

    private EmployeeLogic employeeLogic;

    private ConfirmForm confirmForm;

    private String crudType;

    public String getCrudType() {
        return crudType;
    }

    public void setCrudType(String crudType) {
        this.crudType = crudType;
    }

    @Binding(bindingType = BindingType.MUST)
    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

    public void setConfirmForm(ConfirmForm confirmForm) {
        this.confirmForm = confirmForm;
    }

    public ConfirmForm getConfirmForm() {
        return confirmForm;
    }

    public String goStore() {
        Employee employee = Beans.createAndCopy(Employee.class, confirmForm)
                .excludesWhitespace().excludesNull().execute();
        if (CrudType.CREATE.equals(crudType)) {
            employeeLogic.insert(employee);
            return SEARCH;
        } else if (CrudType.UPDATE.equals(crudType)) {
            employeeLogic.update(employee);
            return LIST;
        } else if (CrudType.DELETE.equals(crudType)) {
            employeeLogic.delete(employee);
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
