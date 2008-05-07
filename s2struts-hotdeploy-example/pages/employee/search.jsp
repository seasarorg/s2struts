<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <link href="../../css/global.css" rel="stylesheet" type="text/css"></link>
  <script type="text/javascript" src="../../js/execute.js"></script>
  <title id="pageTitle">
    Search - Employee Management Demo
  </title>
</head>
<body>
<s2struts:init action="#{employee_searchInitAction.initialize}"/>
<html:errors/>
<div id="appBody">
  <div id="errorMessage"></div>
  <html:form method="GET" action="employee_list">
    <table class="tablebg">
      <tr>
        <td class="label"><span id="labelEmpno">EmployeeNo</span></td>
        <td>
          <input type="text" name="empno" value="" class="number" id="empno"/>
        </td>
      </tr>
      <tr>
        <td class="label"><span id="labelEname">EmployeeName</span></td>
        <td>
          <input type="text" name="ename" value="" class="text" id="ename"/>
        </td>
      </tr>
      <tr>
        <td class="label"><span id="labelJob">Job</span></td>
        <td>
          <input type="text" name="job" value="" class="text" id="job"/>
        </td>
      </tr>
      <tr>
        <td class="label"><span id="labelMgr">Manager</span></td>
        <td>
          <input type="text" name="mgr" value="" class="number" id="mgr"/>
        </td>
      </tr>
      <tr>
        <td class="label"><span id="labelHiredate">HireDate</span></td>
        <td>
          <input type="text" name="fromHiredateDisplay" value="" class="date" id="fromHiredate"/> ～
          <input type="text" name="toHiredateDisplay" value="" class="date" id="toHiredate"/>
        </td>
      </tr>
      <tr>
        <td class="label"><span id="labelSal">Salary</span></td>
        <td>
          <input type="text" name="fromSal" value="" class="number" id="fromSal"/> ～
          <input type="text" name="toSal" value="" class="number" id="toSal"/>
        </td>
      </tr>
      <tr>
        <td class="label"><span id="labelDeptno">Department</span></td>
        <td>
          <html:select property="deptno" errorStyleClass="error">
            <html:option value="Please select"/>
            <html:options collection="departments" property="deptno" labelProperty="dname" />
          </html:select>
        </td>
      </tr>
    </table>

    <input type="submit" value="create" id="goCreate"/>
    <input type="submit" value="search" id="goSearch"/>
  </html:form>
</div>

</body>
</html>