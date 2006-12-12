select
	*
from
	emp
/*BEGIN*/
where
	
	/*IF dto.empno != null*/ empno = /*dto.empno*/0/*END*/
	/*IF dto.empno_not != null*/AND empno != /*dto.empno_not*/0/*END*/
	/*IF dto.empno_large != null*/AND  /*dto.empno_large*/0 < empno/*END*/
	/*IF dto.empno_moreLarge != null*/AND  /*dto.empno_moreLarge*/0 <= empno/*END*/
	/*IF dto.empno_from != null*/AND  /*dto.empno_from*/0 <= empno/*END*/
	/*IF dto.empno_to != null*/AND empno <= /*dto.empno_to*/0/*END*/
	/*IF dto.empno_moreSmall != null*/AND empno <= /*dto.empno_moreSmall*/0/*END*/
	/*IF dto.empno_small != null*/AND empno < /*dto.empno_small*/0/*END*/
	/*IF dto.empno_isNull != null*/AND empno is null /*END*/
	/*IF dto.empno_isNotNull != null*/AND empno is not null/*END*/
	/*IF dto.empno_in != null*/AND empno in /*dto.empno_in*/(0)/*END*/

	
	/*IF dto.ename_matchFull != null*/AND ename Like /*dto.ename_matchFull*/'%TestData%'/*END*/
	/*IF dto.ename_matchFront != null*/AND ename Like /*dto.ename_matchFront*/'TestData%'/*END*/
	/*IF dto.ename_matchBack != null*/AND ename Like /*dto.ename_matchBack*/'%TestData'/*END*/
	/*IF dto.ename != null*/AND ename = /*dto.ename*/'TestData'/*END*/
	/*IF dto.ename_not != null*/AND ename != /*dto.ename_not*/'TestData'/*END*/
	/*IF dto.ename_large != null*/AND  /*dto.ename_large*/'TestData' < ename/*END*/
	/*IF dto.ename_moreLarge != null*/AND  /*dto.ename_moreLarge*/'TestData' <= ename/*END*/
	/*IF dto.ename_from != null*/AND  /*dto.ename_from*/'TestData' <= ename/*END*/
	/*IF dto.ename_to != null*/AND ename <= /*dto.ename_to*/'TestData'/*END*/
	/*IF dto.ename_moreSmall != null*/AND ename <= /*dto.ename_moreSmall*/'TestData'/*END*/
	/*IF dto.ename_small != null*/AND ename < /*dto.ename_small*/'TestData'/*END*/
	/*IF dto.ename_isNull != null*/AND ename is null /*END*/
	/*IF dto.ename_isNotNull != null*/AND ename is not null/*END*/
	/*IF dto.ename_in != null*/AND ename in /*dto.ename_in*/('TestData')/*END*/

	
	/*IF dto.job_matchFull != null*/AND job Like /*dto.job_matchFull*/'%TestData%'/*END*/
	/*IF dto.job_matchFront != null*/AND job Like /*dto.job_matchFront*/'TestData%'/*END*/
	/*IF dto.job_matchBack != null*/AND job Like /*dto.job_matchBack*/'%TestData'/*END*/
	/*IF dto.job != null*/AND job = /*dto.job*/'TestData'/*END*/
	/*IF dto.job_not != null*/AND job != /*dto.job_not*/'TestData'/*END*/
	/*IF dto.job_large != null*/AND  /*dto.job_large*/'TestData' < job/*END*/
	/*IF dto.job_moreLarge != null*/AND  /*dto.job_moreLarge*/'TestData' <= job/*END*/
	/*IF dto.job_from != null*/AND  /*dto.job_from*/'TestData' <= job/*END*/
	/*IF dto.job_to != null*/AND job <= /*dto.job_to*/'TestData'/*END*/
	/*IF dto.job_moreSmall != null*/AND job <= /*dto.job_moreSmall*/'TestData'/*END*/
	/*IF dto.job_small != null*/AND job < /*dto.job_small*/'TestData'/*END*/
	/*IF dto.job_isNull != null*/AND job is null /*END*/
	/*IF dto.job_isNotNull != null*/AND job is not null/*END*/
	/*IF dto.job_in != null*/AND job in /*dto.job_in*/('TestData')/*END*/

	
	/*IF dto.mgr != null*/AND mgr = /*dto.mgr*/0/*END*/
	/*IF dto.mgr_not != null*/AND mgr != /*dto.mgr_not*/0/*END*/
	/*IF dto.mgr_large != null*/AND  /*dto.mgr_large*/0 < mgr/*END*/
	/*IF dto.mgr_moreLarge != null*/AND  /*dto.mgr_moreLarge*/0 <= mgr/*END*/
	/*IF dto.mgr_from != null*/AND  /*dto.mgr_from*/0 <= mgr/*END*/
	/*IF dto.mgr_to != null*/AND mgr <= /*dto.mgr_to*/0/*END*/
	/*IF dto.mgr_moreSmall != null*/AND mgr <= /*dto.mgr_moreSmall*/0/*END*/
	/*IF dto.mgr_small != null*/AND mgr < /*dto.mgr_small*/0/*END*/
	/*IF dto.mgr_isNull != null*/AND mgr is null /*END*/
	/*IF dto.mgr_isNotNull != null*/AND mgr is not null/*END*/
	/*IF dto.mgr_in != null*/AND mgr in /*dto.mgr_in*/(0)/*END*/

	
	/*IF dto.hiredate != null*/AND hiredate = /*dto.hiredate*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.hiredate_not != null*/AND hiredate != /*dto.hiredate_not*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.hiredate_large != null*/AND  /*dto.hiredate_large*/'2005-01-01 00:00:00.0000' < hiredate/*END*/
	/*IF dto.hiredate_moreLarge != null*/AND  /*dto.hiredate_moreLarge*/'2005-01-01 00:00:00.0000' <= hiredate/*END*/
	/*IF dto.hiredate_from != null*/AND  /*dto.hiredate_from*/'2005-01-01 00:00:00.0000' <= hiredate/*END*/
	/*IF dto.hiredate_to != null*/AND hiredate <= /*dto.hiredate_to*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.hiredate_moreSmall != null*/AND hiredate <= /*dto.hiredate_moreSmall*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.hiredate_small != null*/AND hiredate < /*dto.hiredate_small*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.hiredate_isNull != null*/AND hiredate is null /*END*/
	/*IF dto.hiredate_isNotNull != null*/AND hiredate is not null/*END*/
	/*IF dto.hiredate_in != null*/AND hiredate in /*dto.hiredate_in*/('2005-01-01 00:00:00.0000')/*END*/

	
	/*IF dto.sal != null*/AND sal = /*dto.sal*/0/*END*/
	/*IF dto.sal_not != null*/AND sal != /*dto.sal_not*/0/*END*/
	/*IF dto.sal_large != null*/AND  /*dto.sal_large*/0 < sal/*END*/
	/*IF dto.sal_moreLarge != null*/AND  /*dto.sal_moreLarge*/0 <= sal/*END*/
	/*IF dto.sal_from != null*/AND  /*dto.sal_from*/0 <= sal/*END*/
	/*IF dto.sal_to != null*/AND sal <= /*dto.sal_to*/0/*END*/
	/*IF dto.sal_moreSmall != null*/AND sal <= /*dto.sal_moreSmall*/0/*END*/
	/*IF dto.sal_small != null*/AND sal < /*dto.sal_small*/0/*END*/
	/*IF dto.sal_isNull != null*/AND sal is null /*END*/
	/*IF dto.sal_isNotNull != null*/AND sal is not null/*END*/
	/*IF dto.sal_in != null*/AND sal in /*dto.sal_in*/(0)/*END*/

	
	/*IF dto.comm != null*/AND comm = /*dto.comm*/0/*END*/
	/*IF dto.comm_not != null*/AND comm != /*dto.comm_not*/0/*END*/
	/*IF dto.comm_large != null*/AND  /*dto.comm_large*/0 < comm/*END*/
	/*IF dto.comm_moreLarge != null*/AND  /*dto.comm_moreLarge*/0 <= comm/*END*/
	/*IF dto.comm_from != null*/AND  /*dto.comm_from*/0 <= comm/*END*/
	/*IF dto.comm_to != null*/AND comm <= /*dto.comm_to*/0/*END*/
	/*IF dto.comm_moreSmall != null*/AND comm <= /*dto.comm_moreSmall*/0/*END*/
	/*IF dto.comm_small != null*/AND comm < /*dto.comm_small*/0/*END*/
	/*IF dto.comm_isNull != null*/AND comm is null /*END*/
	/*IF dto.comm_isNotNull != null*/AND comm is not null/*END*/
	/*IF dto.comm_in != null*/AND comm in /*dto.comm_in*/(0)/*END*/

	
	/*IF dto.deptno != null*/AND deptno = /*dto.deptno*/0/*END*/
	/*IF dto.deptno_not != null*/AND deptno != /*dto.deptno_not*/0/*END*/
	/*IF dto.deptno_large != null*/AND  /*dto.deptno_large*/0 < deptno/*END*/
	/*IF dto.deptno_moreLarge != null*/AND  /*dto.deptno_moreLarge*/0 <= deptno/*END*/
	/*IF dto.deptno_from != null*/AND  /*dto.deptno_from*/0 <= deptno/*END*/
	/*IF dto.deptno_to != null*/AND deptno <= /*dto.deptno_to*/0/*END*/
	/*IF dto.deptno_moreSmall != null*/AND deptno <= /*dto.deptno_moreSmall*/0/*END*/
	/*IF dto.deptno_small != null*/AND deptno < /*dto.deptno_small*/0/*END*/
	/*IF dto.deptno_isNull != null*/AND deptno is null /*END*/
	/*IF dto.deptno_isNotNull != null*/AND deptno is not null/*END*/
	/*IF dto.deptno_in != null*/AND deptno in /*dto.deptno_in*/(0)/*END*/

	
	/*IF dto.versionno != null*/AND versionNo = /*dto.versionno*/0/*END*/
	/*IF dto.versionno_not != null*/AND versionNo != /*dto.versionno_not*/0/*END*/
	/*IF dto.versionno_large != null*/AND  /*dto.versionNo_large*/0 < versionno/*END*/
	/*IF dto.versionno_moreLarge != null*/AND  /*dto.versionNo_moreLarge*/0 <= versionno/*END*/
	/*IF dto.versionno_from != null*/AND  /*dto.versionNo_from*/0 <= versionno/*END*/
	/*IF dto.versionno_to != null*/AND versionNo <= /*dto.versionno_to*/0/*END*/
	/*IF dto.versionno_moreSmall != null*/AND versionNo <= /*dto.versionno_moreSmall*/0/*END*/
	/*IF dto.versionno_small != null*/AND versionNo < /*dto.versionno_small*/0/*END*/
	/*IF dto.versionno_isNull != null*/AND versionNo is null /*END*/
	/*IF dto.versionno_isNotNull != null*/AND versionNo is not null/*END*/
	/*IF dto.versionno_in != null*/AND versionNo in /*dto.versionno_in*/(0)/*END*/

	/*IF keys != null*/AND empno in /*keys*/(0) /*END*/
	
/*END*/
/*$dto.orderList*/
