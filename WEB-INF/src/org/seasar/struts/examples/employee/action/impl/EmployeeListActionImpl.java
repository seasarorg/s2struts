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

import org.seasar.struts.annotation.tiger.ExportToSession;
import org.seasar.struts.examples.common.Constants;
import org.seasar.struts.examples.employee.action.EmployeeListAction;
import org.seasar.struts.examples.employee.dto.EmployeeDto;
import org.seasar.struts.examples.employee.dto.ProcessModeDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;

/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeListActionImpl implements EmployeeListAction {

    private EmployeeLogic employeeLogic;

    private EmployeeDto employeeForm;

    private ProcessModeDto processModeDto = new ProcessModeDto();

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

    public EmployeeDto getEmployeeForm() {
        return employeeForm;
    }

    public void setEmployeeForm(EmployeeDto employeeForm) {
        this.employeeForm = employeeForm;
    }

    @ExportToSession
    public ProcessModeDto getProcessModeDto() {
        return processModeDto;
    }

    public String goEditForUpdate() {
        loadEmployee();
        processModeDto.setProcessMode(Constants.UPDATE_MODE);
        return EDIT;
    }

    public String goDelete() {
        loadEmployee();
        processModeDto.setProcessMode(Constants.DELETE_MODE);
        return CONFIRM;
    }

    public String goInquire() {
        loadEmployee();
        processModeDto.setProcessMode(Constants.REFER_MODE);
        return CONFIRM;
    }

    private void loadEmployee() {
        employeeForm = employeeLogic.getEmployeeDto(new Integer(employeeForm
                .getEmpno()));
    }

}
