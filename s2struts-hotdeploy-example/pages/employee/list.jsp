<tiles:insert beanName="main">
  <tiles:put name="title" value="Employee Management" />
  <tiles:put name="body" type="string">
  <s2struts:init action="#{employee_listInitAction.init}"/>
  <table class="tablebg">
    <tr class="label">
      <th><bean:message key="button.edit" /><span id="labelEmpno">EmployeeNo</span></th>
      <th><span id="labelEname">EmployeeName</span></th>
      <th><span id="labelJob">Job</span></th>
      <th><span id="labelMgr">Manager</span></th>
      <th><span id="labelHiredate">HireDate</span></th>
      <th><span id="labelSal">Salary</span></th>
      <th><span id="labelComm">Commission</span></th>
      <th><span id="labelDept">Department</span></th>
      <th><span id="labelDetail">Detail</span></th>
    </tr>
    <c:forEach var="e" varStatus="s" items="${empItems}">
      <tr class="${s.index %2 == 0 ? 'row-0' : 'row-1'}">
        <td class="number"><c:out value="${e.empno}" /></td>
        <td class="text"><c:out value="${e.ename}" /></span></td>
        <td class="text"><c:out value="${e.job}" /></span></td>
        <td class="number"><c:out value="${e.mgr}" /></span></td>
        <td class="date"><fmt:formatDate value="${e.hiredate}" pattern="yyyy/MM/dd" var="hiredate" /><c:out value="${hiredate}" /></span></td>
        <td class="number"><c:out value="${e.sal}" /></span></td>
        <td class="number"><c:out value="${e.comm}" /></td>
        <td class="text"><c:out value="${e.dname}" /></td>
        <td>
          <s2struts:link paramId="empno" paramName="e" paramProperty="empno" action="#{employee_listAction.goEdit}" cancel="true"><bean:message key="button.edit" /></s2struts:link>
          <s2struts:link paramId="empno" paramName="e" paramProperty="empno" action="#{employee_listAction.goDelete}" cancel="true"><bean:message key="button.delete" /></s2struts:link>
          <s2struts:link paramId="empno" paramName="e" paramProperty="empno" action="#{employee_listAction.goInquire}" cancel="true"><bean:message key="button.inquire" /></s2struts:link>
        </td>
      </tr>
    </c:forEach>
  </table>
  <s2struts:link action="#{employee_listAction.goPrevious}"><bean:message key="button.prev" /></s2struts:link>
  </tiles:put>
</tiles:insert>
