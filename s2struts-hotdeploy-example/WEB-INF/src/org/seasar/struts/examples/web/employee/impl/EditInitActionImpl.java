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

import java.util.List;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.struts.examples.dto.EmployeeDto;
import org.seasar.struts.examples.entity.Department;
import org.seasar.struts.examples.web.employee.EditInitAction;
import org.seasar.struts.examples.web.employee.EmployeeLogic;

/**
 * @author taedium
 * 
 */
public class EditInitActionImpl implements EditInitAction {

    private EmployeeLogic employeeLogic;

    private ListForm listForm;

    private EmployeeDto employeeDto;

    private List<Department> deptItems;

    private char crudType;

    @Binding(bindingType = BindingType.MUST)
    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

    public void setListForm(ListForm listForm) {
        this.listForm = listForm;
    }

    public List<Department> getDeptItems() {
        return deptItems;
    }

    public void setCrudType(char crudType) {
        this.crudType = crudType;
    }

    public char getCrudType() {
        return crudType;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void initialize() {
        if (crudType == 'u' && listForm != null) {
            int empno = Integer.valueOf(listForm.getEmpno());
            employeeDto = employeeLogic.getEmployeeDto(empno);
        }
        deptItems = employeeLogic.getAllDepartments();
    }
}
