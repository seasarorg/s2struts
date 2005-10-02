package org.seasar.struts.examples.employee.action;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.StrutsAction(name="employeeForm", input="/employeeEdit.do")
 */
public interface EmployeeStoreAction {

	/**
	 * @org.seasar.struts.annotation.StrutsActionForward(path="/employee.do?method=goStoreToNext")
	 */
	public String SUCCESS = "success";

	public String execute();

}