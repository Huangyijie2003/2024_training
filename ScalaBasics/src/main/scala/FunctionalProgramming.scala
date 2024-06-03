object FunctionalProgramming extends App{

  /*
    *  ==========High Level Function==========
    * */

  // Function as expression value
  def addOne(x: Int): Int = {
    println("f is called.")
    x + 1
  }
  val f1 = addOne _
  val f2: Int => Int = addOne
  println(f1, f2) //com.scala.FunctionalProgramming$$$Lambda$21/1456208737@d041cf (是f1的引用地址)


  // Function as argument
  def dualEval(op: (Int, Int) => Int, a: Int, b: Int): Int = {
    op(a, b)
  }

  def Add(a: Int, b: Int): Int = {
    a + b
  }

  def Minus(a: Int, b: Int): Int = {
    a - b
  }

  println(dualEval(Add, 2, 3)) // 5
  println(dualEval(Minus, 2, 3)) // -1


  // Function as return value
  def f3(x: Int): (Int, Int) => Unit = {
    def f4(a: Int, b: Int): Unit = {
      val m = a + b + x
      println(m)
    }
    f4
  }

  println(f3(100)) // com.scala.FunctionalProgramming$$$Lambda$25/240650537@1cd072a9
  val f5 = f3(100) // 再次调用
  f5(1, 2) // 103


  // Examples
  val arr: Array[Int] = Array(1, 2, 3, 4, 5)

  def addTwo(a: Int): Int = {
    a + 2
  }

  def arrayOperation(array: Array[Int], op: Int => Int): Array[Int] = {
    for (elem <- array) yield op(elem)
  }

  val newArr: Array[Int] = arrayOperation(arr, addTwo)

  println(newArr.mkString(","))


  /*
    *  ==========Closure And Currying==========
    * */

  // Closure
  def add(a:Int, b:Int): Int = {
    a + b
  }

  def addByTen(a:Int): Int = {
    a + 10
  }

  def addByAny(a:Int): Int=>Int = {
    def addInner(b:Int): Int = {
      a + b
    }
    addInner
  }

  val addTen = addByAny(10)
  val addFive = addByAny(5)

  println(addTen(29)) // 39
  println(addFive(6)) // 11

  // Lambda expression
  def addByAny2(a: Int): Int => Int = a + _

  val addThree = addByAny2(3)

  println(addThree(7)) // 10


  // Currying
  def addCurrying(a:Int)(b:Int): Int = {
    a + b
  }
  println(addCurrying(30)(30)) // 60


  // Recursive
  def factorial(n: Int): Int = {
    if (n == 0)
      return 1
    factorial(n - 1) * n
  }

  // Tail recursive implementation of factorial
  def tailFact(n: Int): Int = {
    @annotation.tailrec
    def loop(n: Int, curRes: Int): Int = {
      if (n == 0)
        return curRes
      loop(n - 1, curRes * n)
    }
    loop(n, 1)
  }

  println(tailFact(5))

  // Control abstraction

  // Pass value
  def f6(a: Int): Unit = {
    println("a:" + a)
    println("a:" + a)
  }
  f6(23)

  def f7(): Int = {
    println("f7 called")
    12
  }

  f6(f7())

  // Pass name
  def f8(a: =>Int): Unit = {
    println("a:" + a)
    println("a:" + a)
  }

  f8(f7())

  f8({
    println("This is a code block")
    9
  })

  // My while loop
  def myWhile(condition: =>Boolean): (=>Unit)=>Unit = {
    def doLoop(op: =>Unit): Unit = {
      if (condition){
        op
        myWhile(condition)(op)
      }
    }
    doLoop _
  }

  var n = 10
  myWhile(n >= 1){
    println(n)
    n -= 1
  }

  def myWhile2(condition: => Boolean): (=> Unit) => Unit = {
    op => {
      if (condition) {
        op
        myWhile2(condition)(op)
      }
    }
  }

  var m = 10
  myWhile2(m >= 1) {
    println(m)
    m -= 1
  }

  def myWhile3(condition: => Boolean)(op: =>Unit): Unit = {
    if (condition) {
      op
      myWhile2(condition)(op)
    }
  }

  var k = 10
  myWhile3(k >= 1) {
    println(k)
    k -= 1
  }


  // Lazy
  lazy val result = sum(13, 17)

  println("1. function called")
  println("3. result = " + result)

  def sum(a: Int, b: Int): Int = {
    println("2. sum called")
    a + b
  }

}
