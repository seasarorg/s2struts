<%@ include file="/pages/common/tails-defs.jsp" %>
<tiles:insert beanName="main">
  <tiles:put name="title" value="Upload" />
  <tiles:put name="body" type="string">
  <html:form action="/upload/upload" enctype="multipart/form-data">
    <bean:define id="form" name="upload_uploadForm" />
    <table>
      <tr>
        <td>file</td>
        <td><html:file property="file" styleClass="text" errorStyleClass="text-error"/></td>
      </tr>
    </table>
    <s2struts:submit action="#{upload_uploadAction.execute}">upload</s2struts:submit>
  </html:form>
  </tiles:put>
</tiles:insert>