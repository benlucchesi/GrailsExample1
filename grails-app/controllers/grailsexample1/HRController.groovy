package grailsexample1

class HRController {

    def humanResourcesService // dependency injected

    // index page is rendered when no action is specified.
    // - return a view-model containing the list of departments and list of employees
    def index() {
      [ departments: humanResourcesService.listDepartments(), employees: humanResourcesService.listEmployees() ]
    }

    // saveDepartment
    // - ajax callback that saves the department using the human resources service
    // - renders a template in response to up date the list of departments
    def saveDepartment(){
      Department department = new Department()
      department.properties = params // databinding to params from form
      humanResourcesService.saveDepartment(department)
      if( department.errors.hasErrors() ){
        flash.departmentErrors = department.errors
        department.discard()
      }

      render template: "departmentList", model: [departments: humanResourcesService.listDepartments()]
    }

    // deleteDepartment
    // - ajax callback that deletes a department using the human resources service
    // - renders the department list in response
    def deleteDepartment(){
      def department = humanResourcesService.retrieveDepartment(params.id.toLong())   
      department = humanResourcesService.deleteDepartment(department)
      if( department.errors.hasErrors() )
        flash.departmentErrors = department.errors
      render template: "departmentList", model: [departments: humanResourcesService.listDepartments()]
    }

    // saveEmployee
    // - ajax callback that saves an employee using the human resources service
    // - renders the employee list template in response
    def saveEmployee(){
      def employee = new Employee()
      employee.properties = params
      def department = humanResourcesService.retrieveDepartment(params.departmentid.toLong())
      employee = humanResourcesService.saveEmployee(employee,department)
      if( employee.errors.hasErrors() )
        flash.employeeErrors= employee.errors

      render template: "employeeList", 
             model: [departments: humanResourcesService.listDepartments(), 
                     employees: humanResourcesService.listEmployees()]
    }

    // deleteEmployee callback
    // - ajax callback that deletes the employee record using the human resources service
    // - renders the employeeList template in response
    // - 
    def deleteEmployee(){
      def employee = humanResourcesService.retrieveEmployee(params.id.toLong())
      humanResourcesService.deleteEmployee(employee)
      if( employee.errors.hasErrors() )
        flash.employeeErrors= employee.errors

      render template: "employeeList", 
             model: [ departments: humanResourcesService.listDepartments(), 
                      employees: humanResourcesService.listEmployees() ]
    }

    def listEmployeesByDepartment(){
      def employees
      if( params.departmentid ){
          def department = humanResourcesService.retrieveDepartment(params.departmentid.toLong())
          println "listing ${department.name} employees"
          employees = humanResourcesService.listEmployeesForDepartment(department)
      }
      else
      {
          println "listing all employees"
         employees = humanResourcesService.listEmployees()  
      }

      render template: "employeeList", model: [departments: humanResourcesService.listDepartments(), employees: employees]
    }
}
