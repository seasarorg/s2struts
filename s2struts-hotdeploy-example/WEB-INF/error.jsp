<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html:html>
<head>
<title><bean:message key="system.error"/></title>
</head>
<body>
<html:errors/>
<br>
<html:link forward="welcome"><bean:message key="system.back"/></html:link><br>
<br>
</body>
</html:html>