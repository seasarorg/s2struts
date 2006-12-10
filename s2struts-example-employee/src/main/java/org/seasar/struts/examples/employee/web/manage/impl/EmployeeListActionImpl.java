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

import org.seasar.struts.examples.employee.dto.EmpDto;
import org.seasar.struts.examples.employee.dto.EmpPackDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;
import org.seasar.struts.examples.employee.web.manage.EmployeeDxo;
import org.seasar.struts.examples.employee.web.manage.EmployeeForm;
import org.seasar.struts.examples.employee.web.manage.EmployeeListAction;

/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeListActionImpl implements EmployeeListAction {

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

    public void setEmployeeForm(EmployeeForm employeeForm) {
        this.employeeForm = employeeForm;
    }

    //
    //
    //

    public String goEditForUpdate() {
        this.loadEmployee();
        return EmployeeListAction.UPDATE;
    }

    public String goDelete() {
        this.loadEmployee();
        return EmployeeListAction.DELETE;
    }

    public String goInquire() {
        this.loadEmployee();
        return EmployeeListAction.REFER;
    }

    private void loadEmployee() {
        EmpDto dto = this.employeeDxo.convertEmpDto(this.employeeForm);
        EmpPackDto result = this.employeeLogic.getEmp(dto);
        this.employeeForm = this.employeeDxo.convertEmployeeForm(result);
    }

}
