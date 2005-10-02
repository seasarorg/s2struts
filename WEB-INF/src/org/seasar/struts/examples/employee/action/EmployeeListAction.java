package org.seasar.struts.examples.employee.action;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.StrutsAction(name="employeeSearchForm", scope="session",
 *                                                                   input="/employeeSearch.do")
 */
public interface EmployeeListAction {

	/**
	 * @org.seasar.struts.annotation.StrutsActionForward(path="/pages/employee/employeeList.jsp")
	 */
	public String SUCCESS = "success";

    /**
     * @org.seasar.struts.annotation.StrutsActionForward(path="/employeeSearch.do")
     */
    public String ERROR = "error";

	public String execute();

}