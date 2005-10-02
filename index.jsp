<%@ page contentType="text/html;charset=Windows-31j" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%-- logic:redirect forward="addInput"/--%>
<tiles:insert page="/pages/layout/layout.jsp">
    <tiles:put name="layoutTitle" type="string">
        <bean:message key="sample.title"/>
    </tiles:put>

    <tiles:put name="body" type="string">
        <!-- blank -->
    </tiles:put>
</tiles:insert>
