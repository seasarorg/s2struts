package org.seasar.struts.examples.employee.dao;

import java.util.List;

import org.seasar.struts.examples.employee.dto.DepartmentDto;


public interface DepartmentDtoDao {

	public Class BEAN = DepartmentDto.class;

	public List getAllDepartments();
	
	public String getDname_ARGS = "depno";

	public String getDname(Integer deptno);
}
