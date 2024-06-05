import ClassForAccess.Person

object OOP extends App {
  class Student {
    var name: String = "Jon"
    var age: Int = 21
    var sex: String = _
  }

  class Worker extends Person {
    override def printInfo(): Unit = {
      println("Worker: ")
      name = "Bob"
      sex = "Male"
      println(s"$name is $sex")
    }
  }

  val person = new Person()
  println(person.sex)

  val worker = new Worker()
  println(worker.sex)

  class Animal() {
    var name: String = _
    var age: Int = _

    println("1: Main constructor is called")

    // Define this
    def this(name: String) {
      this() // 直接调用主构造器
      println("2: Sub constructor 1 is called")
      this.name = name
      println(s"name:$name age:$age")
    }

    def this(name: String, age: Int) {
      this(name)
      println("3: Sub constructor 2 is called")
      this.age = age
      println(s"name:$name age:$age")
    }

    def Animal() = {
      println("Not constructor!")
    }
  }

  val dog = new Animal()
  dog.Animal()

  val cat = new Animal(name="Mimi") // 1: Main constructor is called 2: Sub constructor 1 is called name: Mimi age:0
  val bird = new Animal(name="Alice", age=2)


  abstract class Person2 {

    // 非抽象属性
    val name: String = ""

    // 抽象属性
    var age: Int

    // 非抽象方法
    def eat(): Unit = {
      println("Eating!")
    }

    // 抽象方法
    def sleep(): Unit
  }

  class Student2 extends Person2{
    var age = 18

    override def sleep(): Unit = {
      println("Sleeping!")
    }

    override val name: String = "Student"

    override def eat(): Unit = {
      super.eat() // 使用super关键字来调用父类中的方法
      println("Eat method overrides!")
    }

  }

  val person2: Person2 = new Person2 {
    override var age: Int = 18

    override def sleep(): Unit = {
      println("This is the anonymous class")
    }
  }

  person2.sleep()
  person2.eat()
}
