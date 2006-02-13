<%@ page contentType="text/html;charset=Windows-31J" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="s2struts" uri="http://www.seasar.org/tags-s2struts" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=Windows-31j">
  <link href="../../css/global.css" rel="stylesheet" type="text/css"></link>
  <script type="text/javascript" src="../../js/execute.js"></script>
  <title id="pageTitle">
    <bean:message key="iteration.title.list" />
  </title>
</head>
<body>
<s2struts:init action="#{iterationListInitAction.initialize}" />
<div id="appBody">
  <div id="errorMessage"></div>

  <html:form action="/iterationList" method="POST">
    <s2struts:submit action="#{iterationListAction.goCreate}">create</s2struts:submit>
    <table border="1">
      <tr>
        <th>command</th>
        <th>id</th>
        <th>name</th>
      </tr>
    <logic:iterate id="iterations" name="iterationListDto" property="iterations" indexId="index">
      <tr>
        <td>
          <s2struts:submit action="#{iterationListAction.goEdit}" indexId="index">edit</s2struts:submit>
          <s2struts:submit action="#{iterationListAction.goDelete}" indexId="index">delete</s2struts:submit>
        </td>
        <td>
          <html:hidden name="iterations" property="id" indexed="true"/>
          <bean:write name="iterations" property="id"/>
        </td>
        <td><bean:write name="iterations" property="name"/></td>
      </tr>
    </logic:iterate>
    </table>
  </html:form>
</div>

</body>
</html>
