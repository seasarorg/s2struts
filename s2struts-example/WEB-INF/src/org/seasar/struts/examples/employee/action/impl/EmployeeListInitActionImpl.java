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
package org.seasar.struts.examples.employee.action.impl;

import java.util.List;

import org.seasar.struts.action.MessageManager;
import org.seasar.struts.examples.employee.action.EmployeeListInitAction;
import org.seasar.struts.examples.employee.dto.EmployeeSearchDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;

public class EmployeeListInitActionImpl implements EmployeeListInitAction {

    private EmployeeLogic employeeLogic;

    private EmployeeSearchDto employeeSearchForm;

    private List employeeDtoList;

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

    public void setEmployeeSearchForm(EmployeeSearchDto employeeSearchDto) {
        this.employeeSearchForm = employeeSearchDto;
    }

    public List getEmployeeDtoList() {
        return employeeDtoList;
    }

    public String initialize() {
        employeeDtoList = employeeLogic.searchEmployeeDtoList(employeeSearchForm);
        if (employeeDtoList.size() == 0) {
            MessageManager.addGlobalError("errors.bad.criteria");
            MessageManager.saveErrors();
        }
        return null;
    }

}
