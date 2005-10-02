select count(*)
from emp e left outer join dept d on e.deptno = d.deptno
/*BEGIN*/
where 
/*IF dto.empno != null   && dto.empno != ""*/empno = /*dto.empno*/7788/*END*/
/*IF dto.ename != null   && dto.ename != ""*/and ename = /*dto.ename*/'SCOTT'/*END*/
/*IF dto.job != null     && dto.job   != ""*/and job = /*dto.job*/'ANALYST'/*END*/
/*IF dto.mgr != null     && dto.mgr   != ""*/and mgr = /*dto.mgr*/7566/*END*/
/*IF dto.fromHiredate != null*/and hiredate >= /*dto.fromHiredate*/'1982-12-01'/*END*/
/*IF dto.toHiredate != null*/and hiredate <= /*dto.toHiredate*/'1982-12-31'/*END*/
/*IF dto.fromSal != null && dto.fromSal != ""*/and sal >= /*dto.fromSal*/1000/*END*/
/*IF dto.toSal != null   && dto.toSal   != ""*/and sal <= /*dto.toSal*/4000/*END*/
/*IF dto.deptno != null  && dto.deptno  != ""*/and deptno = /*dto.deptno*/20/*END*/
/*END*/