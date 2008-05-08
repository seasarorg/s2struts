<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <html:base />
  <link href="../../css/global.css" rel="stylesheet" type="text/css"></link>
  <script type="text/javascript" src="../../js/execute.js"></script>
  <title id="pageTitle">
    Confirm - Employee Management Demo
  </title>
</head>
<body>
<s2struts:init action="#{employee_confirmInitAction.initialize}"/>
<html:errors/>
<html:form method="POST" action="employee_confirm">
  <html:hidden property="crudType" value="${crudType}" />
  <c:set var="e" value="${employeeDto}"/>
  <table class="tablebg">
    <tr>
      <td class="label"><span id="labelEmpno">EmployeeNo</span></td>
      <td class="number">
        <c:out value="${e.empno}" />
        <html:hidden property="empno" value="${e.empno}" />
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelEname">EmployeeName</span></td>
      <td class="text">
        <c:out value="${e.ename}" />
        <html:hidden property="ename" value="${e.ename}" />
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelJob">Job</span></td>
      <td class="text">
        <c:out value="${e.job}" />
        <html:hidden property="job" value="${e.job}" />
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelMgr">Manager</span></td>
      <td class="number">
        <c:out value="${e.mgr}" />
        <html:hidden property="mgr" value="${e.mgr}" />
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelHiredate">HireDate</span></td>
      <td class="date">
        <fmt:formatDate value="${e.hiredate}" pattern="yyyy/MM/dd" var="hiredate" />
        <c:out value="${hiredate}" />
        <html:hidden property="hiredate" value="${hiredate}" />
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelSal">Salary</span></td>
      <td class="number">
        <c:out value="${e.sal}" />
        <html:hidden property="sal" value="${e.sal}" />
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelComm">Commission</span></td>
      <td class="number">
        <span id="comm"></span>
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelDeptno">Department</span></td>
      <td class="text">
        <c:out value="${e.dname}" />
        <html:hidden property="dname" value="${e.dname}" />
      </td>
    </tr>
  </table>
  <c:if test="${crudType != 'r'}">
    <s2struts:submit action="#{employee_confirmAction.store}" value="store"/>
  </c:if>
  <s2struts:submit action="#{employee_confirmAction.goPrevious}" value="previous"/>
</html:form>
</body>
</html>