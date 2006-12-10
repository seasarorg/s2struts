package org.seasar.struts.examples.employee.finddto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.seasar.buri.common.util.ScriptProcessor;

public class EmpFindDto {
	public static final String TABLE = "emp";
    private ArrayList orderList = new ArrayList();
	
	private Integer empno = null;
	private Integer empno_not = null;
	private Integer empno_large = null;
	private Integer empno_moreLarge = null;
	private Integer empno_from = null;
	private Integer empno_to = null;
	private Integer empno_moreSmall = null;
	private Integer empno_small = null;
	private List empno_in = null;
	private Boolean empno_isNull = null;
	private Boolean empno_isNotNull = null;
	private boolean empno_isASC = true;
	private String ename = null;
	private String ename_not = null;
	private String ename_large = null;
	private String ename_moreLarge = null;
	private String ename_from = null;
	private String ename_to = null;
	private String ename_moreSmall = null;
	private String ename_small = null;
	private String ename_matchFull = null;
	private String ename_matchFront = null;
	private String ename_matchBack = null;
	private List ename_in = null;
	private Boolean ename_isNull = null;
	private Boolean ename_isNotNull = null;
	private boolean ename_isASC = true;
	private String job = null;
	private String job_not = null;
	private String job_large = null;
	private String job_moreLarge = null;
	private String job_from = null;
	private String job_to = null;
	private String job_moreSmall = null;
	private String job_small = null;
	private String job_matchFull = null;
	private String job_matchFront = null;
	private String job_matchBack = null;
	private List job_in = null;
	private Boolean job_isNull = null;
	private Boolean job_isNotNull = null;
	private boolean job_isASC = true;
	private Integer mgr = null;
	private Integer mgr_not = null;
	private Integer mgr_large = null;
	private Integer mgr_moreLarge = null;
	private Integer mgr_from = null;
	private Integer mgr_to = null;
	private Integer mgr_moreSmall = null;
	private Integer mgr_small = null;
	private List mgr_in = null;
	private Boolean mgr_isNull = null;
	private Boolean mgr_isNotNull = null;
	private boolean mgr_isASC = true;
	private Date hiredate = null;
	private Date hiredate_not = null;
	private Date hiredate_large = null;
	private Date hiredate_moreLarge = null;
	private Date hiredate_from = null;
	private Date hiredate_to = null;
	private Date hiredate_moreSmall = null;
	private Date hiredate_small = null;
	private List hiredate_in = null;
	private Boolean hiredate_isNull = null;
	private Boolean hiredate_isNotNull = null;
	private boolean hiredate_isASC = true;
	private BigDecimal sal = null;
	private BigDecimal sal_not = null;
	private BigDecimal sal_large = null;
	private BigDecimal sal_moreLarge = null;
	private BigDecimal sal_from = null;
	private BigDecimal sal_to = null;
	private BigDecimal sal_moreSmall = null;
	private BigDecimal sal_small = null;
	private List sal_in = null;
	private Boolean sal_isNull = null;
	private Boolean sal_isNotNull = null;
	private boolean sal_isASC = true;
	private BigDecimal comm = null;
	private BigDecimal comm_not = null;
	private BigDecimal comm_large = null;
	private BigDecimal comm_moreLarge = null;
	private BigDecimal comm_from = null;
	private BigDecimal comm_to = null;
	private BigDecimal comm_moreSmall = null;
	private BigDecimal comm_small = null;
	private List comm_in = null;
	private Boolean comm_isNull = null;
	private Boolean comm_isNotNull = null;
	private boolean comm_isASC = true;
	private Integer deptno = null;
	private Integer deptno_not = null;
	private Integer deptno_large = null;
	private Integer deptno_moreLarge = null;
	private Integer deptno_from = null;
	private Integer deptno_to = null;
	private Integer deptno_moreSmall = null;
	private Integer deptno_small = null;
	private List deptno_in = null;
	private Boolean deptno_isNull = null;
	private Boolean deptno_isNotNull = null;
	private boolean deptno_isASC = true;
	private Integer versionno = null;
	private Integer versionno_not = null;
	private Integer versionno_large = null;
	private Integer versionno_moreLarge = null;
	private Integer versionno_from = null;
	private Integer versionno_to = null;
	private Integer versionno_moreSmall = null;
	private Integer versionno_small = null;
	private List versionno_in = null;
	private Boolean versionno_isNull = null;
	private Boolean versionno_isNotNull = null;
	private boolean versionno_isASC = true;

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public Integer getEmpno_not() {
		return empno_not;
	}

