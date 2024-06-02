object BasicSyntax extends App{

  /*
  *  ==========Data Type==========
  * */

  // Unit type, like void in C, returns nothing
  def myUnitFunction(): Unit = {
    println("I like unit type")
  }
  println(myUnitFunction()) // The value of Unit type is ()


  // Nothing type
  def error(message: String): Nothing = {
    throw new RuntimeException(message)
  }


  // Null type
  class Student(val name: String, val age: Int) {
    // 方法来打印学生信息
    def printInfo(): Unit = {
      println(s"Name: $name, Age: $age")
    }
  }
  var student: Student = new Student("Jon", 20)
  student = null

  /*
  *  ==========BasicSyntax==========
  * */

  // constant value
  val myConstVal: Int = 20


  // variable value
  var myVariableVal: Double = 2.0


  // String interpolation
  var str = s"The answer is $myVariableVal"
  println(str) // The answer is 2.0


  // Code blocks
  val myCodeBlock = {
    val num = 1
    num + 1 // The return value of the code block is the last expression of the block

  }
  println(myCodeBlock) // 2


  // Everything in scala is expression, which means structures that can be reduced to a value
  val ifStatement = if (myConstVal > 0) 12 else 20
  println(ifStatement)
  val anExpression = 50 + 50
  println(anExpression)


  // Anonymous function
  (x: Int) => x + 1


  // Arrow function
  val addByOne = (x: Int) => x + 1
  println(addByOne(1))
  val plusNum = (x: Int, y: Int) => x + y
  println(plusNum(2, 3))
  val getTheAnswer = () => s"The answer is $myVariableVal" // String interpolation
  println(getTheAnswer())


  // Using def
  def myFunction(x: Int, y: String): String = {
    y + " " + x
  }
  println(myFunction(10, "The answer is"))


  // Receive multiple argument list
  def addThenMultiply(x: Int, y: Int)(multiplier: Int): Int = {
    (x + y) * multiplier
  }
  println(addThenMultiply(1, 2)(3)) // 9


  // Recursive
  def recursiveFunction(x: Int): Int = {
    if (x <= 1) 1
    else x * recursiveFunction(x - 1)
  }
  println(recursiveFunction(4))

  /*
  *  ==========For Loop==========
  * */

  // Include 0 and 9
  for (i <- 0 to 9) {
    println(i + "Hello World") // 0-9
  }


  // Not include 0 and 9
  for (i <- 0 until 9) {
    println(i + "Hello World") // 1-8
  }


  // Collection iteration
  for (i <- Array(12, 34, 53)) {
    println(i)
  }
  for (i <- List(12, 34, 53)) {
    println(i)
  }
  for (i <- Set(12, 34, 53)) {
    println(i)
  }


  // Loop guard
  for (i <- 0 to 3 if i != 2) {
    println(i + "Hello World") // 0 1 3 , skip 2
  }


  // Loop step
  for (i <- 0 to 9 by 2) {
    println(i + "Hello World") // 0 2 4 6 8
  }

  for (i <- 9 to 0 by -2) {
    println(i + "Hello World") // 9 7 5 3 1
  }

  for (i <- BigDecimal(0.0) to BigDecimal(2.0) by BigDecimal(0.5)) {
    println(i + "Hello World") // 0.0 0.5 1.0 1.5 2.0
  }


  // Nested loop
  // Normal
  for (i <- 1 to 3) {
    for (j <- 1 to 3) {
      println("i=" + i + "j=" + j)
    }
  }
  // Scala
  for (i <- 1 to 3; j <- 1 to 4) {
    println("i=" + i + "j=" + j)
  }
  /*
  打印九九乘法表
  */
  for (i <- 1 to 9; j <- 1 to i) {
    print(s"$i * $j = ${i * j} \t")
    if (j == i) println() // Line breaking
  }


  // Import variable
  for (i <- 1 to 3; j = i - 1; k = j + i) {
    println("i=" + i + "j=" + j + "k=" + k)
  }


  // Return value of loop
  val res:Unit = for (i <- 0 to 9){
    println(i)
  }
  println(res) // return type:Unit ()
  // Use yield
  val res2 = for (i <- 0 to 9) yield i
  println(res2) // Vector(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
  val res3 = for (i <- 0 to 9) yield i * i
  println(res3) // Vector(0, 1, 4, 9, 16, 25, 36, 49, 64, 81)


  // Use Breaks to interrupt loop
  import scala.util.control.Breaks._
  breakable(
    for (i <- 0 to 9) {
      if (i == 3)
        break()
      println(i)
    }
  )

  /*
    *  ==========While Loop==========
    * */
  // while
  var a = 10
  while (a >= 1) {
    println(a)
    a -= 1
  }

  // do while
  var b = 0
  do {
    println(b)
    b -= 1
  } while (b > 0)
}

