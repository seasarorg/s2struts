<%@ page contentType="text/html;charset=Windows-31j" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.seasar.org/tags-s2struts" prefix="s2struts" %>

<tiles:insert page="/pages/layout/layout.jsp">
    <tiles:put name="layoutTitle" type="string">
        <bean:message key="validate.title" />
    </tiles:put>

    <tiles:put name="body" type="string">

	<html:errors />

	<html:form action="/validate" method="POST">
	<s2struts:page/>

	<table>
		<tr><td>required</td>		<td><html:text property="required" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>byte</td>		<td>	<html:text property="byte" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>short</td>		<td><html:text property="short" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>integer</td>		<td><html:text property="integer" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>long</td>		<td><html:text property="long" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>float</td>		<td><html:text property="float" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>double</td>		<td><html:text property="double" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>length</td>		<td><html:text property="length" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>length2</td>		<td><html:text property="length2" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>range</td>		<td><html:text property="range" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>date</td>		<td><html:text property="date" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>email</td>		<td><html:text property="email" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>url</td>			<td><html:text property="url" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>creditCard</td>	<td><html:text property="creditCard" styleClass="text" errorStyleClass="text-error"/></td></tr>
		<tr><td>mix</td>			<td><html:text property="mix" styleClass="text" errorStyleClass="text-error"/></td></tr>
	</table>

	<html:submit><bean:message key="validate"/></html:submit>

	</html:form>
	
    </tiles:put>
</tiles:insert>
