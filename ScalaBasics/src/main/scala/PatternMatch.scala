object PatternMatchBasics extends App{
  val x: Int = 2
  val y: String = x match {
    case 1 => "one"
    case 2 => "two"
    case 3 => "three"
    case _ => "other"
  }
  println(y)

  // 用模式匹配实现二元运算
  val a = 25
  val b = 12

  def matchDual(op: Char) = op match {
    case '+' => a + b
    case '-' => a - b
    case '*' => a * b
    case '/' => a / b
    case '%' => a % b
    case _ => "Invalid"
  }
  println(matchDual('+'))


  // 模式守卫
  // 求一个整数的绝对值
  def abs(num: Int): Int = {
    num match {
      case i if i >= 0 => i
      case i if i < 0 => -i
    }
  }

  println(abs(-45))


  // 模式匹配类型
  // 匹配类型
  def describeType(x: Any) = x match {
    case i: Int => "Int " + i
    case s: String => "String " + s
    case list: List[String] => "List " + list
    case array: Array[Int] => "Array[Int] " + array
    case a => "Something else " + a
  }

  println(describeType(20)) // match
  println(describeType("hello")) // match
  println(describeType(List("hi", "hello"))) // match
  println(describeType(List(20, 30))) // match
  println(describeType(Array(10, 20))) // match
  println(describeType(Array("hello", "yes"))) // not match
  println(describeType((10, 20))) // not match

  println("=========================================")

  // 匹配数组
  for (arr <- List(
    Array(0),
    Array(1, 0),
    Array(1, 1, 0),
    Array(10, 2, 7, 5),
    Array("hello", 20, 50)
  )) {
    val result = arr match {
      case Array(0) => "0"
      case Array(1, 0) => "Array(1, 0)"
      case Array(x: Int, y: Int) => s"Array($x, $y)" // Array of two elements
      case Array(0, _*) => s"an array begin with 0"
      case Array(_, 1, _) => s"an array with three elements, no.2 is 1"
      case Array(_: String, _*) => s"array that first element is a string"
      case _ => "something else"
    }
    println(result)

  }

  // :: 号进行匹配
  val list = List(1, 2, 5, 7, 24)

  list match {
    case first :: second :: rest => println(s"first: $first, second: $second, rest: $rest")
    case _ => println("something else")
  } // first: 1, second: 2, rest: List(5, 7, 24)


  // 匹配元组
  for (tuple <- List(
    (0,1),
    (0,0),
    ("hello", true),
    (0.5, 6),
    (1, 23, 4),
    ("hello world", 1, true)
  )) {
    val result = tuple match {
      case (a, b) => a + ", " + b
      case (0, _) => "(0, _)"
      case (a, 1, _) => "(a,1,_)" + a
      case _ => "something else"
    }
    println(result)
  }
}
