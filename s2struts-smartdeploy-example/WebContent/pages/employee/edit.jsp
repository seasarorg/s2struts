<tiles:insert beanName="main">
  <tiles:put name="title">
    <bean:message key="employee.title" />
  </tiles:put>
  <tiles:put name="body" type="string">
  <html:form action="/employee/edit">
    <s2struts:init action="@{employee_editInitAction.init}"/>
    <bean:define id="form" name="employee_editForm" />
    <html:hidden property="crudType" value="${crudType}" />
    <table class="tablebg">
      <tr>
        <td class="label"><bean:message key="employee.employeeNo" /></td>
        <td>
          <c:if test="${crudType == 'c'}">
              <html:text property="empno" styleClass="number" errorStyleClass="number-error" />
          </c:if>
          <c:if test="${crudType == 'u'}">
              <html:hidden property="versionNo" />
              <html:hidden property="empno" />
              <bean:write  name="form" property="empno" />
          </c:if>
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
          <html:text property="mgr" styleClass="text" errorStyleClass="text-error" />
        </td>
      </tr>
      <tr>
        <td class="label"><bean:message key="employee.hiredate" /></td>
        <td>
          <html:text property="hiredate" styleClass="date" errorStyleClass="date-error" />
        </td>
      </tr>
      <tr>
        <td class="label"><bean:message key="employee.salary" /></td>
        <td>
          <html:text property="sal" styleClass="number" errorStyleClass="number-error" />
        </td>
      </tr>
      <tr>
        <td class="label"><bean:message key="employee.commission" /></td>
        <td>
          <html:text property="comm" styleClass="number" errorStyleClass="number-error" />
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
    <s2struts:submit action="@{employee_editAction.goConfirm}"><bean:message key="button.confirm"/></s2struts:submit>
    <s2struts:submit action="@{employee_editAction.goPrevious}" cancel="true"><bean:message key="button.prev"/></s2struts:submit>
  </html:form>
  </tiles:put>
</tiles:insert>