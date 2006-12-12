package org.seasar.struts.examples.employee.dto;

import org.seasar.struts.examples.employee.dto.EmpDto;
import org.seasar.struts.examples.employee.dto.DeptDto;

public class EmpPackDto extends EmpDto {

	public static final int deptDto_RELNO = 0;
	public static final String deptDto_RELKEYS = "deptno:deptno";


	private DeptDto dept;
	

	public DeptDto getDeptDto() {
		return this.dept;
	}

	public void setDeptDto(DeptDto dept) {
		this.dept = dept;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toString());
		buf.append("deptDto").append(dept);
		return buf.toString();
	}

}