	public void setEmpno_not(Integer empno_not) {
		this.empno_not = empno_not;
	}
	public Integer getEmpno_large() {
		return empno_large;
	}

	public void setEmpno_large(Integer empno_large) {
		this.empno_large = empno_large;
	}
	public Integer getEmpno_moreLarge() {
		return empno_moreLarge;
	}

	public void setEmpno_moreLarge(Integer empno_moreLarge) {
		this.empno_moreLarge = empno_moreLarge;
	}
	public Integer getEmpno_from() {
		return empno_from;
	}

	public void setEmpno_from(Integer empno_from) {
		this.empno_from = empno_from;
	}
	public Integer getEmpno_to() {
		return empno_to;
	}

	public void setEmpno_to(Integer empno_to) {
		this.empno_to = empno_to;
	}
	public Integer getEmpno_moreSmall() {
		return empno_moreSmall;
	}

	public void setEmpno_moreSmall(Integer empno_moreSmall) {
		this.empno_moreSmall = empno_moreSmall;
	}
	public Integer getEmpno_small() {
		return empno_small;
	}

	public void setEmpno_small(Integer empno_small) {
		this.empno_small = empno_small;
	}
	public List getEmpno_in() {
		return empno_in;
	}

	public void setEmpno_in(List empno_in) {
		this.empno_in = empno_in;
	}
	public Boolean getEmpno_isNull() {
		return empno_isNull;
	}

	public void setEmpno_isNull(Boolean empno_isNull) {
		this.empno_isNull = empno_isNull;
	}
	public Boolean getEmpno_isNotNull() {
		return empno_isNotNull;
	}

	public void setEmpno_isNotNull(Boolean empno_isNotNull) {
		this.empno_isNotNull = empno_isNotNull;
	}
	public boolean getEmpno_isASC() {
		return empno_isASC;
	}

	public void setEmpno_isASC(boolean empno_isASC) {
		this.empno_isASC = empno_isASC;
	}
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEname_not() {
		return ename_not;
	}

	public void setEname_not(String ename_not) {
		this.ename_not = ename_not;
	}
	public String getEname_large() {
		return ename_large;
	}

	public void setEname_large(String ename_large) {
		this.ename_large = ename_large;
	}
	public String getEname_moreLarge() {
		return ename_moreLarge;
	}

	public void setEname_moreLarge(String ename_moreLarge) {
		this.ename_moreLarge = ename_moreLarge;
	}
	public String getEname_from() {
		return ename_from;
	}

	public void setEname_from(String ename_from) {
		this.ename_from = ename_from;
	}
	public String getEname_to() {
		return ename_to;
	}

	public void setEname_to(String ename_to) {
		this.ename_to = ename_to;
	}
	public String getEname_moreSmall() {
		return ename_moreSmall;
	}

	public void setEname_moreSmall(String ename_moreSmall) {
		this.ename_moreSmall = ename_moreSmall;
	}
	public String getEname_small() {
		return ename_small;
	}

	public void setEname_small(String ename_small) {
		this.ename_small = ename_small;
	}
	public String getEname_matchFull() {
		if(ename_matchFull==null) {
			return null;
		}
		return "%"+ename_matchFull+"%";
	}

	public void setEname_matchFull(String ename_matchFull) {
		this.ename_matchFull = ename_matchFull;
	}
	public String getEname_matchFront() {
		if(ename_matchFront==null) {
			return null;
		}
		return ename_matchFront+"%";
	}

