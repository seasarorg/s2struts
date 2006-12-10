select
	*
from
	dept
/*BEGIN*/
where
	
	/*IF dto.deptno != null*/ deptno = /*dto.deptno*/0/*END*/
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

	
	/*IF dto.dname_matchFull != null*/AND dname Like /*dto.dname_matchFull*/'%TestData%'/*END*/
	/*IF dto.dname_matchFront != null*/AND dname Like /*dto.dname_matchFront*/'TestData%'/*END*/
	/*IF dto.dname_matchBack != null*/AND dname Like /*dto.dname_matchBack*/'%TestData'/*END*/
	/*IF dto.dname != null*/AND dname = /*dto.dname*/'TestData'/*END*/
	/*IF dto.dname_not != null*/AND dname != /*dto.dname_not*/'TestData'/*END*/
	/*IF dto.dname_large != null*/AND  /*dto.dname_large*/'TestData' < dname/*END*/
	/*IF dto.dname_moreLarge != null*/AND  /*dto.dname_moreLarge*/'TestData' <= dname/*END*/
	/*IF dto.dname_from != null*/AND  /*dto.dname_from*/'TestData' <= dname/*END*/
	/*IF dto.dname_to != null*/AND dname <= /*dto.dname_to*/'TestData'/*END*/
	/*IF dto.dname_moreSmall != null*/AND dname <= /*dto.dname_moreSmall*/'TestData'/*END*/
	/*IF dto.dname_small != null*/AND dname < /*dto.dname_small*/'TestData'/*END*/
	/*IF dto.dname_isNull != null*/AND dname is null /*END*/
	/*IF dto.dname_isNotNull != null*/AND dname is not null/*END*/
	/*IF dto.dname_in != null*/AND dname in /*dto.dname_in*/('TestData')/*END*/

	
	/*IF dto.loc_matchFull != null*/AND loc Like /*dto.loc_matchFull*/'%TestData%'/*END*/
	/*IF dto.loc_matchFront != null*/AND loc Like /*dto.loc_matchFront*/'TestData%'/*END*/
	/*IF dto.loc_matchBack != null*/AND loc Like /*dto.loc_matchBack*/'%TestData'/*END*/
	/*IF dto.loc != null*/AND loc = /*dto.loc*/'TestData'/*END*/
	/*IF dto.loc_not != null*/AND loc != /*dto.loc_not*/'TestData'/*END*/
	/*IF dto.loc_large != null*/AND  /*dto.loc_large*/'TestData' < loc/*END*/
	/*IF dto.loc_moreLarge != null*/AND  /*dto.loc_moreLarge*/'TestData' <= loc/*END*/
	/*IF dto.loc_from != null*/AND  /*dto.loc_from*/'TestData' <= loc/*END*/
	/*IF dto.loc_to != null*/AND loc <= /*dto.loc_to*/'TestData'/*END*/
	/*IF dto.loc_moreSmall != null*/AND loc <= /*dto.loc_moreSmall*/'TestData'/*END*/
	/*IF dto.loc_small != null*/AND loc < /*dto.loc_small*/'TestData'/*END*/
	/*IF dto.loc_isNull != null*/AND loc is null /*END*/
	/*IF dto.loc_isNotNull != null*/AND loc is not null/*END*/
	/*IF dto.loc_in != null*/AND loc in /*dto.loc_in*/('TestData')/*END*/

	
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

	/*IF keys != null*/AND deptno in /*keys*/(0) /*END*/
	
/*END*/
/*$dto.orderList*/
