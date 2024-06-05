object ClassForAccess extends App{

  class Person {
    private var ID: String = "510131"
    protected var name: String = "Jon"
    var sex: String = "Male"

    def printInfo(): Unit = {
      println(s"$name 's id is $ID")
    }
  }

}
