<tiles:insert beanName="main">
  <tiles:put name="title" value="Validate" />
  <tiles:put name="body" type="string">
  <html:form action="/validate/validate">
    <table>
      <tr><td>required</td><td><html:text property="required" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>requiredOther</td><td><html:text  property="requiredOtherMessage" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>byte</td><td><html:text property="byte" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>short</td><td><html:text property="short" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>integer</td><td><html:text property="integer" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>long</td><td><html:text property="long" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>float</td><td><html:text property="float" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>double</td><td><html:text property="double" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>length</td><td><html:text property="length" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>length2</td><td><html:text property="length2" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>range</td><td><html:text property="range" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>date</td><td><html:text property="date" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>email</td><td><html:text property="email" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>url</td><td><html:text property="url" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>creditCard</td><td><html:text property="creditCard" styleClass="text" errorStyleClass="text-error"/></td></tr>
      <tr><td>mix</td><td><html:text property="mix" styleClass="text" errorStyleClass="text-error"/></td></tr>
    </table>
    <s2struts:submit action="@{validate_validateAction.execute}" value="validate" />
  </html:form>
  </tiles:put>
</tiles:insert>