package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.ScopeType;
import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(scope = ScopeType.SESSION)
public interface EmployeeSearchAction {

    @StrutsActionForward(path = "/pages/employee/employeeList.jsp")
    public String LIST = "list";

    @StrutsActionForward(path = "/pages/employee/employeeEdit.jsp")
    public String EDIT = "edit";

    public String goList();

    public String goEditForCreate();

}