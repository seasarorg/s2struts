package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(name = "employeeForm")
public interface EmployeeEditAction {
    
    @StrutsActionForward(path = "/pages/employee/employeeEdit.html")
    public String ERROR = "error";

    @StrutsActionForward(path = "/pages/employee/employeeConfirm.html")
    public String CONFIRM = "confirm";

    @StrutsActionForward(path = "/pages/employee/employeeSearch.html", redirect = true)
    public String SEARCH = "search";

    @StrutsActionForward(path = "/pages/employee/employeeList.html", redirect = true)
    public String LIST = "list";

    public String goConfirm();

    public String goPrevious();

}