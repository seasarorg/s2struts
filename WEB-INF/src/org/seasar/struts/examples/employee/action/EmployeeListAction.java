package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(name = "employeeForm", parameter = "method", validate = false)
public interface EmployeeListAction {

    @StrutsActionForward(path = "/pages/employee/employeeEdit.jsp")
    public String EDIT = "edit";

    @StrutsActionForward(path = "/pages/employee/employeeConfirm.jsp")
    public String CONFIRM = "confirm";

    public String goEditForUpdate();

    public String goDelete();

    public String goInquire();

}