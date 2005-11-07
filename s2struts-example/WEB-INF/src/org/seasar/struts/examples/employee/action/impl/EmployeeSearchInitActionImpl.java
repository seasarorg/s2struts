package org.seasar.struts.examples.employee.action.impl;

import java.util.List;

import org.seasar.struts.examples.employee.action.EmployeeSearchInitAction;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;

public class EmployeeSearchInitActionImpl implements EmployeeSearchInitAction {

    private EmployeeLogic employeeLogic;

    private List departmentDtoList;

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }
    
    public List getDepartmentDtoList() {
        return departmentDtoList;
    }

    public String initialize() {
        departmentDtoList = employeeLogic.getAllDepartments();
        return null;
    }
    
}