<tiles:insert beanName="main">
  <tiles:put name="title" value="Submit" />
  <tiles:put name="body" type="string">
  <html:form action="/image/image">
    <html:text property="arg" /><br />
    <s2struts:image action="@{image_imageAction.execute}" src="${contextPath}/images/submit.gif" />
    <s2struts:image action="@{image_imageAction.execute}" src="${contextPath}/images/cancel.gif" cancel="true" />
  </html:form>
  </tiles:put>
</tiles:insert>