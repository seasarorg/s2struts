<tiles:insert beanName="main">
  <tiles:put name="title" value="Iterate" />
  <tiles:put name="body" type="string">
  <html:form action="/iterate/iterate">
    <table>
      <c:forEach begin="1" end="3" varStatus="s">
        <tr>
          <td>
            <s2struts:submit action="#{iterate_iterateAction.submit}" indexed="true" cancel="true">
              submit <c:out value="${s.index}" />
            </s2struts:submit>
          </td>
          <td>
            <c:url value="/images/echo.jpg" var="src" />
            <s2struts:image action="#{iterate_iterateAction.image}"  src="${src}" indexed="true" cancel="true">
              image <c:out value="${s.index}" />
            </s2struts:image>
          </td>
          <td>
            <s2struts:link action="#{iterate_iterateAction.link}" indexed="true" cancel="true">
              link <c:out value="${s.index}" />
            </s2struts:link>
          </td>
        </tr>
      </c:forEach>
    </table>
  </html:form>
  </tiles:put>
</tiles:insert>