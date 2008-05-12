package org.seasar.struts.examples.web.employee;

import java.util.List;

import org.seasar.struts.examples.dto.EmployeeDto;
import org.seasar.struts.examples.dto.EmployeeSearchDto;
import org.seasar.struts.examples.entity.Department;
import org.seasar.struts.examples.entity.Employee;

public interface EmployeeLogic {

    List<EmployeeDto> getEmployeeDtoList(EmployeeSearchDto dto);

    EmployeeDto getEmployeeDto(int empno);

    List<Department> getDepartmentList();

    String getDeptno(int deptno);

    void insert(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);

}
