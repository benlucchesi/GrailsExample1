package grailsexample1

class Employee {

    static constraints = {
      firstName blank:false
      lastName blank:false
      emailAddress blank:false, unique: true
    }

    static mapping = {
      emailAddress index: "email_address_idx", unique: true // index the email address column
    }

    String emailAddress
    String firstName
    String lastName
   
    static belongsTo = [ department:Department ]

    // auto timestamping is enabled by default
    // - these two fields are used by auto timestamping by default
    Date dateCreated
    Date lastUpdated
}
