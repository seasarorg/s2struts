package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(name = "employeeForm", input = "/pages/employee/employeeEdit.html")
public interface EmployeeConfirmAction {

    @StrutsActionForward(path = "/pages/employee/employeeEdit.html")
    public String EDIT = "edit";

    @StrutsActionForward(path = "/pages/employee/employeeSearch.html", redirect = true)
    public String SEARCH = "search";

    @StrutsActionForward(path = "/pages/employee/employeeList.html", redirect = true)
    public String LIST = "list";

    public String store();

    public String goPrevious();

}