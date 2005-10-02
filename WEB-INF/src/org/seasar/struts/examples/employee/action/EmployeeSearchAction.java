package org.seasar.struts.examples.employee.action;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.StrutsAction(scope="session", validate=false)
 */
public interface EmployeeSearchAction {

	/**
	 * @org.seasar.struts.annotation.StrutsActionForward(path="/pages/employee/employeeSearch.jsp")
	 */
	public String SUCCESS = "success";

	public String execute();

}