package org.seasar.struts.examples.employee.action.impl;

import org.seasar.struts.examples.employee.action.EmployeeConfirmInitAction;
import org.seasar.struts.examples.employee.dto.EmployeeDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;
import org.seasar.struts.examples.util.StringUtil;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public class EmployeeConfirmInitActionImpl implements EmployeeConfirmInitAction {

    private EmployeeLogic employeeLogic;
    
    private EmployeeDto employeeForm;

    public void setEmployeeLogic(EmployeeLogic employeeLogic) {
        this.employeeLogic = employeeLogic;
    }
    
    public void setEmployeeForm(EmployeeDto employeeForm) {
        this.employeeForm = employeeForm;
    }

    public String initialize() {
        if (!StringUtil.isEmpty(employeeForm.getDeptno())) {
            String dname = employeeLogic.getDname(new Integer(employeeForm.getDeptno()));
            employeeForm.setDname(dname);
        }
        return null;
    }
    
}
