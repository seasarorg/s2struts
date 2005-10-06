<%@ page contentType="text/html;charset=Windows-31j" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.seasar.org/tags-s2struts" prefix="s2struts" %>

<s2struts:init action="#{employeeListInit.initialize}" />

<tiles:insert page="/pages/layout/layout.jsp">
    <tiles:put name="layoutTitle" type="string">
        <bean:message key="title.employee.list" /> - <bean:message key="title.employee" />
    </tiles:put>

    <tiles:put name="body" type="string">

<html:errors />
<html:form action="/employeeList" method="POST">

<table class="tablebg">
    <tr class="label">
        <th><bean:message key="form.employee.no" /></th>
        <th><bean:message key="form.employee.name" /></th>
        <th><bean:message key="form.employee.job" /></th>
        <th><bean:message key="form.employee.manager" /></th>
        <th><bean:message key="form.employee.hiredate" /></th>
        <th><bean:message key="form.employee.salary" /></th>
        <th><bean:message key="form.employee.commission" /></th>
        <th><bean:message key="form.department" /></th>
        <th><bean:message key="form.detail" /></th>
    </tr>
    
    <logic:iterate id="employee" name="employeeDtoList" indexId="index">
    <tr class="row-<%= index.intValue() % 2 %>">
		<td class="number">
		    <bean:write name="employee" property="empno" />
		</td>
        <td class="text">
            <bean:write name="employee" property="ename" />
        </td>
        <td class="text">
            <bean:write name="employee" property="job" />
        </td>
        <td class="number">
            <bean:write name="employee" property="mgr" />
        </td>
        <td class="date">
            <bean:write name="employee" property="hiredateDisplay" />
        </td>
        <td class="number">
            <bean:write name="employee" property="sal" />
        </td>
        <td class="number">
            <bean:write name="employee" property="comm" />
        </td>
        <td class="text">
            <bean:write name="employee" property="dname" />
        </td>
        <td>
            <html:link page="/employeeList.do?method=goEditForUpdate"
                       paramId="empno" paramName="employee" paramProperty="empno">
                <bean:message key="button.edit" />
            </html:link>
            <html:link page="/employeeList.do?method=goDelete"
                       paramId="empno" paramName="employee" paramProperty="empno">
                <bean:message key="button.delete" />
            </html:link>
            <html:link page="/employeeList.do?method=goInquire"
                       paramId="empno" paramName="employee" paramProperty="empno">
                <bean:message key="button.inquire" />
            </html:link>
        </td>
    </tr>
    </logic:iterate>
</table>

<html:link page="/pages/employee/employeeSearch.jsp">
    <bean:message key="button.prev" />
</html:link>

</html:form>

    </tiles:put>
</tiles:insert>
