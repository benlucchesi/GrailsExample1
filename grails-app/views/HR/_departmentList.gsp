<g:if test="flash.departmentErrors">
  <span>${flash.departmentErrors}<span>
</g:if>
<table>
  <thead>
    <tr>
      <th>name</th>
      <th>description</th>
      <th>actions</th>
    </tr>
  </thead>
  <tbody>
    <g:each in="${departments}">
      <tr>
        <td>${it.name}</td>
        <td>${it.description}</td>
        <td><g:remoteLink action="deleteDepartment" id="${it.id}" update="departmentList">Delete</g:remoteLink>
      </tr>
    </g:each>
  </tbody>
</table>
