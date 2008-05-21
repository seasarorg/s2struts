package org.seasar.struts.examples.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.struts.examples.entity.Department;

@S2Dao(bean = Department.class)
public interface DepartmentDao {

    List<Department> getDepartmentList();

    @Arguments("deptno")
    Department getDepartment(int deptno);
}
