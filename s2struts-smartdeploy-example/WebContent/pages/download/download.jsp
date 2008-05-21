<tiles:insert beanName="main">
  <tiles:put name="title" value="Download" />
  <tiles:put name="body" type="string">
  <html:form action="/download/download">
    <bean:define id="form" name="download_downloadForm" />
    <table>
      <tr>
        <td>text</td>
        <td><html:text property="text" styleClass="text" errorStyleClass="text-error"/></td>
      </tr>
    </table>
    <s2struts:submit action="@{download_downloadAction.execute}">download</s2struts:submit>
  </html:form>
  </tiles:put>
</tiles:insert>