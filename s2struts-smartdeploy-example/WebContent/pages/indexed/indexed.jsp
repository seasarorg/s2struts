<tiles:insert beanName="main">
  <tiles:put name="title" value="Indexed" />
  <tiles:put name="body" type="string">
  <html:form action="/indexed/indexed">
    <table border="1">
      <tr>
        <th>child</th>
        <td><html:text property="children[0].value" styleClass="text" errorStyleClass="text-error"/></td>
        <td><html:text property="children[1].value" styleClass="text" errorStyleClass="text-error"/></td>
        <td><html:text property="children[2].value" styleClass="text" errorStyleClass="text-error"/></td>
      </tr>
      <tr>
        <th>array</th>
        <td><html:text property="array[0]" styleClass="text" errorStyleClass="text-error"/></td>
        <td><html:text property="array[1]" styleClass="text" errorStyleClass="text-error"/></td>
        <td><html:text property="array[2]" styleClass="text" errorStyleClass="text-error"/></td>
      </tr>
      <tr>
        <th>list</th>
        <td><html:text property="list[0]" styleClass="text" errorStyleClass="text-error"/></td>
        <td><html:text property="list[1]" styleClass="text" errorStyleClass="text-error"/></td>
        <td><html:text property="list[2]" styleClass="text" errorStyleClass="text-error"/></td>
      </tr>
      <tr>
        <th>map</th>
        <td><html:text property="map(aaa)" styleClass="text" errorStyleClass="text-error"/></td>
        <td><html:text property="map(bbb)" styleClass="text" errorStyleClass="text-error"/></td>
        <td><html:text property="map(ccc)" styleClass="text" errorStyleClass="text-error"/></td>
      </tr>
    </table>
    <s2struts:submit action="@{indexed_indexedAction.execute}" value="submit" />
  </html:form>
  </tiles:put>
</tiles:insert>