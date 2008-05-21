/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.struts.examples.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author taedium
 * 
 */
public class EmployeeSearchDto {

    private Integer empno;

    private String ename;

    private String job;

    private Integer mgr;

    private Date fromHiredate;

    private Date toHiredate;

    private BigDecimal fromSal;

    private BigDecimal toSal;

    private Integer deptno;

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getFromHiredate() {
        return fromHiredate;
    }

    public void setFromHiredate(Date fromHiredate) {
        this.fromHiredate = fromHiredate;
    }

    public Date getToHiredate() {
        return toHiredate;
    }

    public void setToHiredate(Date toHiredate) {
        this.toHiredate = toHiredate;
    }

    public BigDecimal getFromSal() {
        return fromSal;
    }

    public void setFromSal(BigDecimal fromSal) {
        this.fromSal = fromSal;
    }

    public BigDecimal getToSal() {
        return toSal;
    }

    public void setToSal(BigDecimal toSal) {
        this.toSal = toSal;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

}
