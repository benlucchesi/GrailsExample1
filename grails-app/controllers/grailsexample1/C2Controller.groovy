package grailsexample1
import grails.converters.*

class C2Controller {

    //NOTES:
    // - updated URLMapping to map /person URL /w verbs to actions
    // - update Config to accept

    static scope = "session"

    def peopleStore = [new Person(id: 1, firstName: 'ben', lastName: 'lucchesi'), 
                       new Person(id: 2, firstName: 'matt', lastName: 'lucchesi') ]

    def index() {
      render "nothing to see here. move along"
    }

    def addPerson(){

      // create new person
      def p = new Person()
      p.id = peopleStore.size()

      // parse the request
      request.withFormat {
        json{
          println "received json request"
          p.firstName = request.JSON.firstName
          p.lastName = request.JSON.lastName
          peopleStore << p
        }
        xml{
          p.firstName = request.XML.@firstName
          p.lastName = request.XML.@lastName
          peopleStore << p
        }
      }

      def responseMessage = "person added"
      def status = 201

      // produce the output
      withFormat{
        html{
          render( text: responseMessage, status: status )
        }
        xml{
          render( contentType: 'text/xml', status: status){
              message("person added")
          }
        }
        json{
          render( contentType: 'text/json', status: 201 ){
              message = "person added"
          }
        }
      }
    }

    def updatePerson(){
       
      // lookup the person
      def p = peopleStore.find{ it.id == params.id?.toLong() } 
      println request.format
      if( p ){
        request.withFormat{
          xml{
            println "withFormat.xml"
            p.firstName = request.XML.@firstName
            p.lastName = request.XML.@lastName
          }
          json{
            println "withFormat.json"
            p.firstName = request.JSON.firstName 
            p.lastName = request.JSON.lastName
          }
        }
      }

      println "request: ${request}"

      def responseMessage = p?'person updated':'person not found'
      def status = p?200:404

      withFormat{
        html{
          render text: responseMessage, status: status
        }
        xml{
            render( contentType: 'text/xml', status: status ){
              message(responseMessage)
            }
        }
        json{
            render( contentType: 'text/json', status: status ){
              message = responseMessage
            }
        }
      }
    }

    def showPerson(){

      def listPeople = true
      def p = null
      def status = 200
      def responseMessage = ""

      if( params.id ){
        listPeople = false
        p = peopleStore.find{ it.id == params.id?.toLong() } 
        status = p?200:404
        responseMessage = p?'':'person not found'
      }
      else{
        listPeople = true 
        status = 200
      }
  
      withFormat{

        html{
          if( listPeople )
            render view: "listPeople", model: [people: peopleStore], status: status
          else if( p )
            render text: "${p.id} ${p}", status: status
          else
            render text: responseMessage , status: status
        }

        xml{

          render( contentType: 'text/xml', status: status ){
            if( listPeople ){
              people{
                peopleStore.each{
                  person firstName: it.firstName, lastName: it.lastName, id: it.id
                }
              }
            }
            else if( p )
              person firstName: p.firstName, lastName: p.lastName, id: p.id 
            else
              message(responseMessage)
          }

        }

        json{
          render( contentType: 'text/json', status: status ){
            if( listPeople ){
              people = array{
                peopleStore.each{
                  element(firstName: it.firstName, lastName: it.lastName, id: it.id)
                }
              }
            }
            else if( p ){
              firstName = p.firstName
              lastName = p.lastName
              id = p.id
            }
            else{
              message = responseMessage
            }
          }
        }
      }
    }

    def deletePerson(){
      
      def modified = peopleStore.removeAll{ it.id == params.id?.toLong() }
      def responseMessage = modified?"deleted person":"person doesn't exist"
      def status = modified?200:404

      withFormat{
        html{
          render text: responseMessage, status: status
        }
        json{
          render( contentType: 'text/json', status: status ){
            message = responseMessage          
          }
        }
        xml{
            render( contentType: 'text/xml', status: status ){
              message(responseMessage)
            }
        }
      }
    }
}
