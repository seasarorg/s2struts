package org.seasar.struts.examples.employee.dao;

import java.util.List;

import org.seasar.struts.examples.employee.dto.EmpDto;
import org.seasar.struts.examples.employee.finddto.EmpFindDto;

public interface EmpDao {
    public Class BEAN = EmpDto.class;

    public List getAllEmp();

    public String getEmp_QUERY = "empno = ?";
    public EmpDto getEmp(int empno);

    public String getEmpByIds_ARGS = "empnos";
    public String getEmpByIds_QUERY = "empno in /*empnos*/(1)";
    public List getEmpByIds(List empnos);
    
    public String find_ARGS = "dto,keys";
    public List find(EmpFindDto dto,List keys);

    public EmpDto soleMatch(EmpFindDto dto);
    
    public void insert(EmpDto dto);
    
    public void update(EmpDto dto);
    
    public void delete(EmpDto dto);
}

