package grailsexample1

class Department {

    static constraints = {
      name unique:true
    }

    String name
    String description

    static hasMany = [employees:Employee]
}
