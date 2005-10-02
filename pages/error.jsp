<%@ page contentType="text/html;charset=Windows-31j" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert page="/pages/layout/layout.jsp">
    <tiles:put name="layoutTitle" type="string">
        <bean:message key="title.error" />
    </tiles:put>

    <tiles:put name="body" type="string">
        <html:errors/>

<!--
<%
    Throwable e = (Throwable) request.getAttribute("org.apache.struts.action.EXCEPTION");
    if (e != null) {
        e.printStackTrace(new java.io.PrintWriter(out));
    }
%>
-->

    </tiles:put>
</tiles:insert>
