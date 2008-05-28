<tiles:insert beanName="main">
  <tiles:put name="title" value="Submit" />
  <tiles:put name="body" type="string">
  <html:form action="/submit/submit">
    <html:text property="arg" /><br />
    <s2struts:submit action="@{submit_submitAction.execute}" value="submit" />
    <s2struts:submit action="@{submit_submitAction.execute}" value="cancel" cancel="true" />
  </html:form>
  </tiles:put>
</tiles:insert>