<%@ page contentType="text/html;charset=Windows-31j" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert page="/pages/layout/layout.jsp">
    <tiles:put name="layoutTitle" type="string">
        <bean:message key="result.title"/>
    </tiles:put>

    <tiles:put name="body" type="string">
        <center>
            <bean:message key="result"/>:<bean:write name="calcForm" property="result"/>
        </center>
    </tiles:put>
</tiles:insert>
