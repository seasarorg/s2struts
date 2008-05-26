<tiles:insert beanName="main">
  <tiles:put name="title">
    <bean:message key="employee.title" />
  </tiles:put>
  <tiles:put name="body" type="string">
  <html:form action="/employee/confirm">
    <s2struts:init action="@{employee_confirmInitAction.init}"/>
    <bean:define id="form" name="employee_confirmForm" />
    <html:hidden property="crudType" value="${crudType}" />
    <table class="tablebg">
      <tr>
        <td class="label">EmployeeNo</td>
        <td class="number">
          <bean:write name="form" property="empno" />
          <html:hidden property="empno" />
          <html:hidden property="versionNo" />
        </td>
      </tr>
      <tr>
        <td class="label">EmployeeName</td>
        <td class="text">
          <bean:write name="form" property="ename" />
          <html:hidden property="ename" />
        </td>
      </tr>
      <tr>
        <td class="label">Job</td>
        <td class="text">
          <bean:write name="form" property="job" />
          <html:hidden property="job" />
        </td>
      </tr>
      <tr>
        <td class="label">Manager</td>
        <td class="number">
          <bean:write name="form" property="mgr" />
          <html:hidden property="mgr" />
        </td>
      </tr>
      <tr>
        <td class="label">HireDate</td>
        <td class="date">
          <bean:write name="form" property="hiredate" />
          <html:hidden property="hiredate" />
        </td>
      </tr>
      <tr>
        <td class="label">Salary</td>
        <td class="number">
          <bean:write name="form" property="sal" />
          <html:hidden property="sal" />
        </td>
      </tr>
      <tr>
        <td class="label">Commission</td>
        <td class="number">
          <bean:write name="form" property="comm" />
          <html:hidden property="comm" />
        </td>
      </tr>
      <tr>
        <td class="label">Department</td>
        <td class="text">
          <bean:write name="form" property="dname" />
          <html:hidden property="deptno" />
        </td>
      </tr>
    </table>
    <c:if test="${crudType != 'r'}">
      <s2struts:submit action="@{employee_confirmAction.goStore}" ><bean:message key="button.store" /></s2struts:submit>
    </c:if>
    <s2struts:submit action="@{employee_confirmAction.goPrevious}"><bean:message key="button.prev" /></s2struts:submit>
  </html:form>
  </tiles:put>
</tiles:insert>