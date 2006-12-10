package org.seasar.struts.examples.employee.web.manage;

import java.io.Serializable;

public class EmployeeForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String empno = "";

    private String ename = "";

    private String job = "";

    private String mgr = "";

    private String hiredate = "";

    private String sal = "";

    private String comm = "";

    private String deptno = "";

    private String dname = "";

    private int versionNo;

    public EmployeeForm() {
    }

    public String getEmpno() {
        return this.empno;
    }

    public static final String empno_VALIDATOR = "required";

    public static final String empno_VALIDATOR_0 = "integer";

    public static final String empno_VALIDATOR_ARGS = "form.employee.no";

    public static final int empno_VALIDATOR_ORDER = 1;

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getEname() {
        return this.ename;
    }

    public static final String ename_VALIDATOR = "required";

    public static final String ename_VALIDATOR_ARGS = "form.employee.name";

    public static final int ename_VALIDATOR_ORDER = 2;

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMgr() {
        return this.mgr;
    }

    public static final String mgr_VALIDATOR_0 = "integer";

    public static final String mgr_VALIDATOR_ARGS = "form.employee.manager";

    public static final int mgr_VALIDATOR_ORDER = 3;

    public void setMgr(String mgr) {
        this.mgr = mgr;
    }

    public String getHiredate() {
        return this.hiredate;
    }

    public static final String hiredate_VALIDATOR_0 = "date";

    public static final String hiredate_VALIDATOR_ARGS = "form.employee.hiredate";

    public static final int hiredate_VALIDATOR_ORDER = 4;

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getSal() {
        return this.sal;
    }

    public static final String sal_VALIDATOR_0 = "integer";

    public static final String sal_VALIDATOR_ARGS = "form.employee.salary";

    public static final int sal_VALIDATOR_ORDER = 5;

    public void setSal(String sal) {
        this.sal = sal;
    }

    public String getComm() {
        return this.comm;
    }

    public static final String comm_VALIDATOR_0 = "integer";

    public static final String comm_VALIDATOR_ARGS = "form.employee.commission";

    public static final int comm_VALIDATOR_ORDER = 6;

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getDeptno() {
        return this.deptno;
    }

    public static final String deptno_VALIDATOR = "required";

    public static final String deptno_VALIDATOR_0 = "integer";

    public static final String deptno_VALIDATOR_ARGS = "form.department";

    public static final int deptno_VALIDATOR_ORDER = 7;

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return this.dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public int getVersionNo() {
        return this.versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("[");
        buf.append(this.empno).append(", ");
        buf.append(this.ename).append(", ");
        buf.append(this.job).append(", ");
        buf.append(this.mgr).append(", ");
        buf.append(this.hiredate).append(", ");
        buf.append(this.sal).append(", ");
        buf.append(this.comm).append(", ");
        buf.append(this.deptno).append(", ");
        buf.append(this.dname).append(", ");
        buf.append(this.versionNo);
        buf.append("]");
        return buf.toString();
    }

}
