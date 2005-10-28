package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(name = "employeeForm", parameter = "method", validate = false)
public interface EmployeeListAction {

    @StrutsActionForward(path = "/pages/employee/employeeEdit.html")
    public String EDIT = "edit";

    @StrutsActionForward(path = "/pages/employee/employeeConfirm.html")
    public String CONFIRM = "confirm";

    public String goEditForUpdate();

    public String goDelete();

    public String goInquire();

}