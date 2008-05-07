package org.seasar.struts.examples.web.employee;

import java.util.List;

import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.struts.examples.entity.Department;

@S2Dao(bean = Department.class)
public interface DepartmentDao {

    List<Department> getAllDepartments();
}
