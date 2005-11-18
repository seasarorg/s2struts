/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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
package org.seasar.struts.examples.employee.entity;

import java.io.Serializable;

import org.seasar.struts.examples.util.DateUtil;
import org.seasar.struts.examples.util.StringUtil;
import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.DateType;
import org.seasar.struts.validator.annotation.tiger.IntegerType;
import org.seasar.struts.validator.annotation.tiger.NoValidate;
import org.seasar.struts.validator.annotation.tiger.Required;

public class Employee implements Serializable {

    public static final String TABLE = "EMP";

    private String empno = "";

    private String ename = "";

    private String job = "";

    private String mgr = "";

    private String hiredateDisplay = "";

    private String sal = "";

    private String comm = "";

    private String deptno = "";

    private int versionNo;

    public Employee() {
    }

    public String getEmpno() {
        return this.empno;
    }

    @Required
    @IntegerType
    @Args(keys = "form.employee.no")
    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public java.lang.String getEname() {
        return this.ename;
    }

    @Required
    @Args(keys = "form.employee.name")
    public void setEname(java.lang.String ename) {
        this.ename = ename;
    }

    public java.lang.String getJob() {
        return this.job;
    }

    public void setJob(java.lang.String job) {
        this.job = job;
    }

    public String getMgr() {
        return StringUtil.toNumericString(this.mgr);
    }

    @IntegerType
    @Args(keys = "form.employee.manager")
    public void setMgr(String mgr) {
        this.mgr = mgr;
    }

    public java.util.Date getHiredate() {
        return DateUtil.toDate(this.hiredateDisplay);
    }

    @NoValidate
    public void setHiredate(java.util.Date hiredate) {
        this.hiredateDisplay = StringUtil.toString(hiredate);
    }

    public String getHiredateDisplay() {
        return hiredateDisplay;
    }

    @DateType
    @Args(keys = "form.employee.hiredate")
    public void setHiredateDisplay(String hiredateDisplay) {
        this.hiredateDisplay = hiredateDisplay;
    }

    public String getSal() {
        return StringUtil.toNumericString(this.sal);
    }

    @IntegerType
    @Args(keys = "form.employee.salary")
    public void setSal(String sal) {
        this.sal = sal;
    }

    public String getComm() {
        return StringUtil.toNumericString(this.comm);
    }

    @IntegerType
    @Args(keys = "form.employee.commission")
    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getDeptno() {
        return StringUtil.toNumericString(this.deptno);
    }

    @Required
    @Args(keys = "form.department")
    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Employee)) {
            return false;
        }
        Employee castOther = (Employee) other;
        return getEmpno() == castOther.getEmpno();
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("[");
        setupToString(buf);
        buf.append("]");
        return buf.toString();
    }

    protected void setupToString(StringBuffer buf) {
        buf.append(empno).append(", ");
        buf.append(ename).append(", ");
        buf.append(job).append(", ");
        buf.append(mgr).append(", ");
        buf.append(hiredateDisplay).append(", ");
        buf.append(sal).append(", ");
        buf.append(comm).append(", ");
        buf.append(deptno).append(", ");
        buf.append(versionNo);
    }

    public int hashCode() {
        if (empno != null) {
            return Integer.parseInt(empno);
        } else {
            return 0;
        }
    }
}