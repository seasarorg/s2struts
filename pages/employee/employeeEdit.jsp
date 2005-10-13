<%@ page contentType="text/html;charset=Windows-31j" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.seasar.org/tags-s2struts" prefix="s2struts" %>

<s2struts:init action="#{employeeEditInitAction.initialize}" />

<logic:equal name="processModeDto" property="processMode" value="1">
    <bean:define id="title" value="title.employee.create" />
</logic:equal>
<logic:equal name="processModeDto" property="processMode" value="2">
    <bean:define id="title" value="title.employee.update" />
</logic:equal>

<tiles:insert page="/pages/layout/layout.jsp">
    <tiles:put name="layoutTitle" type="string">
        <bean:message name="title" /> - <bean:message key="title.employee" />
    </tiles:put>

    <tiles:put name="body" type="string">

<h2><bean:message name="title" /></h2>

<html:errors />
<html:form action="/employeeEdit" method="POST">
<s2struts:page />

<logic:equal name="processModeDto" property="processMode" value="2">
    <html:hidden property="versionNo" />
    <html:hidden property="empno" />
</logic:equal>

<table class="tablebg">
<tr>
    <td class="label"><bean:message key="form.employee.no" /></td>
    <td>
        <logic:equal name="processModeDto" property="processMode" value="1">
            <html:text property="empno" styleClass="number" errorStyleClass="number-error" />
        </logic:equal>
        <logic:equal name="processModeDto" property="processMode" value="2">
            <div class="right">
                <bean:write name="employeeForm" property="empno"/>
            </div>
        </logic:equal>
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
        <html:text property="hiredateDisplay" styleClass="date" errorStyleClass="date-error" />
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.employee.salary" /></td>
    <td>
        <html:text property="sal" styleClass="number" errorStyleClass="number-error" />
    </td>
</tr>
<tr>
    <td class="label"><bean:message key="form.employee.commission" /></td>
    <td>
        <html:text property="comm" styleClass="number" errorStyleClass="number-error" />
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

<s2struts:submit action="#{employeeEditAction.goConfirm}">
    <bean:message key="button.confirm" />
</s2struts:submit>
<s2struts:submit action="#{employeeEditAction.goPrevious}">
    <bean:message key="button.prev" />
</s2struts:submit>

</html:form>

    </tiles:put>
</tiles:insert>
