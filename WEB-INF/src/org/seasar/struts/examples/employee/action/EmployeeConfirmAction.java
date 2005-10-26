package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(name = "employeeForm", input = "/pages/employee/employeeEdit.jsp")
public interface EmployeeConfirmAction {

    @StrutsActionForward(path = "/pages/employee/employeeEdit.jsp")
    public String EDIT = "edit";

    @StrutsActionForward(path = "/pages/employee/employeeSearch.jsp", redirect = true)
    public String SEARCH = "search";

    @StrutsActionForward(path = "/pages/employee/employeeList.jsp", redirect = true)
    public String LIST = "list";

    public String store();

    public String goPrevious();

}