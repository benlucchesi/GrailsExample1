<html>
  <body>
    <g:link action="example4" params="[firstName: 'ben', lastName: 'lucchesi',age: 25]">I'm a list with parameters</g:link>
    
    <h3>List of params passed to controller</h3>
    <ol>
      <g:each in="${params}" var="item">
        <li>${item}</li>
      </g:each>
    </ol>
  </body>
</html>
