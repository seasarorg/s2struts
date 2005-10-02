package org.seasar.struts.examples.employee.action;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.StrutsAction(name="employeeForm", validate=false)
 */
public interface EmployeeEditAction {

	/**
	 * @org.seasar.struts.annotation.StrutsActionForward(path="/pages/employee/employeeEdit.jsp")
	 */
	public String SUCCESS = "success";

	public String execute();

}