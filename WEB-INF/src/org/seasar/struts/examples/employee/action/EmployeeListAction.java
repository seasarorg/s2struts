package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.ScopeType;
import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(name = "employeeSearchForm", scope = ScopeType.SESSION, input = "/employeeSearch.do")
public interface EmployeeListAction {

    @StrutsActionForward(path = "/pages/employee/employeeList.jsp")
    public String SUCCESS = "success";

    @StrutsActionForward(path = "/employeeSearch.do")
    public String ERROR = "error";

    public String execute();

}