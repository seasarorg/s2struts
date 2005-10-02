package org.seasar.struts.examples.employee.action.impl;

import org.seasar.struts.examples.employee.action.EmployeeConfirmAction;
import org.seasar.struts.examples.employee.dto.EmployeeDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;


/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeConfirmActionImpl implements EmployeeConfirmAction {

    private EmployeeLogic employeeLogic;

    private EmployeeDto employeeForm;

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }

    public EmployeeDto getEmployeeForm() {
        return employeeForm;
    }

    public void setEmployeeForm(EmployeeDto employeeForm) {
        this.employeeForm = employeeForm;
    }

    public String execute() {
        employeeForm = employeeLogic.getEmployeeDto(new Integer(employeeForm
                .getEmpno()));
        return SUCCESS;
    }

}