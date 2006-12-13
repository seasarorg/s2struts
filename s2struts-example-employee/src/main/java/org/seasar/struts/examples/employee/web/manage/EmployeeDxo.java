package org.seasar.struts.examples.employee.web.manage;

import java.util.List;

import org.seasar.struts.examples.employee.dto.DeptDto;
import org.seasar.struts.examples.employee.dto.EmpDto;
import org.seasar.struts.examples.employee.dto.EmpPackDto;
import org.seasar.struts.examples.employee.finddto.EmpPackFindDto;

public interface EmployeeDxo {

    String DATE_PATTERN = "yyyy/MM/dd";

    EmpDto convertEmpDto(EmployeeForm employeeForm);

    DeptDto convertDeptDto(EmployeeForm employeeForm);

    EmpPackFindDto convertEmpPackFindDto(EmployeeSearchForm employeeSearchForm);

    EmployeeForm convertEmployeeForm(EmpPackDto result);

    EmployeeForm[] convertEmployeeForms(List result);

}
