/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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
import org.seasar.struts.examples.common.Constants;
import org.seasar.struts.examples.employee.action.EmployeeEditAction;
import org.seasar.struts.examples.employee.dto.EmployeeDto;
import org.seasar.struts.examples.employee.dto.ProcessModeDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;

/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeEditActionImpl implements EmployeeEditAction {

    private EmployeeLogic employeeLogic;

    private EmployeeDto employeeForm;

    private ProcessModeDto processModeDto;

    private List departmentDtoList;

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

    public EmployeeDto getEmployeeForm() {
        return employeeForm;
    }

    public void setEmployeeForm(EmployeeDto employeeForm) {
        this.employeeForm = employeeForm;
    }

    public void setProcessModeDto(ProcessModeDto processModeDto) {
        this.processModeDto = processModeDto;
    }

    public List getDepartmentDtoList() {
        return departmentDtoList;
    }

    public String goConfirm() {
        if (processModeDto.getProcessMode() == Constants.CREATE_MODE
                && employeeLogic.existEmployee(new Integer(employeeForm.getEmpno()))) {
            MessageManager.addGlobalError("errors.employee.exist", employeeForm.getEmpno());
            MessageManager.saveErrors();
            return ERROR;
        }
        return CONFIRM;
    }

    public String goPrevious() {
        switch (processModeDto.getProcessMode()) {
        case Constants.CREATE_MODE:
            return SEARCH;
        case Constants.UPDATE_MODE:
            return LIST;
        default:
            return null;
        }
    }

}