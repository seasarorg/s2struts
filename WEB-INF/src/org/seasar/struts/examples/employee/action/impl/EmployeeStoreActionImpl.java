package org.seasar.struts.examples.employee.action.impl;

import org.seasar.struts.examples.common.Constants;
import org.seasar.struts.examples.employee.action.EmployeeStoreAction;
import org.seasar.struts.examples.employee.dto.EmployeeDto;
import org.seasar.struts.examples.employee.dto.ProcessModeDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;


/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeStoreActionImpl implements EmployeeStoreAction {

    private EmployeeLogic employeeLogic;

    private EmployeeDto employeeForm;

    private ProcessModeDto processModeDto;

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

    public String execute() {
        switch (processModeDto.getProcessMode()) {
        case Constants.CREATE_MODE :
            employeeLogic.insert(employeeForm);
            break;
        case Constants.UPDATE_MODE :
            employeeLogic.update(employeeForm);
            break;
        case Constants.DELETE_MODE :
            employeeLogic.delete(employeeForm);
            break;
        }
        return SUCCESS;
    }
    
}