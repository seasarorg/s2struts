package org.seasar.struts.examples.employee.dto;

import org.seasar.struts.examples.employee.entity.Employee;

/**
 * @author Katsuhiko Nagashima
 * @org.seasar.struts.annotation.StrutsActionForm(name="employeeForm")
 */
public class EmployeeDto extends Employee {

	private String dname = "";
	
	public EmployeeDto() {
	}
	
	public String getDname() {
		return dname;
	}
	
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	protected void setupToString(StringBuffer buf) {
		super.setupToString(buf);
		buf.append(", ");
		buf.append(dname);
	}
}
