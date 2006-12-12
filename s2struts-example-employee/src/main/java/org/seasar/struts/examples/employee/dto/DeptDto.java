package org.seasar.struts.examples.employee.dto;


public class DeptDto {
	public static final String TABLE = "dept";

//	public static final String deptno_ID = "sequence, sequenceName=deptno";
	private int deptno;
	private String dname;
	private String loc;
	private Integer versionno;
	
	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Integer getVersionno() {
		return versionno;
	}

	public void setVersionno(Integer versionno) {
		this.versionno = versionno;
	}

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/deptno=").append(deptno);
		buff.append("/dname=").append(dname);
		buff.append("/loc=").append(loc);
		buff.append("/versionno=").append(versionno);
		buff.append("]");
		return buff.toString();
	}
	
}
