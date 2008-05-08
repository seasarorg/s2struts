package org.seasar.struts.examples.web.employee.impl;

import java.util.List;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.struts.examples.dao.DepartmentDao;
import org.seasar.struts.examples.dao.EmployeeDao;
import org.seasar.struts.examples.dto.EmployeeDto;
import org.seasar.struts.examples.dto.EmployeeSearchDto;
import org.seasar.struts.examples.entity.Department;
import org.seasar.struts.examples.entity.Employee;
import org.seasar.struts.examples.web.employee.EmployeeLogic;

public class EmployeeLogicImpl implements EmployeeLogic {

    private EmployeeDao employeeDao;

    private DepartmentDao departmentDao;

    @Binding(bindingType = BindingType.MUST)
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Binding(bindingType = BindingType.MUST)
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public List<Employee> getEmployees(EmployeeSearchDto dto) {
        return employeeDao.getEmployees(dto);
    }

    public EmployeeDto getEmployeeDto(int empno) {
        return employeeDao.getEmployeeDto(empno);
    }

    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    public void delete(Employee employee) {
        employeeDao.delete(employee);
    }

    public void insert(Employee employee) {
        employeeDao.insert(employee);
    }

    public void update(Employee employee) {
        employeeDao.update(employee);
    }

}
