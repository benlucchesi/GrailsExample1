package grailsexample1

class HumanResourcesService {

  // department functions
    def listDepartments() {
      Department.list(sort: 'name')
    }

    def retrieveDepartment( Long id ){
      Department.get(id)
    }

    def saveDepartment(Department department){
      department.save(true) 
    }

    def updateDepartment(Department department){
      department.save(true) 
    }

    def deleteDepartment(Department department){

      department.employees.each{ employee ->
        department.removeFromEmployees(employee) // removeFrom* is a dynamic method
      }

      department.delete()
      department
    }

  // employee functions

    def saveEmployee(Employee employee, Department department){
      employee.department = department
      employee.save(true)
      employee
    }

    def retrieveEmployee(Long id){
      Employee.get(id)
    }

    def listEmployees(){
      Employee.list(sort: 'id')
    }

    def deleteEmployee(Employee employee){
      employee.delete()
      employee
    }

    def listEmployeesForDepartment( Department d ){
      Employee.findAllByDepartment(d)
    }
}
