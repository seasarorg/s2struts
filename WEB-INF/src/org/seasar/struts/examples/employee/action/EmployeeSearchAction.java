package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.tiger.ScopeType;
import org.seasar.struts.annotation.tiger.StrutsAction;
import org.seasar.struts.annotation.tiger.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(scope = ScopeType.SESSION)
public interface EmployeeSearchAction {

    @StrutsActionForward(path = "/pages/employee/employeeList.html")
    public String LIST = "list";

    @StrutsActionForward(path = "/pages/employee/employeeEdit.html", redirect = true)
    public String EDIT = "edit";

    public String goList();

    public String goEditForCreate();

}