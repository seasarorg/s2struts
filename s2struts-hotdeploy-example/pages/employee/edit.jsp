<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <html:base />
  <link href="../../css/global.css" rel="stylesheet" type="text/css"></link>
  <script type="text/javascript" src="../../js/execute.js"></script>
  <title id="pageTitle">
    Edit - Employee Management Demo
  </title>
</head>
<body>
<s2struts:init action="#{employee_editInitAction.initialize}"/>
<html:errors/>
<html:form method="POST" action="employee_edit">
  <html:hidden property="crudType" value="${crudType}" />
  <c:set var="e" value="${employeeDto}"/>
  <table class="tablebg">
    <tr>
      <td class="label"><span id="labelEmpno">EmployeeNo</span></td>
      <td>
        <c:if test="${crudType == 'c'}">
            <html:text property="empno" styleClass="number" errorStyleClass="number-error" />
        </c:if>
        <c:if test="${crudType == 'u'}">
            <html:hidden property="versionNo" />
            <html:hidden property="empno" />
            <c:out value="${e.empno}" />
        </c:if>
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelEname">EmployeeName</span></td>
      <td>
        <html:text property="ename" value="${e.ename}" styleClass="text" errorStyleClass="text-error" />
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelJob">Job</span></td>
      <td>
        <html:text property="job" value="${e.job}" styleClass="text" errorStyleClass="text-error" />
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelMgr">Manager</span></td>
      <td>
        <html:text property="mgr" value="${e.mgr}" styleClass="text" errorStyleClass="text-error" />
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelHiredate">HireDate</span></td>
      <td>
        <fmt:formatDate value="${e.hiredate}" pattern="yyyy/MM/dd" var="hiredate" />
        <html:text property="hiredate" value="${hiredate}" styleClass="date" errorStyleClass="date-error" />
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelSal">Salary</span></td>
      <td>
        <html:text property="sal" value="${e.sal}" styleClass="number" errorStyleClass="number-error" />
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelComm">Commission</span></td>
      <td>
        
      </td>
    </tr>
    <tr>
      <td class="label"><span id="labelDeptno">Department</span></td>
      <td>
	    <html:select property="deptno" value="${e.deptno}" errorStyleClass="error">
	        <html:option value="" key="messages.select.space" />
	        <html:options collection="deptItems" property="deptno" labelProperty="dname" />
	    </html:select>
      </td>
    </tr>
  </table>
  <s2struts:submit action="#{employee_confirmAction.goConfirm}" value="confirm"/>
  <s2struts:submit action="#{employee_confirmAction.goPrevious}" value="previous"/>
</html:form>
</body>
</html>