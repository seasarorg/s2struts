package org.seasar.struts.examples.web.employee.impl;

import java.util.List;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.struts.examples.entity.Department;
import org.seasar.struts.examples.entity.Employee;
import org.seasar.struts.examples.web.employee.DepartmentDao;
import org.seasar.struts.examples.web.employee.EmployeeDao;
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

    public Employee getEmployee() {
        return null;
    }

    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

}
