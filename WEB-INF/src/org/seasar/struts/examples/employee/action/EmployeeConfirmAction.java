package org.seasar.struts.examples.employee.action;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.StrutsAction(name="employeeForm", validate=false)
 */
public interface EmployeeConfirmAction {

    /**
     * @org.seasar.struts.annotation.StrutsActionForward(path="/pages/employee/employeeConfirm.jsp")
     */
    public String SUCCESS = "success";

    public String execute();

}