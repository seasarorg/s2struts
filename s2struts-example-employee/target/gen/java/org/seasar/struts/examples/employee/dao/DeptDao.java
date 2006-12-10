package org.seasar.struts.examples.employee.dao;

import java.util.List;

import org.seasar.struts.examples.employee.dto.DeptDto;
import org.seasar.struts.examples.employee.finddto.DeptFindDto;

public interface DeptDao {
    public Class BEAN = DeptDto.class;

    public List getAllDept();

    public String getDept_QUERY = "deptno = ?";
    public DeptDto getDept(int deptno);

    public String getDeptByIds_ARGS = "deptnos";
    public String getDeptByIds_QUERY = "deptno in /*deptnos*/(1)";
    public List getDeptByIds(List deptnos);
    
    public String find_ARGS = "dto,keys";
    public List find(DeptFindDto dto,List keys);

    public DeptDto soleMatch(DeptFindDto dto);
    
    public void insert(DeptDto dto);
    
    public void update(DeptDto dto);
    
    public void delete(DeptDto dto);
}

