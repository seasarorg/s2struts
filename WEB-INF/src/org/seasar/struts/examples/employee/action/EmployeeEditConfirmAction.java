package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(name="employeeForm", input="/employeeEdit.do")
public interface EmployeeEditConfirmAction {

    @StrutsActionForward(path="/pages/employee/employeeConfirm.jsp")
    public String SUCCESS = "success";

    @StrutsActionForward(path="/employeeEdit.do")
    public String ERROR = "error";

    public String execute();

}