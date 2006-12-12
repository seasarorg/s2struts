select
	emp.empno
	,emp.ename
	,emp.job
	,emp.mgr
	,emp.hiredate
	,emp.sal
	,emp.comm
	,emp.deptno
	,emp.versionNo
	,deptDto.deptno AS deptno_0
	,deptDto.dname AS dname_0
	,deptDto.loc AS loc_0
	,deptDto.versionNo AS versionNo_0
from
	emp
	LEFT OUTER JOIN dept DeptDto ON emp.deptno = deptDto.deptno
/*BEGIN*/
where

	/*IF dto.empno != null*/ emp.Empno = /*dto.empno*/0/*END*/
	/*IF dto.empno_not != null*/AND emp.Empno != /*dto.empno_not*/0/*END*/
	/*IF dto.empno_large != null*/AND  /*dto.empno_large*/0 < emp.Empno/*END*/
	/*IF dto.empno_moreLarge != null*/AND  /*dto.empno_moreLarge*/0 <= emp.Empno/*END*/
	/*IF dto.empno_from != null*/AND  /*dto.empno_from*/0 <= emp.Empno/*END*/
	/*IF dto.empno_to != null*/AND emp.Empno <= /*dto.empno_to*/0/*END*/
	/*IF dto.empno_moreSmall != null*/AND emp.Empno <= /*dto.empno_moreSmall*/0/*END*/
	/*IF dto.empno_small != null*/AND emp.Empno < /*dto.empno_small*/0/*END*/
	/*IF dto.empno_isNull != null*/AND emp.Empno is null /*END*/
	/*IF dto.empno_isNotNull != null*/AND emp.Empno is not null/*END*/
	/*IF dto.empno_in != null*/AND emp.Empno in /*dto.empno_in*/(0)/*END*/
	/*IF dto.ename_matchFull != null*/AND emp.Ename Like /*dto.ename_matchFull*/'%TestData%'/*END*/
	/*IF dto.ename_matchFront != null*/AND emp.Ename Like /*dto.ename_matchFront*/'TestData%'/*END*/
	/*IF dto.ename_matchBack != null*/AND emp.Ename Like /*dto.ename_matchBack*/'%TestData'/*END*/
	/*IF dto.ename != null*/AND emp.Ename = /*dto.ename*/'TestData'/*END*/
	/*IF dto.ename_not != null*/AND emp.Ename != /*dto.ename_not*/'TestData'/*END*/
	/*IF dto.ename_large != null*/AND  /*dto.ename_large*/'TestData' < emp.Ename/*END*/
	/*IF dto.ename_moreLarge != null*/AND  /*dto.ename_moreLarge*/'TestData' <= emp.Ename/*END*/
	/*IF dto.ename_from != null*/AND  /*dto.ename_from*/'TestData' <= emp.Ename/*END*/
	/*IF dto.ename_to != null*/AND emp.Ename <= /*dto.ename_to*/'TestData'/*END*/
	/*IF dto.ename_moreSmall != null*/AND emp.Ename <= /*dto.ename_moreSmall*/'TestData'/*END*/
	/*IF dto.ename_small != null*/AND emp.Ename < /*dto.ename_small*/'TestData'/*END*/
	/*IF dto.ename_isNull != null*/AND emp.Ename is null /*END*/
	/*IF dto.ename_isNotNull != null*/AND emp.Ename is not null/*END*/
	/*IF dto.ename_in != null*/AND emp.Ename in /*dto.ename_in*/('TestData')/*END*/
	/*IF dto.job_matchFull != null*/AND emp.Job Like /*dto.job_matchFull*/'%TestData%'/*END*/
	/*IF dto.job_matchFront != null*/AND emp.Job Like /*dto.job_matchFront*/'TestData%'/*END*/
	/*IF dto.job_matchBack != null*/AND emp.Job Like /*dto.job_matchBack*/'%TestData'/*END*/
	/*IF dto.job != null*/AND emp.Job = /*dto.job*/'TestData'/*END*/
	/*IF dto.job_not != null*/AND emp.Job != /*dto.job_not*/'TestData'/*END*/
	/*IF dto.job_large != null*/AND  /*dto.job_large*/'TestData' < emp.Job/*END*/
	/*IF dto.job_moreLarge != null*/AND  /*dto.job_moreLarge*/'TestData' <= emp.Job/*END*/
	/*IF dto.job_from != null*/AND  /*dto.job_from*/'TestData' <= emp.Job/*END*/
	/*IF dto.job_to != null*/AND emp.Job <= /*dto.job_to*/'TestData'/*END*/
	/*IF dto.job_moreSmall != null*/AND emp.Job <= /*dto.job_moreSmall*/'TestData'/*END*/
	/*IF dto.job_small != null*/AND emp.Job < /*dto.job_small*/'TestData'/*END*/
	/*IF dto.job_isNull != null*/AND emp.Job is null /*END*/
	/*IF dto.job_isNotNull != null*/AND emp.Job is not null/*END*/
	/*IF dto.job_in != null*/AND emp.Job in /*dto.job_in*/('TestData')/*END*/
	/*IF dto.mgr != null*/AND emp.Mgr = /*dto.mgr*/0/*END*/
	/*IF dto.mgr_not != null*/AND emp.Mgr != /*dto.mgr_not*/0/*END*/
	/*IF dto.mgr_large != null*/AND  /*dto.mgr_large*/0 < emp.Mgr/*END*/
	/*IF dto.mgr_moreLarge != null*/AND  /*dto.mgr_moreLarge*/0 <= emp.Mgr/*END*/
	/*IF dto.mgr_from != null*/AND  /*dto.mgr_from*/0 <= emp.Mgr/*END*/
	/*IF dto.mgr_to != null*/AND emp.Mgr <= /*dto.mgr_to*/0/*END*/
	/*IF dto.mgr_moreSmall != null*/AND emp.Mgr <= /*dto.mgr_moreSmall*/0/*END*/
	/*IF dto.mgr_small != null*/AND emp.Mgr < /*dto.mgr_small*/0/*END*/
	/*IF dto.mgr_isNull != null*/AND emp.Mgr is null /*END*/
	/*IF dto.mgr_isNotNull != null*/AND emp.Mgr is not null/*END*/
	/*IF dto.mgr_in != null*/AND emp.Mgr in /*dto.mgr_in*/(0)/*END*/
	/*IF dto.hiredate != null*/AND emp.Hiredate = /*dto.hiredate*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.hiredate_not != null*/AND emp.Hiredate != /*dto.hiredate_not*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.hiredate_large != null*/AND  /*dto.hiredate_large*/'2005-01-01 00:00:00.0000' < emp.Hiredate/*END*/
	/*IF dto.hiredate_moreLarge != null*/AND  /*dto.hiredate_moreLarge*/'2005-01-01 00:00:00.0000' <= emp.Hiredate/*END*/
	/*IF dto.hiredate_from != null*/AND  /*dto.hiredate_from*/'2005-01-01 00:00:00.0000' <= emp.Hiredate/*END*/
	/*IF dto.hiredate_to != null*/AND emp.Hiredate <= /*dto.hiredate_to*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.hiredate_moreSmall != null*/AND emp.Hiredate <= /*dto.hiredate_moreSmall*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.hiredate_small != null*/AND emp.Hiredate < /*dto.hiredate_small*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.hiredate_isNull != null*/AND emp.Hiredate is null /*END*/
	/*IF dto.hiredate_isNotNull != null*/AND emp.Hiredate is not null/*END*/
	/*IF dto.hiredate_in != null*/AND emp.Hiredate in /*dto.hiredate_in*/('2005-01-01 00:00:00.0000')/*END*/
	/*IF dto.sal != null*/AND emp.Sal = /*dto.sal*/0/*END*/
	/*IF dto.sal_not != null*/AND emp.Sal != /*dto.sal_not*/0/*END*/
	/*IF dto.sal_large != null*/AND  /*dto.sal_large*/0 < emp.Sal/*END*/
	/*IF dto.sal_moreLarge != null*/AND  /*dto.sal_moreLarge*/0 <= emp.Sal/*END*/
	/*IF dto.sal_from != null*/AND  /*dto.sal_from*/0 <= emp.Sal/*END*/
	/*IF dto.sal_to != null*/AND emp.Sal <= /*dto.sal_to*/0/*END*/
	/*IF dto.sal_moreSmall != null*/AND emp.Sal <= /*dto.sal_moreSmall*/0/*END*/
	/*IF dto.sal_small != null*/AND emp.Sal < /*dto.sal_small*/0/*END*/
	/*IF dto.sal_isNull != null*/AND emp.Sal is null /*END*/
	/*IF dto.sal_isNotNull != null*/AND emp.Sal is not null/*END*/
	/*IF dto.sal_in != null*/AND emp.Sal in /*dto.sal_in*/(0)/*END*/
	/*IF dto.comm != null*/AND emp.Comm = /*dto.comm*/0/*END*/
	/*IF dto.comm_not != null*/AND emp.Comm != /*dto.comm_not*/0/*END*/
	/*IF dto.comm_large != null*/AND  /*dto.comm_large*/0 < emp.Comm/*END*/
	/*IF dto.comm_moreLarge != null*/AND  /*dto.comm_moreLarge*/0 <= emp.Comm/*END*/
	/*IF dto.comm_from != null*/AND  /*dto.comm_from*/0 <= emp.Comm/*END*/
	/*IF dto.comm_to != null*/AND emp.Comm <= /*dto.comm_to*/0/*END*/
	/*IF dto.comm_moreSmall != null*/AND emp.Comm <= /*dto.comm_moreSmall*/0/*END*/
	/*IF dto.comm_small != null*/AND emp.Comm < /*dto.comm_small*/0/*END*/
	/*IF dto.comm_isNull != null*/AND emp.Comm is null /*END*/
	/*IF dto.comm_isNotNull != null*/AND emp.Comm is not null/*END*/
	/*IF dto.comm_in != null*/AND emp.Comm in /*dto.comm_in*/(0)/*END*/
	/*IF dto.deptno != null*/AND emp.Deptno = /*dto.deptno*/0/*END*/
	/*IF dto.deptno_not != null*/AND emp.Deptno != /*dto.deptno_not*/0/*END*/
	/*IF dto.deptno_large != null*/AND  /*dto.deptno_large*/0 < emp.Deptno/*END*/
	/*IF dto.deptno_moreLarge != null*/AND  /*dto.deptno_moreLarge*/0 <= emp.Deptno/*END*/
	/*IF dto.deptno_from != null*/AND  /*dto.deptno_from*/0 <= emp.Deptno/*END*/
	/*IF dto.deptno_to != null*/AND emp.Deptno <= /*dto.deptno_to*/0/*END*/
	/*IF dto.deptno_moreSmall != null*/AND emp.Deptno <= /*dto.deptno_moreSmall*/0/*END*/
	/*IF dto.deptno_small != null*/AND emp.Deptno < /*dto.deptno_small*/0/*END*/
	/*IF dto.deptno_isNull != null*/AND emp.Deptno is null /*END*/
	/*IF dto.deptno_isNotNull != null*/AND emp.Deptno is not null/*END*/
	/*IF dto.deptno_in != null*/AND emp.Deptno in /*dto.deptno_in*/(0)/*END*/
	/*IF dto.versionno != null*/AND emp.VersionNo = /*dto.versionno*/0/*END*/
	/*IF dto.versionno_not != null*/AND emp.VersionNo != /*dto.versionno_not*/0/*END*/
	/*IF dto.versionno_large != null*/AND  /*dto.versionno_large*/0 < emp.VersionNo/*END*/
	/*IF dto.versionno_moreLarge != null*/AND  /*dto.versionno_moreLarge*/0 <= emp.VersionNo/*END*/
	/*IF dto.versionno_from != null*/AND  /*dto.versionno_from*/0 <= emp.VersionNo/*END*/
	/*IF dto.versionno_to != null*/AND emp.VersionNo <= /*dto.versionno_to*/0/*END*/
	/*IF dto.versionno_moreSmall != null*/AND emp.VersionNo <= /*dto.versionno_moreSmall*/0/*END*/
	/*IF dto.versionno_small != null*/AND emp.VersionNo < /*dto.versionno_small*/0/*END*/
	/*IF dto.versionno_isNull != null*/AND emp.VersionNo is null /*END*/
	/*IF dto.versionno_isNotNull != null*/AND emp.VersionNo is not null/*END*/
	/*IF dto.versionno_in != null*/AND emp.VersionNo in /*dto.versionno_in*/(0)/*END*/

	/*IF keys != null*/AND emp.empno in /*keys*/(0) /*END*/
	
/*END*/
/*$dto.orderList*/
