<%@ page contentType="text/html;charset=Windows-31j" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html:html>
<head>
<link href="<html:rewrite forward='globalStyle'/>" rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src="<html:rewrite forward='executeJs'/>"></script>
<title>
  <tiles:insert attribute="layoutTitle"/>
</title>
</head>

<body>
<table border="0" cellspacing="5">
<tr>
  <td colspan="2">
    <tiles:insert page="/pages/layout/header.jsp"/>
  </td>
</tr>
<tr>
  <td width="200" valign="top">
    <tiles:insert page="/pages/layout/menu.jsp"/>
  </td>

  <td valign="top" align="left">
    <tiles:insert attribute="body"/>
  </td>
</tr>
<tr>
  <td colspan="2">
    <hr/>
  </td>
</tr>
<tr>
  <td colspan="2">
    <tiles:insert page="/pages/layout/footer.jsp"/>
  </td>
</tr>
</table>
</body>

</html:html>