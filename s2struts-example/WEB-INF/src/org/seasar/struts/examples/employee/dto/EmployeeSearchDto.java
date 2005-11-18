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
package org.seasar.struts.examples.employee.dto;

import java.io.Serializable;

import org.seasar.struts.annotation.tiger.StrutsActionForm;
import org.seasar.struts.examples.util.DateUtil;
import org.seasar.struts.examples.util.StringUtil;
import org.seasar.struts.validator.annotation.tiger.Args;
import org.seasar.struts.validator.annotation.tiger.DateType;
import org.seasar.struts.validator.annotation.tiger.IntegerType;
import org.seasar.struts.validator.annotation.tiger.NoValidate;

/**
 * @author Katsuhiko Nagashima
 */
@StrutsActionForm(name = "employeeSearchForm")
public class EmployeeSearchDto implements Serializable {

    private String empno = "";

    private String ename = "";

    private String job = "";

    private String mgr = "";

    private String fromHiredateDisplay = "";

    private String toHiredateDisplay = "";

    private String fromSal = "";

    private String toSal = "";

    private String deptno = "";

    public EmployeeSearchDto() {
    }

    public String getEmpno() {
        return this.empno;
    }

    @IntegerType
    @Args(keys = "form.employee.no")
    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public java.lang.String getEname() {
        return this.ename;
    }

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
        return this.mgr;
    }

    @IntegerType
    @Args(keys = "form.employee.manager")
    public void setMgr(String mgr) {
        this.mgr = mgr;
    }

    public java.util.Date getFromHiredate() {
        return DateUtil.toDate(fromHiredateDisplay);
    }

    @NoValidate
    public void setFromHiredate(java.util.Date fromHiredate) {
        this.fromHiredateDisplay = StringUtil.toString(fromHiredate);
    }

    public String getFromHiredateDisplay() {
        return fromHiredateDisplay;
    }

    @DateType
    @Args(keys = "form.employee.hiredate")
    public void setFromHiredateDisplay(String formHiredateDisplay) {
        this.fromHiredateDisplay = formHiredateDisplay;
    }

    public java.util.Date getToHiredate() {
        return DateUtil.toDate(toHiredateDisplay);
    }

    @NoValidate
    public void setToHiredate(java.util.Date toHiredate) {
        this.toHiredateDisplay = StringUtil.toString(toHiredate);
    }

    public String getToHiredateDisplay() {
        return toHiredateDisplay;
    }

    @DateType
    @Args(keys = "form.employee.hiredate")
    public void setToHiredateDisplay(String toHiredateDisplay) {
        this.toHiredateDisplay = toHiredateDisplay;
    }

    public String getFromSal() {
        return this.fromSal;
    }

    @IntegerType
    @Args(keys = "form.employee.salary")
    public void setFromSal(String fromSal) {
        this.fromSal = fromSal;
    }

    public String getToSal() {
        return this.toSal;
    }

    @IntegerType
    @Args(keys = "form.employee.salary")
    public void setToSal(String toSal) {
        this.toSal = toSal;
    }

    public String getDeptno() {
        return this.deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("[");
        buf.append(empno).append(", ");
        buf.append(ename).append(", ");
        buf.append(job).append(", ");
        buf.append(mgr).append(", ");
        buf.append(fromHiredateDisplay).append(", ");
        buf.append(toHiredateDisplay).append(", ");
        buf.append(fromSal).append(", ");
        buf.append(toSal).append(", ");
        buf.append(deptno).append("]");
        return buf.toString();
    }
}