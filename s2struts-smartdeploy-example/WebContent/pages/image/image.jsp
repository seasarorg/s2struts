<tiles:insert beanName="main">
  <tiles:put name="title" value="Submit" />
  <tiles:put name="body" type="string">
  <html:form action="/image/image">
    <html:text property="arg" /><br />
    <s2struts:image action="@{image_imageAction.execute}" src="${contextPath}/images/echo.jpg" />
    <s2struts:image action="@{image_imageAction.execute}" src="${contextPath}/images/echo.jpg" cancel="true" />
  </html:form>
  </tiles:put>
</tiles:insert>