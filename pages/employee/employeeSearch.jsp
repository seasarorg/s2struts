<%@ page contentType="text/html;charset=Windows-31j" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.seasar.org/tags-s2struts" prefix="s2struts" %>

<s2struts:init action="#{employeeSearchInitAction.initialize}" />

<tiles:insert page="/pages/layout/layout.jsp">
    <tiles:put name="layoutTitle" type="string">
        <bean:message key="title.employee.search" /> - <bean:message key="title.employee" />
    </tiles:put>

    <tiles:put name="body" type="string">

<html:errors />
<html:form action="/employeeSearch" method="POST">
<s2struts:page />

<table class="tablebg">
<tr>
    <td class="label"><bean:message key="form.employee.no" /></td>
    <td>
        <html:text property="empno" styleClass="number" errorStyleClass="number-error" />
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.employee.name" /></td>
    <td>
        <html:text property="ename" styleClass="text" errorStyleClass="text-error" />
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.employee.job" /></td>
    <td>
        <html:text property="job" styleClass="text" errorStyleClass="text-error" />
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.employee.manager" /></td>
    <td>
        <html:text property="mgr" styleClass="number" errorStyleClass="number-error" />
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.employee.hiredate" /></td>
    <td>
        <html:text property="fromHiredateDisplay" styleClass="date" errorStyleClass="date-error" />
        Å`
        <html:text property="toHiredateDisplay" styleClass="date" errorStyleClass="date-error" />
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.employee.salary" /></td>
    <td>
        <html:text property="fromSal" styleClass="number" errorStyleClass="number-error" />
        Å`
        <html:text property="toSal" styleClass="number" errorStyleClass="number-error" />
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.department" /></td>
    <td>
        <html:select property="deptno" errorStyleClass="error">
    		<html:option value="" key="messages.select.space" />
    		<html:options collection="departmentDtoList"
    		    property="deptno" labelProperty="dname" />
    	</html:select>
	</td>
</tr>
</table>

<s2struts:submit action="#{employeeSearchAction.goEditForCreate}">
    <bean:message key="button.create" />
</s2struts:submit>
<s2struts:submit action="#{employeeSearchAction.goList}">
    <bean:message key="button.search" />
</s2struts:submit>

</html:form>

    </tiles:put>
</tiles:insert>
