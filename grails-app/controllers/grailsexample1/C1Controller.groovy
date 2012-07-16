package grailsexample1

class C1Controller {

    String sampleData

    def index() {
      // explicitly render some content to the screen.
      render("I'm an example of rendering plain text to the screen. I have not view.");
    }

    // example1: controller class is used as model
    //   nothing is returned from this method, so grails uses the controller class
    //   instance as the model
    def example1(){
      // assign something to sampleData class variable
      sampleData = "example1() was called."
      return null
    }

    // example2: explicitly define the model to use in the view
    //  Returning a map causes grails to use it as the model when rendering
    //  the view.
    //  By convention, grails uses views/c1/example2.gsp
    def example2(){
      List firstList = ["item 1", "item 2", "item 3", "item 4", "item 5"]
      List secondList = ["item a", "item b", "item c", "item d", "item e"]
      Map dataToRender = [list1: firstList, list2: secondList]
      return( dataToRender );
    }
   
    // example3: explicity define the model and view to use
    //  The render method allows you to assign the view to render
    //  with this method
    def example3(){
      def firstList = ["ben", "dan", "javier", "ryan", "pete"]
      def secondList = ["umid", "shreesh", "javier", "scott"]
      def dataToRender = [list1: firstList, list2: secondList]
      render( view: 'example2', model: dataToRender );
    }

    // example4: inspect query string parameters
    // query string parameters are assigned to the params object which can be accessed as a hash
    def example4(){
      println "example4() params: ${params}"
    }
   
    // example5: command object
    // grails can automatically databind to a class by assigning parameters to an object
    // defined in the method parameter list
    def example5(Person p){
      println p
      [person: p]  // groovy by default returns the last variable in the statement
    }

    // example6: form submit with command object
    // Form parameters are data bound to the person command object.
    // The person object is then passed back to the view which is then rendered in a template
    def example6(Person p){
      println "Params: ${params}"
      println "Person: ${p}"
      
      [person: p]  // groovy by default returns the last variable in the statement
    }
}

// Command object - used to databind controller input parameters

@grails.validation.Validateable
class Person{
  String firstName
  String lastName
  int id = 0  

  String toString(){
    return( "${firstName?:''} ${lastName?:''}" );
  }

  // assigning a closure to static variable
  static constraints = {
    firstName([blank:false, matches: '[A-Za-z]+']); // most syntax in groovy is optional. both of these method calls are equivelent.
    lastName blank:false, validator: { if( it ==~ '[A-Za-z]+' ) return true }
  }
}
