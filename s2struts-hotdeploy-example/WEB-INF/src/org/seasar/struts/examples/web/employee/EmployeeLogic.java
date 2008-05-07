package org.seasar.struts.examples.web.employee;

import java.util.List;

import org.seasar.struts.examples.entity.Department;
import org.seasar.struts.examples.entity.Employee;

public interface EmployeeLogic {

    Employee getEmployee();

    List<Department> getAllDepartments();
}
