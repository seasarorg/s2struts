<tiles:insert beanName="main">
  <tiles:put name="title" value="Checkbox" />
  <tiles:put name="body" type="string">
  <html:form action="/checkbox/checkbox">
    <bean:define id="form" name="checkbox_checkboxForm" />
    <table>
      <tr>
        <td>check1</td>
        <td><html:checkbox property="arg1" /></td>
        <td><bean:write name="form" property="arg1" /></td>
      </tr>
      <tr>
        <td>check2</td>
        <td><s2struts:checkbox property="arg2" /></td>
        <td><bean:write name="form" property="arg2" /></td>
      </tr>
    </table>
    <s2struts:submit action="@{checkbox_checkboxAction.execute}">submit</s2struts:submit>
  </html:form>
  </tiles:put>
</tiles:insert>