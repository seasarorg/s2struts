package org.seasar.struts.examples.employee.action.impl;

import java.util.List;

import org.seasar.struts.examples.employee.action.EmployeeSearchAction;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;


/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeSearchActionImpl implements EmployeeSearchAction {

    private EmployeeLogic employeeLogic;

    private List departmentDtoList;

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }
    
    public List getDepartmentDtoList() {
        return departmentDtoList;
    }

    public String execute() {
        departmentDtoList = employeeLogic.getAllDepartments();
        return SUCCESS;
    }
    
}