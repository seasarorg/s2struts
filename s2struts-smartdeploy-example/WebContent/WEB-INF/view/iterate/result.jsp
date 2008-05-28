<tiles:insert beanName="main">
  <tiles:put name="title" value="Upload" />
  <tiles:put name="body" type="string">
  <html:form action="/iterate/result">
    <s2struts:init action="@{iterate_resultInitAction.init}" />
    <bean:define id="form" name="iterate_resultForm" />
    <table>
      <tr>
       <td>name: </td>
       <td><bean:write name="form" property="name"/></td>
     </tr>
      <tr>
        <td>index: </td>
        <td><bean:write name="form" property="index"/></td>
      </tr>
    </table>
    <s2struts:submit action="@{iterate_resultAction.goPrevious}"><bean:message key="button.prev" /></s2struts:submit>
  </html:form>
  </tiles:put>
</tiles:insert>