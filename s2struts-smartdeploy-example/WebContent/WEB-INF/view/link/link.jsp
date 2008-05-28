<tiles:insert beanName="main">
  <tiles:put name="title" value="Link" />
  <tiles:put name="body" type="string">
  <html:form action="/link/link">
    <s2struts:link action="@{link_linkAction.execute}">link</s2struts:link>
    <s2struts:link action="@{link_linkAction.execute}" cancel="true">cancel</s2struts:link>
  </html:form>
  </tiles:put>
</tiles:insert>