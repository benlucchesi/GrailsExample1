<form>
  <label>Department Name</label>
  <g:textField name="name" />
  <label>Description</label> 
  <g:textField name="description" />
  <g:submitToRemote action="saveDepartment" update="departmentList" value="Save"/>
</form>
