package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(name = "employeeForm", input = "/pages/employee/employeeEdit.jsp")
public interface EmployeeEditAction {
    
    @StrutsActionForward(path = "/pages/employee/employeeEdit.jsp")
    public String ERROR = "error";

    @StrutsActionForward(path = "/pages/employee/employeeConfirm.jsp")
    public String CONFIRM = "confirm";

    @StrutsActionForward(path = "/pages/employee/employeeSearch.jsp")
    public String SEARCH = "search";

    @StrutsActionForward(path = "/pages/employee/employeeList.jsp")
    public String LIST = "list";

    public String goConfirm();

    public String goPrevious();

}