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

import java.util.List;

import org.seasar.struts.examples.employee.finddto.EmpPackFindDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;
import org.seasar.struts.examples.employee.web.manage.EmployeeDxo;
import org.seasar.struts.examples.employee.web.manage.EmployeeForm;
import org.seasar.struts.examples.employee.web.manage.EmployeeListInitAction;
import org.seasar.struts.examples.employee.web.manage.EmployeeSearchForm;
import org.seasar.struts.pojo.MessageManager;

public class EmployeeListInitActionImpl implements EmployeeListInitAction {

    private EmployeeDxo employeeDxo;

    public void setEmployeeDxo(EmployeeDxo employeeDxo) {
        this.employeeDxo = employeeDxo;
    }

    private EmployeeLogic employeeLogic;

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

    private EmployeeSearchForm employeeSearchForm;

    public void setEmployeeSearchForm(EmployeeSearchForm employeeSearchForm) {
        this.employeeSearchForm = employeeSearchForm;
    }

    private EmployeeForm[] employeeForms;

    public EmployeeForm[] getEmployeeForms() {
        return this.employeeForms;
    }

    //
    //
    //

    public void initialize() {
        EmpPackFindDto dto = this.employeeDxo
                .convertEmpPackFindDto(this.employeeSearchForm);

        List result = this.employeeLogic.findEmp(dto);
        if (result.size() == 0) {
            MessageManager.addGlobalError("errors.bad.criteria");
            MessageManager.saveErrors();
        }
        this.employeeForms = this.employeeDxo.convertEmployeeForms(result);
    }

}
