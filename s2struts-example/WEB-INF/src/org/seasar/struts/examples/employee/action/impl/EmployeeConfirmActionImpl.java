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

import org.seasar.struts.examples.common.Constants;
import org.seasar.struts.examples.employee.action.EmployeeConfirmAction;
import org.seasar.struts.examples.employee.dto.EmployeeDto;
import org.seasar.struts.examples.employee.dto.ProcessModeDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;

/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeConfirmActionImpl implements EmployeeConfirmAction {

    private EmployeeLogic employeeLogic;

    private ProcessModeDto processModeDto;

    private EmployeeDto employeeForm;

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

    public void setProcessModeDto(ProcessModeDto processModeDto) {
        this.processModeDto = processModeDto;
    }

    public EmployeeDto getEmployeeForm() {
        return employeeForm;
    }

    public void setEmployeeForm(EmployeeDto employeeDto) {
        this.employeeForm = employeeDto;
    }

    public String store() {
        switch (processModeDto.getProcessMode()) {
        case Constants.CREATE_MODE:
            employeeLogic.insert(employeeForm);
            return SEARCH;
        case Constants.UPDATE_MODE:
            employeeLogic.update(employeeForm);
            return LIST;
        case Constants.DELETE_MODE:
            employeeLogic.delete(employeeForm);
            return LIST;
        default:
            return SEARCH;
        }
    }

    public String goPrevious() {
        switch (processModeDto.getProcessMode()) {
        case Constants.CREATE_MODE:
        case Constants.UPDATE_MODE:
            return EDIT;
        default:
            return LIST;
        }
    }

}