	public void setEname_matchFront(String ename_matchFront) {
		this.ename_matchFront = ename_matchFront;
	}
	public String getEname_matchBack() {
		if(ename_matchBack==null) {
			return null;
		}
		return "%"+ename_matchBack;
	}

	public void setEname_matchBack(String ename_matchBack) {
		this.ename_matchBack = ename_matchBack;
	}
	public List getEname_in() {
		return ename_in;
	}

	public void setEname_in(List ename_in) {
		this.ename_in = ename_in;
	}
	public Boolean getEname_isNull() {
		return ename_isNull;
	}

	public void setEname_isNull(Boolean ename_isNull) {
		this.ename_isNull = ename_isNull;
	}
	public Boolean getEname_isNotNull() {
		return ename_isNotNull;
	}

	public void setEname_isNotNull(Boolean ename_isNotNull) {
		this.ename_isNotNull = ename_isNotNull;
	}
	public boolean getEname_isASC() {
		return ename_isASC;
	}

	public void setEname_isASC(boolean ename_isASC) {
		this.ename_isASC = ename_isASC;
	}
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	public String getJob_not() {
		return job_not;
	}

	public void setJob_not(String job_not) {
		this.job_not = job_not;
	}
	public String getJob_large() {
		return job_large;
	}

	public void setJob_large(String job_large) {
		this.job_large = job_large;
	}
	public String getJob_moreLarge() {
		return job_moreLarge;
	}

	public void setJob_moreLarge(String job_moreLarge) {
		this.job_moreLarge = job_moreLarge;
	}
	public String getJob_from() {
		return job_from;
	}

	public void setJob_from(String job_from) {
		this.job_from = job_from;
	}
	public String getJob_to() {
		return job_to;
	}

	public void setJob_to(String job_to) {
		this.job_to = job_to;
	}
	public String getJob_moreSmall() {
		return job_moreSmall;
	}

	public void setJob_moreSmall(String job_moreSmall) {
		this.job_moreSmall = job_moreSmall;
	}
	public String getJob_small() {
		return job_small;
	}

	public void setJob_small(String job_small) {
		this.job_small = job_small;
	}
	public String getJob_matchFull() {
		if(job_matchFull==null) {
			return null;
		}
		return "%"+job_matchFull+"%";
	}

	public void setJob_matchFull(String job_matchFull) {
		this.job_matchFull = job_matchFull;
	}
	public String getJob_matchFront() {
		if(job_matchFront==null) {
			return null;
		}
		return job_matchFront+"%";
	}

	public void setJob_matchFront(String job_matchFront) {
		this.job_matchFront = job_matchFront;
	}
	public String getJob_matchBack() {
		if(job_matchBack==null) {
			return null;
		}
		return "%"+job_matchBack;
	}

	public void setJob_matchBack(String job_matchBack) {
		this.job_matchBack = job_matchBack;
	}
	public List getJob_in() {
		return job_in;
	}

	public void setJob_in(List job_in) {
		this.job_in = job_in;
	}
	public Boolean getJob_isNull() {
		return job_isNull;
	}

	public void setJob_isNull(Boolean job_isNull) {
		this.job_isNull = job_isNull;
	}
	public Boolean getJob_isNotNull() {
		return job_isNotNull;
	}

	public void setJob_isNotNull(Boolean job_isNotNull) {
		this.job_isNotNull = job_isNotNull;
	}
	public boolean getJob_isASC() {
		return job_isASC;
	}

