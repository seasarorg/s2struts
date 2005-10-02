package org.seasar.struts.examples.exception;

public class EmployeeNotFoundRuntimeException extends AppRuntimeException {

	private int empno;

	public EmployeeNotFoundRuntimeException(int empno) {
		super("examples.jsf.EmployeeNotFound", new Object[]{String.valueOf(empno)});
		this.empno = empno;
	}
	
	public int getEmpno() {
		return empno;
	}
}