<g:if test="flash.employeeErrors">
  <span>${flash.employeeErrors}</span>
</g:if>
<table>
  <thead>
    <tr>
      <th>email</th>
      <th>name</th>
      <th>actions</th>
    </tr>
  </thead>
  <tbody>
    <g:each in="${employees}">
      <tr>
        <td>${it.emailAddress}</td>
        <td>${it.firstName} ${it.lastName}</td>
        <td><g:remoteLink action="deleteEmployee" id="${it.id}" update="employeeList">Delete</g:remoteLink>
      </tr>
    </g:each>
  </tbody>
</table>
