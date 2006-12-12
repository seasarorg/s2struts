package org.seasar.struts.examples.employee.dao;

import java.util.List;

import org.seasar.struts.examples.employee.dto.EmpPackDto;
import org.seasar.struts.examples.employee.finddto.EmpPackFindDto;

public interface EmpPackDao {
    public Class BEAN = EmpPackDto.class;

    public List getAllEmp();

    public String getEmp_QUERY = "Empno  = ?";
    public EmpPackDto getEmp(int empno);

    public String getEmpByIds_ARGS = "empnos";
    public String getEmpByIds_QUERY = "empno in /*empnos*/(1)";
    public List getEmpByIds(List empnos);
    
    public String find_ARGS = "dto,keys";
    public List find(EmpPackFindDto dto,List keys);

    public EmpPackDto soleMatch(EmpPackFindDto dto);
    
    public void insert(EmpPackDto dto);
    
    public void update(EmpPackDto dto);
    
    public void delete(EmpPackDto dto);
}
