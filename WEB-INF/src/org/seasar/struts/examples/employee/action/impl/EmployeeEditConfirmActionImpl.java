package org.seasar.struts.examples.employee.action.impl;

import org.seasar.struts.action.MessageManager;
import org.seasar.struts.examples.common.Constants;
import org.seasar.struts.examples.employee.action.EmployeeEditConfirmAction;
import org.seasar.struts.examples.employee.dto.EmployeeDto;
import org.seasar.struts.examples.employee.dto.ProcessModeDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;


/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeEditConfirmActionImpl implements EmployeeEditConfirmAction {

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
        if (!validate()) {
            return ERROR;
        }
        
        if (employeeForm.getDeptno().length() != 0) {
            String dname = employeeLogic.getDname(new Integer(employeeForm.getDeptno()));
            employeeForm.setDname(dname);
        }
        return SUCCESS;
    }

    public boolean validate() {
        if (processModeDto.getProcessMode() != Constants.CREATE_MODE) {
            return true;
        }
        
        boolean result = true;
        if (employeeLogic.existEmployee(new Integer(employeeForm.getEmpno()))) {
            MessageManager.addError("errors.employee.exist", employeeForm.getEmpno());
            MessageManager.saveErrors();
            result = false;
        }
        return result;
    }

}