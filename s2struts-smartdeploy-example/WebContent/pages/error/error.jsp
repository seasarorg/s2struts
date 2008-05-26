<%@page import="org.apache.struts.Globals"%>
<%@page import="java.io.PrintWriter"%>
<tiles:insert beanName="main">
  <tiles:put name="title" value="Employee Management" />
  <tiles:put name="body" type="string">
  <%
  Exception e = (Exception) request.getAttribute(Globals.EXCEPTION_KEY);
  e.printStackTrace(new PrintWriter(out));
  %>
  </tiles:put>
</tiles:insert>
