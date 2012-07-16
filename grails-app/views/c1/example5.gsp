<%@ page import="grailsexample1.Person" %>
<html>
  <body>
    <g:link action="example5" params="[firstName: 'ben', lastName: 'lucchesi',age: 25]">I'm a list with parameters</g:link><p/>
    <h3>Person: ${person}</h3>
  </body>
</html>
