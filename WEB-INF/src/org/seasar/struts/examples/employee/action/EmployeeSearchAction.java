package org.seasar.struts.examples.employee.action;

import org.seasar.struts.annotation.ScopeType;
import org.seasar.struts.annotation.StrutsAction;
import org.seasar.struts.annotation.StrutsActionForward;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsAction(scope=ScopeType.SESSION, validate=false)
public interface EmployeeSearchAction {

    @StrutsActionForward(path="/pages/employee/employeeSearch.jsp")
    public String SUCCESS = "success";

    public String execute();

}