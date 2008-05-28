<tiles:insert beanName="main">
  <tiles:put name="title">
    <bean:message key="employee.title" />
  </tiles:put>
  <tiles:put name="body" type="string">
  <s2struts:init action="@{employee_listInitAction.init}"/>
  <table class="tablebg">
    <tr class="label">
      <th><bean:message key="employee.employeeNo" /></th>
      <th><bean:message key="employee.employeeName" /></th>
      <th><bean:message key="employee.job" /></th>
      <th><bean:message key="employee.manager" /></th>
      <th><bean:message key="employee.hiredate" /></th>
      <th><bean:message key="employee.salary" /></th>
      <th><bean:message key="employee.commission" /></th>
      <th><bean:message key="employee.department" /></th>
      <th><bean:message key="employee.detail" /></th>
    </tr>
    <c:forEach var="e" varStatus="s" items="${empItems}">
      <tr class="${s.index %2 == 0 ? 'row-0' : 'row-1'}">
        <td class="number"><c:out value="${e.empno}" /></td>
        <td class="text"><c:out value="${e.ename}" /></td>
        <td class="text"><c:out value="${e.job}" /></td>
        <td class="number"><c:out value="${e.mgr}" /></td>
        <td class="date"><fmt:formatDate value="${e.hiredate}" pattern="yyyy/MM/dd" var="hiredate" /><c:out value="${hiredate}" /></td>
        <td class="number"><c:out value="${e.sal}" /></td>
        <td class="number"><c:out value="${e.comm}" /></td>
        <td class="text"><c:out value="${e.dname}" /></td>
        <td>
          <s2struts:link paramId="empno" paramName="e" paramProperty="empno" action="@{employee_listAction.goEdit}" cancel="true"><bean:message key="button.edit" /></s2struts:link>
          <s2struts:link paramId="empno" paramName="e" paramProperty="empno" action="@{employee_listAction.goDelete}" cancel="true"><bean:message key="button.delete" /></s2struts:link>
          <s2struts:link paramId="empno" paramName="e" paramProperty="empno" action="@{employee_listAction.goInquire}" cancel="true"><bean:message key="button.inquire" /></s2struts:link>
        </td>
      </tr>
    </c:forEach>
  </table>
  <s2struts:link action="@{employee_listAction.goPrevious}"><bean:message key="button.prev" /></s2struts:link>
  </tiles:put>
</tiles:insert>
