<tiles:insert beanName="main">
  <tiles:put name="title" value="Upload" />
  <tiles:put name="body" type="string">
  <html:form action="/upload/result">
    <s2struts:init action="#{upload_resultInitAction.init}" />
    <bean:define id="form" name="upload_resultForm" />
    <table>
      <tr>
       <td>name: </td>
       <td><bean:write name="form" property="name"/></td>
     </tr>
      <tr>
        <td>size: </td>
        <td><bean:write name="form" property="size"/> bytes</td>
      </tr>
    </table>
    <s2struts:submit action="#{upload_resultAction.goPrevious}"><bean:message key="button.prev" /></s2struts:submit>
  </html:form>
  </tiles:put>
</tiles:insert>