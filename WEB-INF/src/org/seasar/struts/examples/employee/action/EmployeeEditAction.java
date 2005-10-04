package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(name = "employeeForm", validate = false)
public interface EmployeeEditAction {

    @StrutsActionForward(path = "/pages/employee/employeeEdit.jsp")
    public String SUCCESS = "success";

    public String execute();

}