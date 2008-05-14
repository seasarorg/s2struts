<%@ include file="/pages/common/layout-defs.jsp" %>
<tiles:insert beanName="main">
  <tiles:put name="title" value="S2Struts HOT deploy Example" />
  <tiles:put name="body" type="string">
    <h1>Welcome</h1>
  </tiles:put>
</tiles:insert>