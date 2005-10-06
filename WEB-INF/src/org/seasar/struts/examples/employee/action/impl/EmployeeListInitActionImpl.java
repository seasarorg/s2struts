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
            MessageManager.addError("errors.bad.criteria");
            MessageManager.saveErrors();
        }
        return null;
    }

}
