package org.seasar.struts.examples.employee.logic;

import java.util.List;

import org.seasar.struts.examples.employee.dto.EmployeeDto;
import org.seasar.struts.examples.employee.dto.EmployeeSearchDto;


public interface EmployeeLogic {

	public int getSearchCount(EmployeeSearchDto dto);

	public List searchEmployeeDtoList(EmployeeSearchDto dto);
	
	public EmployeeDto getEmployeeDto(Integer empno);
	
	public boolean existEmployee(Integer empno);
	
	public List getAllDepartments();
	
	public String getDname(Integer deptno);
	
	public void insert(EmployeeDto dto);
	
	public void update(EmployeeDto dto);
	
	public void delete(EmployeeDto dto);
}
