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

  class Student3 private(val name: String, val age: Int){
    def printInfo(): Unit = {
      println(s"Name: $name, age: $age, school: ${Student3.school}")
    }
  }

  object Student3 {
    var school: String = "CUG" // 伴生对象里的属性可以当成静态属性来用

    def apply(name: String, age: Int): Student3 = new Student3(name, age)
  }

  val student3 = Student3("Jon", 21)

  student3.printInfo()

  class Singleton private() {
    def doSomething(): Unit = {
      println("Doing something in a more controlled singleton instance...")
    }
  }

  // 饿汉式单例设计模式
  object Singleton {
    private val instance = new Singleton()

    def getInstance: Singleton = instance
  }

  // 懒汉式单例设计模式
//  object Singleton {
//    private var instance: Singleton = _
//
//    def getInstance(): Singleton = {
//      if (instance == null){
//        instance = new Singleton()
//      }
//      instance
//    }
//
//  }

  val singleton = Singleton.getInstance
  singleton.doSomething() // 输出 "Doing something in a more controlled singleton instance..."

  val singleton2 = Singleton.getInstance
  singleton2.doSomething()

  println(singleton)
  println(singleton2)

  class Person4 {
    val name: String = "person"
    var age: Int = 18
    def sayHello():Unit = {
      println("Hello from: " + name)
    }
  }

  // 定义一个特质
  trait Young {
    // 定义一个抽象属性
    var age: Int
    // 定义一个非抽象属性
    val name: String = "Young"

    // 声明非抽象方法
    def play(): Unit = {
      println(s"Young people $name is playing")
    }
    // 声明抽象方法
    def dating(): Unit
  }

  trait Knowledge {
    var amount: Int = 0

    def increase(): Unit
  }

  trait Talent {
    def singing(): Unit
    def dancing(): Unit
  }

  class Student4 extends Person4 with Young with Knowledge {
    // 重写冲突的属性name, 不然会报错
    override val name: String = "Jon"
    // 实现抽象方法
    override def dating(): Unit = {
      println(s"Student $name is dating")
    }

    override def increase(): Unit = {
      amount += 1
      println(s"$name's current amount is $amount")
    }
    // 定义类特有的方法
    def study(): Unit = {
      println(s"$name is studying")
    }

    //重写父类方法
    override def sayHello(): Unit = {
      super.sayHello()
      println(s"Hello from student $name")
    }
  }

  val student4 = new Student4
  student4.sayHello()
  student4.study()
  student4.play()
  student4.dating()

  student4.increase()
  student4.increase()
  student4.increase()

  val studentWithTalent = new Student4 with Talent {
    override def singing(): Unit = {
      println(s"$name is singing")
    }

    override def dancing(): Unit = {
      println(s"$name is dancing")
    }
  }

  studentWithTalent.singing()

  class User(val name: String, val password: String)

  trait UserDao {
    _: User =>

    def insert(): Unit = {
      println(s"Insert into database: ${this.name}")
    }
  }

  class RegisterUser(name:String, password:String) extends User(name, password) with UserDao

  val user = new RegisterUser(name="Jon", password="123456")

  user.insert()




}
