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
package org.seasar.struts.examples.employee.web.manage;

import java.io.Serializable;

/**
 * @author Katsuhiko Nagashima
 */
public class EmployeeSearchForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String empno = "";

    private String ename = "";

    private String job = "";

    private String mgr = "";

    private String hiredate_from = "";

    private String hiredate_to = "";

    private String sal_from = "";

    private String sal_to = "";

    private String deptno = "";

    public EmployeeSearchForm() {
    }

    public String getEmpno() {
        return this.empno;
    }

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

    public static final String mgr_VALIDATOR = "integer";

    public static final String mgr_VALIDATOR_ARGS = "form.employee.manager";

    public void setMgr(String mgr) {
        this.mgr = mgr;
    }

    public String getHiredate_from() {
        return this.hiredate_from;
    }

    public void setHiredate(String hiredate) {
    }

    public String getHiredate() {
        return null;
    }

    public static final String hiredate_from_VALIDATOR = "date";

    public static final String hiredate_from_VALIDATOR_ARGS = "form.employee.hiredate";

    public void setHiredate_from(String hiredate_from) {
        this.hiredate_from = hiredate_from;
    }

    public String getHiredate_to() {
        return this.hiredate_to;
    }

    public static final String hiredate_to_VALIDATOR = "date";

    public static final String hiredate_to_VALIDATOR_ARGS = "form.employee.hiredate";

    public void setHiredate_to(String hiredate_to) {
        this.hiredate_to = hiredate_to;
    }

    public String getSal_from() {
        return this.sal_from;
    }

    public static final String sal_from_VALIDATOR = "integer";

    public static final String sal_from_VALIDATOR_ARGS = "form.employee.salary";

    public void setSal_from(String sal_from) {
        this.sal_from = sal_from;
    }

    public String getSal_to() {
        return this.sal_to;
    }

    public static final String sal_to_VALIDATOR = "integer";

    public static final String sal_to_VALIDATOR_ARGS = "form.employee.salary";

    public void setSal_to(String sal_to) {
        this.sal_to = sal_to;
    }

    public String getDeptno() {
        return this.deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("[");
        buf.append(this.empno).append(", ");
        buf.append(this.ename).append(", ");
        buf.append(this.job).append(", ");
        buf.append(this.mgr).append(", ");
        buf.append(this.hiredate_from).append(", ");
        buf.append(this.hiredate_to).append(", ");
        buf.append(this.sal_from).append(", ");
        buf.append(this.sal_to).append(", ");
        buf.append(this.deptno);
        buf.append("]");
        return buf.toString();
    }

}