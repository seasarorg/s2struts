package org.seasar.struts.examples.employee.dto;

import java.math.BigDecimal;
import java.util.Date;

public class EmpDto {
	public static final String TABLE = "emp";

//	public static final String empno_ID = "sequence, sequenceName=empno";
	private int empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Date hiredate;
	private BigDecimal sal;
	private BigDecimal comm;
	private Integer deptno;
	private Integer versionno;
	
	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
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

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public BigDecimal getSal() {
		return sal;
	}

	public void setSal(BigDecimal sal) {
		this.sal = sal;
	}

	public BigDecimal getComm() {
		return comm;
	}

	public void setComm(BigDecimal comm) {
		this.comm = comm;
	}

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public Integer getVersionno() {
		return versionno;
	}

	public void setVersionno(Integer versionno) {
		this.versionno = versionno;
	}

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/empno=").append(empno);
		buff.append("/ename=").append(ename);
		buff.append("/job=").append(job);
		buff.append("/mgr=").append(mgr);
		buff.append("/hiredate=").append(hiredate);
		buff.append("/sal=").append(sal);
		buff.append("/comm=").append(comm);
		buff.append("/deptno=").append(deptno);
		buff.append("/versionno=").append(versionno);
		buff.append("]");
		return buff.toString();
	}
	
}
