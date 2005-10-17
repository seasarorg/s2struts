<%@ page contentType="text/html;charset=Windows-31j" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert page="/pages/layout/layout.jsp">
    <tiles:put name="layoutTitle" type="string">
        <bean:message key="mod.title"/>
    </tiles:put>

    <tiles:put name="body" type="string">

        <html:errors/>

        <html:form action="/mod" focus="arg1">
            <html:text property="arg1"/> %
            <html:text property="arg2"/>
            <html:submit><bean:message key="calc"/></html:submit>
        </html:form>

    </tiles:put>
</tiles:insert>
