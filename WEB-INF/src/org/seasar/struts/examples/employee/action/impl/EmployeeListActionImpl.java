package org.seasar.struts.examples.employee.action.impl;

import java.util.List;

import org.seasar.struts.action.MessageManager;
import org.seasar.struts.examples.employee.action.EmployeeListAction;
import org.seasar.struts.examples.employee.dto.EmployeeSearchDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;


/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeListActionImpl implements EmployeeListAction {

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

    public String execute() {
        employeeDtoList = employeeLogic.searchEmployeeDtoList(employeeSearchForm);
        if (employeeDtoList.size() == 0) {
            MessageManager.addError("errors.bad.criteria");
            MessageManager.saveErrors();
            return ERROR;
        }
        return SUCCESS;
    }
    
}
