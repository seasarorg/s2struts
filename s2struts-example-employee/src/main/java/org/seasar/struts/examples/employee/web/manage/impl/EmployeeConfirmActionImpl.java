/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.struts.examples.employee.web.manage.impl;

import org.seasar.struts.examples.employee.CrudType;
import org.seasar.struts.examples.employee.dto.EmpDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;
import org.seasar.struts.examples.employee.web.manage.EmployeeConfirmAction;
import org.seasar.struts.examples.employee.web.manage.EmployeeDxo;
import org.seasar.struts.examples.employee.web.manage.EmployeeForm;

/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeConfirmActionImpl implements EmployeeConfirmAction {

    private EmployeeDxo employeeDxo;

    public void setEmployeeDxo(EmployeeDxo employeeDxo) {
        this.employeeDxo = employeeDxo;
    }

    private EmployeeLogic employeeLogic;

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

    private EmployeeForm employeeForm;

    public EmployeeForm getEmployeeForm() {
        return this.employeeForm;
    }

    public void setEmployeeForm(EmployeeForm employeeDto) {
        this.employeeForm = employeeDto;
    }

    private CrudType crudType = new CrudType();

    public void setMode(String mode) {
        this.crudType.setMode(mode);
    }

    //
    //
    //

    public String doStore() {
        EmpDto dto = this.employeeDxo.convertEmpDto(this.employeeForm);

        if (this.crudType.isCreateMode()) {
            this.employeeLogic.insertEmp(dto);
            return EmployeeConfirmAction.SEARCH;
        }
        if (this.crudType.isUpdateMode()) {
            this.employeeLogic.updateEmp(dto);
            return EmployeeConfirmAction.LIST;
        }
        if (this.crudType.isDeleteMode()) {
            this.employeeLogic.deleteEmp(dto);
            return EmployeeConfirmAction.LIST;
        }
        return null;
    }

    public String goPrevious() {
        if (this.crudType.isCreateMode()) {
            return EmployeeConfirmAction.EDIT;
        }
        if (this.crudType.isUpdateMode()) {
            return EmployeeConfirmAction.EDIT;
        }
        if (this.crudType.isDeleteMode()) {
            return EmployeeConfirmAction.LIST;
        }
        if (this.crudType.isReferMode()) {
            return EmployeeConfirmAction.LIST;
        }
        return null;
    }

}