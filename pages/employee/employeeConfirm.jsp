<%@ page contentType="text/html;charset=Windows-31j" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<logic:equal name="processModeDto" property="processMode" value="1">
    <bean:define id="title" value="title.employee.create" />
</logic:equal>
<logic:equal name="processModeDto" property="processMode" value="2">
    <bean:define id="title" value="title.employee.update" />
</logic:equal>
<logic:equal name="processModeDto" property="processMode" value="3">
    <bean:define id="title" value="title.employee.delete" />
</logic:equal>
<logic:equal name="processModeDto" property="processMode" value="4">
    <bean:define id="title" value="title.employee.inquire" />
</logic:equal>

<tiles:insert page="/pages/layout/layout.jsp">
    <tiles:put name="layoutTitle" type="string">
        <bean:message name="title"/> - <bean:message key="title.employee" />
    </tiles:put>

    <tiles:put name="body" type="string">

<h2><bean:message name="title" /></h2>

<html:errors />
<html:form action="/employee" method="POST">
<html:hidden property="method" value="goError" />

<html:hidden property="versionNo" />
<html:hidden property="empno" />
<html:hidden property="ename" />
<html:hidden property="job" />
<html:hidden property="mgr" />
<html:hidden property="hiredateDisplay" />
<html:hidden property="sal" />
<html:hidden property="comm" />
<html:hidden property="deptno" />
<html:hidden property="dname" />

<table class="tablebg">
<tr>
    <td class="label"><bean:message key="form.employee.no" /></td>
    <td class="number">
        <bean:write name="employeeForm" property="empno"/>
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.employee.name" /></td>
    <td class="text">
        <bean:write name="employeeForm" property="ename"/>
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.employee.job" /></td>
    <td class="text">
        <bean:write name="employeeForm" property="job"/>
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.employee.manager" /></td>
    <td class="number">
        <bean:write name="employeeForm" property="mgr"/>
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.employee.hiredate" /></td>
    <td class="date">
        <bean:write name="employeeForm" property="hiredateDisplay"/>
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.employee.salary" /></td>
    <td class="number">
        <bean:write name="employeeForm" property="sal"/>
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.employee.commission" /></td>
    <td class="number">
        <bean:write name="employeeForm" property="comm"/>
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.department" /></td>
    <td class="text">
        <bean:write name="employeeForm" property="dname"/>
    </td>
</tr>
</table>

<logic:notEqual name="processModeDto" property="processMode" value="4">
    <html:submit property="goStore" onclick="go(this);">
        <bean:message key="button.store" />
    </html:submit>
</logic:notEqual>
<html:submit property="goPreviousFromConfirm" onclick="go(this);">
    <bean:message key="button.prev" />
</html:submit>
	
</html:form>

    </tiles:put>
</tiles:insert>
