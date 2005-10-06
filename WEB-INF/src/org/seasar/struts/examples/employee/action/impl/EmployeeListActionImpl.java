package org.seasar.struts.examples.employee.action.impl;

import org.seasar.struts.annotation.ExportToSession;
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
