/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.struts.examples.employee.logic.impl;

import java.util.List;

import org.seasar.struts.examples.employee.dao.DeptDao;
import org.seasar.struts.examples.employee.dao.EmpDao;
import org.seasar.struts.examples.employee.dao.EmpPackDao;
import org.seasar.struts.examples.employee.dto.DeptDto;
import org.seasar.struts.examples.employee.dto.EmpDto;
import org.seasar.struts.examples.employee.dto.EmpPackDto;
import org.seasar.struts.examples.employee.finddto.EmpPackFindDto;
import org.seasar.struts.examples.employee.logic.EmployeeLogic;

public class EmployeeLogicImpl implements EmployeeLogic {

    private EmpDao empDao;

    public void setEmpDao(EmpDao empDao) {
        this.empDao = empDao;
    }

    private DeptDao deptDao;

    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    private EmpPackDao empPackDao;

    public void setEmpPackDao(EmpPackDao empPackDao) {
        this.empPackDao = empPackDao;
    }

    //
    //
    //

    public boolean existEmp(EmpDto dto) {
        return (this.empDao.getEmp(dto.getEmpno()) != null);
    }

    public List findEmp(EmpPackFindDto dto) {
        return this.empPackDao.find(dto, null);
    }

    public EmpPackDto getEmp(EmpDto dto) {
        return this.empPackDao.getEmp(dto.getEmpno());
    }

    public List getAllDept() {
        return this.deptDao.getAllDept();
    }

    public DeptDto getDept(DeptDto dto) {
        return this.deptDao.getDept(dto.getDeptno());
    }

    public void insertEmp(EmpDto dto) {
        this.empDao.insert(dto);
    }

    public void updateEmp(EmpDto dto) {
        this.empDao.update(dto);
    }

    public void deleteEmp(EmpDto dto) {
        this.empDao.delete(dto);
    }

}
