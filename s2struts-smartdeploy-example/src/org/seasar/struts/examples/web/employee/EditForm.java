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
package org.seasar.struts.examples.web.employee;

import java.io.Serializable;

import org.seasar.struts.annotation.tiger.StrutsActionForm;
import org.seasar.struts.validator.annotation.tiger.DateType;
import org.seasar.struts.validator.annotation.tiger.IntegerType;
import org.seasar.struts.validator.annotation.tiger.Required;

/**
 * @author taedium
 * 
 */
@StrutsActionForm
public class EditForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String empno;

    private String ename;

    private String job;

    private String mgr;

    private String hiredate;

    private String sal;

    private String comm;

    private String deptno;

    private String versionNo;

    public EditForm() {
    }

    public String getEmpno() {
        return empno;
    }

    @Required
    @IntegerType
    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    @Required
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

    @IntegerType
    public void setMgr(String mgr) {
        this.mgr = mgr;
    }

    public String getHiredate() {
        return hiredate;
    }

    @DateType(pattern = "yyyy/MM/dd")
    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getSal() {
        return sal;
    }

    @IntegerType
    public void setSal(String sal) {
        this.sal = sal;
    }

    public String getComm() {
        return comm;
    }

    @IntegerType
    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getDeptno() {
        return deptno;
    }

    @Required
    @IntegerType
    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

}
