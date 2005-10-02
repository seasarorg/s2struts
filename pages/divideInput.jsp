<%@ page contentType="text/html;charset=Windows-31j" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html:html locale="true">
<head>
<title><bean:message key="divide.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<html:errors/>

<html:form action="/divide" focus="arg1">
<input name="arg1" type="text"> /
<input name="arg2" type="text">
<html:submit><bean:message key="calc"/></html:submit>
</html:form>
</body>
</html:html>
