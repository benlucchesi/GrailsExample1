<html>
  <body>
  <g:form name="personForm" action="example6">
    <label for="firstName">First Name:</label>
    <g:textField name="firstName" /><br/>

    <label for="lastName">Last Name:</label>
    <g:textField name="lastName" /> <br/>

    <g:submitButton name="submit" value="Submit" />
  </g:form>

  <h2>Below will render a template</h2><br/>
  <g:render template="person" model="[person: person]" />

  </body>
</html>
