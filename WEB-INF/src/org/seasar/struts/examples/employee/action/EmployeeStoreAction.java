package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(name = "employeeForm", input = "/employeeEdit.do")
public interface EmployeeStoreAction {

    @StrutsActionForward(path = "/employee.do?method=goStoreToNext")
    public String SUCCESS = "success";

    public String execute();

}