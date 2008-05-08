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
package org.seasar.struts.examples.web.employee.impl;

import java.io.Serializable;

import org.seasar.struts.annotation.tiger.StrutsActionForm;

/**
 * @author taedium
 * 
 */
@StrutsActionForm
public class SearchForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String empno;

    private String ename;

    private String job;

    private String mgr;

    private String fromHiredate;

    private String toHiredate;

    private String fromSal;

    private String toSal;

    private String deptno;

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
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

    public String getMgr() {
        return mgr;
    }

    public void setMgr(String mgr) {
        this.mgr = mgr;
    }

    public String getFromHiredate() {
        return fromHiredate;
    }

    public void setFromHiredate(String fromHiredate) {
        this.fromHiredate = fromHiredate;
    }

    public String getToHiredate() {
        return toHiredate;
    }

    public void setToHiredate(String toHiredate) {
        this.toHiredate = toHiredate;
    }

    public String getFromSal() {
        return fromSal;
    }

    public void setFromSal(String fromSal) {
        this.fromSal = fromSal;
    }

    public String getToSal() {
        return toSal;
    }

    public void setToSal(String toSal) {
        this.toSal = toSal;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

}