	public void setJob_isASC(boolean job_isASC) {
		this.job_isASC = job_isASC;
	}
	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}
	public Integer getMgr_not() {
		return mgr_not;
	}

	public void setMgr_not(Integer mgr_not) {
		this.mgr_not = mgr_not;
	}
	public Integer getMgr_large() {
		return mgr_large;
	}

	public void setMgr_large(Integer mgr_large) {
		this.mgr_large = mgr_large;
	}
	public Integer getMgr_moreLarge() {
		return mgr_moreLarge;
	}

	public void setMgr_moreLarge(Integer mgr_moreLarge) {
		this.mgr_moreLarge = mgr_moreLarge;
	}
	public Integer getMgr_from() {
		return mgr_from;
	}

	public void setMgr_from(Integer mgr_from) {
		this.mgr_from = mgr_from;
	}
	public Integer getMgr_to() {
		return mgr_to;
	}

	public void setMgr_to(Integer mgr_to) {
		this.mgr_to = mgr_to;
	}
	public Integer getMgr_moreSmall() {
		return mgr_moreSmall;
	}

	public void setMgr_moreSmall(Integer mgr_moreSmall) {
		this.mgr_moreSmall = mgr_moreSmall;
	}
	public Integer getMgr_small() {
		return mgr_small;
	}

	public void setMgr_small(Integer mgr_small) {
		this.mgr_small = mgr_small;
	}
	public List getMgr_in() {
		return mgr_in;
	}

	public void setMgr_in(List mgr_in) {
		this.mgr_in = mgr_in;
	}
	public Boolean getMgr_isNull() {
		return mgr_isNull;
	}

	public void setMgr_isNull(Boolean mgr_isNull) {
		this.mgr_isNull = mgr_isNull;
	}
	public Boolean getMgr_isNotNull() {
		return mgr_isNotNull;
	}

	public void setMgr_isNotNull(Boolean mgr_isNotNull) {
		this.mgr_isNotNull = mgr_isNotNull;
	}
	public boolean getMgr_isASC() {
		return mgr_isASC;
	}

	public void setMgr_isASC(boolean mgr_isASC) {
		this.mgr_isASC = mgr_isASC;
	}
	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public Date getHiredate_not() {
		return hiredate_not;
	}

	public void setHiredate_not(Date hiredate_not) {
		this.hiredate_not = hiredate_not;
	}
	public Date getHiredate_large() {
		return hiredate_large;
	}

	public void setHiredate_large(Date hiredate_large) {
		this.hiredate_large = hiredate_large;
	}
	public Date getHiredate_moreLarge() {
		return hiredate_moreLarge;
	}

	public void setHiredate_moreLarge(Date hiredate_moreLarge) {
		this.hiredate_moreLarge = hiredate_moreLarge;
	}
	public Date getHiredate_from() {
		return hiredate_from;
	}

	public void setHiredate_from(Date hiredate_from) {
		this.hiredate_from = hiredate_from;
	}
	public Date getHiredate_to() {
		return hiredate_to;
	}

	public void setHiredate_to(Date hiredate_to) {
		this.hiredate_to = hiredate_to;
	}
	public Date getHiredate_moreSmall() {
		return hiredate_moreSmall;
	}

	public void setHiredate_moreSmall(Date hiredate_moreSmall) {
		this.hiredate_moreSmall = hiredate_moreSmall;
	}
	public Date getHiredate_small() {
		return hiredate_small;
	}

	public void setHiredate_small(Date hiredate_small) {
		this.hiredate_small = hiredate_small;
	}
	public List getHiredate_in() {
		return hiredate_in;
	}

	public void setHiredate_in(List hiredate_in) {
		this.hiredate_in = hiredate_in;
	}
	public Boolean getHiredate_isNull() {
		return hiredate_isNull;
	}

	public void setHiredate_isNull(Boolean hiredate_isNull) {
		this.hiredate_isNull = hiredate_isNull;
	}
	public Boolean getHiredate_isNotNull() {
		return hiredate_isNotNull;
	}

	public void setHiredate_isNotNull(Boolean hiredate_isNotNull) {
		this.hiredate_isNotNull = hiredate_isNotNull;
	}
	public boolean getHiredate_isASC() {
		return hiredate_isASC;
	}

	public void setHiredate_isASC(boolean hiredate_isASC) {
		this.hiredate_isASC = hiredate_isASC;
	}
	public BigDecimal getSal() {
		return sal;
	}

	public void setSal(BigDecimal sal) {
		this.sal = sal;
	}
	public BigDecimal getSal_not() {
		return sal_not;
	}

	public void setSal_not(BigDecimal sal_not) {
		this.sal_not = sal_not;
	}
	public BigDecimal getSal_large() {
		return sal_large;
	}

	public void setSal_large(BigDecimal sal_large) {
		this.sal_large = sal_large;
	}
	public BigDecimal getSal_moreLarge() {
		return sal_moreLarge;
	}

	public void setSal_moreLarge(BigDecimal sal_moreLarge) {
		this.sal_moreLarge = sal_moreLarge;
	}
	public BigDecimal getSal_from() {
		return sal_from;
	}

	public void setSal_from(BigDecimal sal_from) {
		this.sal_from = sal_from;
	}
	public BigDecimal getSal_to() {
		return sal_to;
	}

	public void setSal_to(BigDecimal sal_to) {
		this.sal_to = sal_to;
	}
	public BigDecimal getSal_moreSmall() {
		return sal_moreSmall;
	}

	public void setSal_moreSmall(BigDecimal sal_moreSmall) {
		this.sal_moreSmall = sal_moreSmall;
	}
	public BigDecimal getSal_small() {
		return sal_small;
	}

	public void setSal_small(BigDecimal sal_small) {
		this.sal_small = sal_small;
	}
	public List getSal_in() {
		return sal_in;
	}

	public void setSal_in(List sal_in) {
		this.sal_in = sal_in;
	}
	public Boolean getSal_isNull() {
		return sal_isNull;
	}

	public void setSal_isNull(Boolean sal_isNull) {
		this.sal_isNull = sal_isNull;
	}
	public Boolean getSal_isNotNull() {
		return sal_isNotNull;
	}

	public void setSal_isNotNull(Boolean sal_isNotNull) {
		this.sal_isNotNull = sal_isNotNull;
	}
	public boolean getSal_isASC() {
		return sal_isASC;
	}

	public void setSal_isASC(boolean sal_isASC) {
		this.sal_isASC = sal_isASC;
	}
	public BigDecimal getComm() {
		return comm;
	}

	public void setComm(BigDecimal comm) {
		this.comm = comm;
	}
	public BigDecimal getComm_not() {
		return comm_not;
	}

	public void setComm_not(BigDecimal comm_not) {
		this.comm_not = comm_not;
	}
	public BigDecimal getComm_large() {
		return comm_large;
	}

	public void setComm_large(BigDecimal comm_large) {
		this.comm_large = comm_large;
	}
	public BigDecimal getComm_moreLarge() {
		return comm_moreLarge;
	}

	public void setComm_moreLarge(BigDecimal comm_moreLarge) {
		this.comm_moreLarge = comm_moreLarge;
	}
	public BigDecimal getComm_from() {
		return comm_from;
	}

	public void setComm_from(BigDecimal comm_from) {
		this.comm_from = comm_from;
	}
	public BigDecimal getComm_to() {
		return comm_to;
	}

	public void setComm_to(BigDecimal comm_to) {
		this.comm_to = comm_to;
	}
	public BigDecimal getComm_moreSmall() {
		return comm_moreSmall;
	}

	public void setComm_moreSmall(BigDecimal comm_moreSmall) {
		this.comm_moreSmall = comm_moreSmall;
	}
	public BigDecimal getComm_small() {
		return comm_small;
	}

	public void setComm_small(BigDecimal comm_small) {
		this.comm_small = comm_small;
	}
	public List getComm_in() {
		return comm_in;
	}

	public void setComm_in(List comm_in) {
		this.comm_in = comm_in;
	}
	public Boolean getComm_isNull() {
		return comm_isNull;
	}

	public void setComm_isNull(Boolean comm_isNull) {
		this.comm_isNull = comm_isNull;
	}
	public Boolean getComm_isNotNull() {
		return comm_isNotNull;
	}

	public void setComm_isNotNull(Boolean comm_isNotNull) {
		this.comm_isNotNull = comm_isNotNull;
	}
	public boolean getComm_isASC() {
		return comm_isASC;
	}

	public void setComm_isASC(boolean comm_isASC) {
		this.comm_isASC = comm_isASC;
	}
	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	public Integer getDeptno_not() {
		return deptno_not;
	}

	public void setDeptno_not(Integer deptno_not) {
		this.deptno_not = deptno_not;
	}
	public Integer getDeptno_large() {
		return deptno_large;
	}

	public void setDeptno_large(Integer deptno_large) {
		this.deptno_large = deptno_large;
	}
	public Integer getDeptno_moreLarge() {
		return deptno_moreLarge;
	}

	public void setDeptno_moreLarge(Integer deptno_moreLarge) {
		this.deptno_moreLarge = deptno_moreLarge;
	}
	public Integer getDeptno_from() {
		return deptno_from;
	}

	public void setDeptno_from(Integer deptno_from) {
		this.deptno_from = deptno_from;
	}
	public Integer getDeptno_to() {
		return deptno_to;
	}

	public void setDeptno_to(Integer deptno_to) {
		this.deptno_to = deptno_to;
	}
	public Integer getDeptno_moreSmall() {
		return deptno_moreSmall;
	}

	public void setDeptno_moreSmall(Integer deptno_moreSmall) {
		this.deptno_moreSmall = deptno_moreSmall;
	}
	public Integer getDeptno_small() {
		return deptno_small;
	}

	public void setDeptno_small(Integer deptno_small) {
		this.deptno_small = deptno_small;
	}
	public List getDeptno_in() {
		return deptno_in;
	}

	public void setDeptno_in(List deptno_in) {
		this.deptno_in = deptno_in;
	}
	public Boolean getDeptno_isNull() {
		return deptno_isNull;
	}

	public void setDeptno_isNull(Boolean deptno_isNull) {
		this.deptno_isNull = deptno_isNull;
	}
	public Boolean getDeptno_isNotNull() {
		return deptno_isNotNull;
	}

	public void setDeptno_isNotNull(Boolean deptno_isNotNull) {
		this.deptno_isNotNull = deptno_isNotNull;
	}
	public boolean getDeptno_isASC() {
		return deptno_isASC;
	}

	public void setDeptno_isASC(boolean deptno_isASC) {
		this.deptno_isASC = deptno_isASC;
	}
	public Integer getVersionno() {
		return versionno;
	}

	public void setVersionno(Integer versionno) {
		this.versionno = versionno;
	}
	public Integer getVersionno_not() {
		return versionno_not;
	}

	public void setVersionno_not(Integer versionno_not) {
		this.versionno_not = versionno_not;
	}
	public Integer getVersionno_large() {
		return versionno_large;
	}

	public void setVersionno_large(Integer versionno_large) {
		this.versionno_large = versionno_large;
	}
	public Integer getVersionno_moreLarge() {
		return versionno_moreLarge;
	}

	public void setVersionno_moreLarge(Integer versionno_moreLarge) {
		this.versionno_moreLarge = versionno_moreLarge;
	}
	public Integer getVersionno_from() {
		return versionno_from;
	}

	public void setVersionno_from(Integer versionno_from) {
		this.versionno_from = versionno_from;
	}
	public Integer getVersionno_to() {
		return versionno_to;
	}

	public void setVersionno_to(Integer versionno_to) {
		this.versionno_to = versionno_to;
	}
	public Integer getVersionno_moreSmall() {
		return versionno_moreSmall;
	}

	public void setVersionno_moreSmall(Integer versionno_moreSmall) {
		this.versionno_moreSmall = versionno_moreSmall;
	}
	public Integer getVersionno_small() {
		return versionno_small;
	}

	public void setVersionno_small(Integer versionno_small) {
		this.versionno_small = versionno_small;
	}
	public List getVersionno_in() {
		return versionno_in;
	}

	public void setVersionno_in(List versionno_in) {
		this.versionno_in = versionno_in;
	}
	public Boolean getVersionno_isNull() {
		return versionno_isNull;
	}

	public void setVersionno_isNull(Boolean versionno_isNull) {
		this.versionno_isNull = versionno_isNull;
	}
	public Boolean getVersionno_isNotNull() {
		return versionno_isNotNull;
	}

	public void setVersionno_isNotNull(Boolean versionno_isNotNull) {
		this.versionno_isNotNull = versionno_isNotNull;
	}
	public boolean getVersionno_isASC() {
		return versionno_isASC;
	}

	public void setVersionno_isASC(boolean versionno_isASC) {
		this.versionno_isASC = versionno_isASC;
	}


    public void addOrderList(String order) {
        orderList.add(order);
    }

    public void addOrderList(String order,boolean isAsc) {
        orderList.add(order);
        ScriptProcessor processor = new ScriptProcessor();
        processor.setValue(order.replace('.','_') + "_isASC",this,new Boolean(isAsc));
    }
    
    public String getOrderList() {
        String order = "";
        String ORDER = "ORDER BY ";
        Iterator ite = orderList.iterator();
        ScriptProcessor processor = new ScriptProcessor();
        while(ite.hasNext()) {
            String orderTgt = (String)ite.next();
            order = ORDER + order + orderTgt.replace('_','.') + " ";
            Boolean var = (Boolean)processor.getValue(orderTgt + "_isASC", this);
            if( ! var.booleanValue()) {
                order = order + "DESC ";
            }
            ORDER = "";
        }
        return order;
    }

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/empno=").append(empno);
		buff.append("/empno_not=").append(empno_not);
		buff.append("/empno_large=").append(empno_large);
		buff.append("/empno_moreLarge=").append(empno_moreLarge);
		buff.append("/empno_from=").append(empno_from);
		buff.append("/empno_to=").append(empno_to);
		buff.append("/empno_moreSmall=").append(empno_moreSmall);
		buff.append("/empno_small=").append(empno_small);
		buff.append("/empno_in=").append(empno_in);
		buff.append("/empno_isNull=").append(empno_isNull);
		buff.append("/empno_isNotNull=").append(empno_isNotNull);
		buff.append("/empno_isASC=").append(empno_isASC);
		buff.append("/ename=").append(ename);
		buff.append("/ename_not=").append(ename_not);
		buff.append("/ename_large=").append(ename_large);
		buff.append("/ename_moreLarge=").append(ename_moreLarge);
		buff.append("/ename_from=").append(ename_from);
		buff.append("/ename_to=").append(ename_to);
		buff.append("/ename_moreSmall=").append(ename_moreSmall);
		buff.append("/ename_small=").append(ename_small);
		buff.append("/ename_in=").append(ename_in);
		buff.append("/ename_isNull=").append(ename_isNull);
		buff.append("/ename_isNotNull=").append(ename_isNotNull);
		buff.append("/ename_isASC=").append(ename_isASC);
		buff.append("/job=").append(job);
		buff.append("/job_not=").append(job_not);
		buff.append("/job_large=").append(job_large);
		buff.append("/job_moreLarge=").append(job_moreLarge);
		buff.append("/job_from=").append(job_from);
		buff.append("/job_to=").append(job_to);
		buff.append("/job_moreSmall=").append(job_moreSmall);
		buff.append("/job_small=").append(job_small);
		buff.append("/job_in=").append(job_in);
		buff.append("/job_isNull=").append(job_isNull);
		buff.append("/job_isNotNull=").append(job_isNotNull);
		buff.append("/job_isASC=").append(job_isASC);
		buff.append("/mgr=").append(mgr);
		buff.append("/mgr_not=").append(mgr_not);
		buff.append("/mgr_large=").append(mgr_large);
		buff.append("/mgr_moreLarge=").append(mgr_moreLarge);
		buff.append("/mgr_from=").append(mgr_from);
		buff.append("/mgr_to=").append(mgr_to);
		buff.append("/mgr_moreSmall=").append(mgr_moreSmall);
		buff.append("/mgr_small=").append(mgr_small);
		buff.append("/mgr_in=").append(mgr_in);
		buff.append("/mgr_isNull=").append(mgr_isNull);
		buff.append("/mgr_isNotNull=").append(mgr_isNotNull);
		buff.append("/mgr_isASC=").append(mgr_isASC);
		buff.append("/hiredate=").append(hiredate);
		buff.append("/hiredate_not=").append(hiredate_not);
		buff.append("/hiredate_large=").append(hiredate_large);
		buff.append("/hiredate_moreLarge=").append(hiredate_moreLarge);
		buff.append("/hiredate_from=").append(hiredate_from);
		buff.append("/hiredate_to=").append(hiredate_to);
		buff.append("/hiredate_moreSmall=").append(hiredate_moreSmall);
		buff.append("/hiredate_small=").append(hiredate_small);
		buff.append("/hiredate_in=").append(hiredate_in);
		buff.append("/hiredate_isNull=").append(hiredate_isNull);
		buff.append("/hiredate_isNotNull=").append(hiredate_isNotNull);
		buff.append("/hiredate_isASC=").append(hiredate_isASC);
		buff.append("/sal=").append(sal);
		buff.append("/sal_not=").append(sal_not);
		buff.append("/sal_large=").append(sal_large);
		buff.append("/sal_moreLarge=").append(sal_moreLarge);
		buff.append("/sal_from=").append(sal_from);
		buff.append("/sal_to=").append(sal_to);
		buff.append("/sal_moreSmall=").append(sal_moreSmall);
		buff.append("/sal_small=").append(sal_small);
		buff.append("/sal_in=").append(sal_in);
		buff.append("/sal_isNull=").append(sal_isNull);
		buff.append("/sal_isNotNull=").append(sal_isNotNull);
		buff.append("/sal_isASC=").append(sal_isASC);
		buff.append("/comm=").append(comm);
		buff.append("/comm_not=").append(comm_not);
		buff.append("/comm_large=").append(comm_large);
		buff.append("/comm_moreLarge=").append(comm_moreLarge);
		buff.append("/comm_from=").append(comm_from);
		buff.append("/comm_to=").append(comm_to);
		buff.append("/comm_moreSmall=").append(comm_moreSmall);
		buff.append("/comm_small=").append(comm_small);
		buff.append("/comm_in=").append(comm_in);
		buff.append("/comm_isNull=").append(comm_isNull);
		buff.append("/comm_isNotNull=").append(comm_isNotNull);
		buff.append("/comm_isASC=").append(comm_isASC);
		buff.append("/deptno=").append(deptno);
		buff.append("/deptno_not=").append(deptno_not);
		buff.append("/deptno_large=").append(deptno_large);
		buff.append("/deptno_moreLarge=").append(deptno_moreLarge);
		buff.append("/deptno_from=").append(deptno_from);
		buff.append("/deptno_to=").append(deptno_to);
		buff.append("/deptno_moreSmall=").append(deptno_moreSmall);
		buff.append("/deptno_small=").append(deptno_small);
		buff.append("/deptno_in=").append(deptno_in);
		buff.append("/deptno_isNull=").append(deptno_isNull);
		buff.append("/deptno_isNotNull=").append(deptno_isNotNull);
		buff.append("/deptno_isASC=").append(deptno_isASC);
		buff.append("/versionno=").append(versionno);
		buff.append("/versionno_not=").append(versionno_not);
		buff.append("/versionno_large=").append(versionno_large);
		buff.append("/versionno_moreLarge=").append(versionno_moreLarge);
		buff.append("/versionno_from=").append(versionno_from);
		buff.append("/versionno_to=").append(versionno_to);
		buff.append("/versionno_moreSmall=").append(versionno_moreSmall);
		buff.append("/versionno_small=").append(versionno_small);
		buff.append("/versionno_in=").append(versionno_in);
		buff.append("/versionno_isNull=").append(versionno_isNull);
		buff.append("/versionno_isNotNull=").append(versionno_isNotNull);
		buff.append("/versionno_isASC=").append(versionno_isASC);
		buff.append("]");
		return buff.toString();
	}
	
}
