<html>
  <head>
    <g:javascript library="jquery" />
    <r:layoutResources />
  </head>
  <body>
    <hr />
    <h2>Departments</h2>

    <g:render template="departmentForm" /> 

    <div id="departmentList">
      <g:render template="departmentList" model="[departments: departments]" />
    </div>

    <hr />

    <h2>Employees</h2>

    <g:render template="employeeForm" />

    <g:form action="searchEmployee">
      Filter Employee List: 
      <g:select name="departmentid" 
        noSelection="['':'All Departments']" 
        optionValue="name" optionKey="id" 
        from="${departments}" 
        value="${employee?.department?.id}" />
      <g:submitToRemote action="listEmployeesByDepartment" update="employeeList" value="Search"/>
    </g:form>

    <div id="employeeList">
      <g:render template="employeeList" model="[departments: departments, employees: employees]" />
    </div>

  </body>
</html>
