package org.seasar.struts.examples.employee.action;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.StrutsAction(name="employeeForm", input="/employeeEdit.do")
 */
public interface EmployeeEditConfirmAction {

	/**
	 * @org.seasar.struts.annotation.StrutsActionForward(path="/pages/employee/employeeConfirm.jsp")
	 */
	public String SUCCESS = "success";

    /**
     * @org.seasar.struts.annotation.StrutsActionForward(path="/employeeEdit.do")
     */
    public String ERROR = "error";

	public String execute();

}