<form>
  <label>First Name</label>
  <g:textField name="firstName" value="${employee?.firstName}" /> <br/>

  <label>Last Name</label> 
  <g:textField name="lastName" value="${employee?.lastName}"/> <br/>

  <label>Email Address</label> 
  <g:textField name="emailAddress" value="${employee?.emailAddress}"/> <br/>

  <label>Department</label>
  <g:select name="departmentid" noSelection="['':'-- chose one --']" optionValue="name" optionKey="id" from="${departments}" value="${employee?.department?.id}" />

  <g:submitToRemote action="saveEmployee" update="employeeList" value="Save"/>
</form>
