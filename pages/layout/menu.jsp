<%@ page contentType="text/html;charset=Windows-31j" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<ul>
<li>
  <html:link page="/pages/addInput.jsp"><bean:message key="add.title"/></html:link>
</li>
<li>
  <html:link page="/pages/subtractInput.jsp"><bean:message key="subtract.title"/></html:link>
</li>
<li>
  <html:link page="/pages/multiplyInput.jsp"><bean:message key="multiply.title"/></html:link>
</li>
<li>
  <html:link page="/pages/divideInput.jsp"><bean:message key="divide.title"/></html:link>
</li>
<li>
  <html:link page="/modInput.do"><bean:message key="mod.title"/></html:link>
</li>
<li>
  <html:link page="/pages/echoInput.jsp"><bean:message key="echo.title"/></html:link>
</li>
<li>
  <html:link page="/pages/changeCaseInput.jsp"><bean:message key="changeCase.title"/></html:link>
</li>
<li>
  <html:link page="/message.do"><bean:message key="message.title"/></html:link>
</li>
<li>
  <html:link page="/pages/validate.jsp"><bean:message key="validate.title"/></html:link>
</li>
<li>
  <html:link forward="employee"><bean:message key="title.employee" /></html:link>
</li>
</ul>
