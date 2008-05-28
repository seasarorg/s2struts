<tiles:insert beanName="main">
  <tiles:put name="title">
    <bean:message key="employee.title" />
  </tiles:put>
  <tiles:put name="body" type="string">
  <html:form action="/employee/search">
    <s2struts:init action="@{employee_searchInitAction.init}"/>
    <table class="tablebg">
      <tr>
        <td class="label"><bean:message key="employee.employeeNo" /></td>
        <td>
          <html:text property="empno" styleClass="number" errorStyleClass="number-error" />
        </td>
      </tr>
      <tr>
        <td class="label"><bean:message key="employee.employeeName" /></td>
        <td>
          <html:text property="ename" styleClass="text" errorStyleClass="text-error" />
        </td>
      </tr>
      <tr>
        <td class="label"><bean:message key="employee.job" /></td>
        <td>
          <html:text property="job" styleClass="text" errorStyleClass="text-error" />
        </td>
      </tr>
      <tr>
        <td class="label"><bean:message key="employee.manager" /></td>
        <td>
          <html:text property="mgr" styleClass="number" errorStyleClass="number-error" />
        </td>
      </tr>
      <tr>
        <td class="label"><bean:message key="employee.hiredate" /></td>
        <td>
          <html:text property="fromHiredate" styleClass="date" errorStyleClass="date-error" /> ～
          <html:text property="toHiredate" styleClass="date" errorStyleClass="date-error" />
        </td>
      </tr>
      <tr>
        <td class="label"><bean:message key="employee.salary" /></td>
        <td>
          <html:text property="fromSal" styleClass="number" errorStyleClass="number-error" /> ～
          <html:text property="toSal" styleClass="number" errorStyleClass="number-error" />
        </td>
      </tr>
      <tr>
        <td class="label"><bean:message key="employee.department" /></td>
        <td>
          <html:select property="deptno" errorStyleClass="error">
            <html:option value="" key="messages.select.space" />
            <html:options collection="deptItems" property="deptno" labelProperty="dname" />
          </html:select>
        </td>
      </tr>
    </table>
    <s2struts:submit action="@{employee_searchAction.goEdit}" cancel="true"><bean:message key="button.create" /></s2struts:submit>
    <s2struts:submit action="@{employee_searchAction.goList}"><bean:message key="button.search" /></s2struts:submit>
  </html:form>
  </tiles:put>
</tiles:insert>