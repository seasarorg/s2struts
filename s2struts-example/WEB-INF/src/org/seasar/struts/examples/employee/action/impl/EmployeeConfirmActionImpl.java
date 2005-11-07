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