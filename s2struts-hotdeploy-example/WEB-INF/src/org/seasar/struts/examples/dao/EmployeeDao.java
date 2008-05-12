package org.seasar.struts.examples.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.annotation.tiger.SqlFile;
import org.seasar.struts.examples.dto.EmployeeDto;
import org.seasar.struts.examples.dto.EmployeeSearchDto;
import org.seasar.struts.examples.entity.Employee;

@S2Dao(bean = Employee.class)
public interface EmployeeDao {

    @SqlFile
    List<EmployeeDto> getEmployeeDtoList(EmployeeSearchDto dto);

    @Arguments("empno")
    EmployeeDto getEmployeeDto(int empno);

    void insert(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);
}
