<%@ page contentType="text/html;charset=Windows-31j" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://www.seasar.org/tags-s2struts" prefix="s2struts" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert page="/pages/layout/layout.jsp">
    <tiles:put name="layoutTitle" type="string">
        <bean:message key="echo.title"/>
    </tiles:put>

    <tiles:put name="body" type="string">

        <html:errors/>

        <html:form action="/changeCase" focus="input">
            <input name="input" type="text">
            <p>
            <html:submit property="command"><bean:message key="toLowerCase"/></html:submit>
            <p>
            <html:submit property="command"><bean:message key="toUpperCase"/></html:submit>
            <hr/>
            <b>MethodBinding</b><br/>
            button : 
            <s2struts:submit action="#{changeCaseAction.toLowerCase}"><bean:message key="toLowerCase"/></s2struts:submit>
            <s2struts:submit action="#{changeCaseAction.toUpperCase}"><bean:message key="toUpperCase"/></s2struts:submit>
            <br/>
            image : 
            <s2struts:image src="../images/toLowerCase.jpg" action="#{changeCaseAction.toLowerCase}"/>
            <s2struts:image src="../images/toUpperCase.jpg" action="#{changeCaseAction.toUpperCase}"/>

        </html:form>

    </tiles:put>
</tiles:insert>
