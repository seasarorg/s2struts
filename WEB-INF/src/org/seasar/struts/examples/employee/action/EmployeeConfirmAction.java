package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(name = "employeeForm", validate = false)
public interface EmployeeConfirmAction {

    @StrutsActionForward(path = "/pages/employee/employeeConfirm.jsp")
    public String SUCCESS = "success";

    public String execute();

}