import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer} // 引入可变数组

object Collections extends App{

  /*
  * ==========Immutable Array==========
  * */

  // 创建数组
  val arr = new Array[Int](5)
  // 使用伴生对象中的apply方法创建
  val arr2 = Array.apply(1, 2, 3, 4, 5)
  println(arr.mkString(", ")) // 0, 0, 0, 0, 0
  println(arr2.mkString(", ")) // 1, 2, 3, 4, 5
  println(arr2(0)) // 访问具体元素
  arr(0) = 20 // 赋值
  println(arr.mkString(", "))

  // 普通for循环
  for (i <- arr.indices) {
    println(arr2(i))
  }

  // 增强for循环
  for (elem <- arr2) {
    println(elem)
  }

  // 迭代器
  val iter = arr2.iterator
  while (iter.hasNext) {
    println(iter.next())
  }

  // foreach方法
  arr2.foreach((elem: Int)=>(println(elem)))
  arr2.foreach(println)

  val newArr = arr2.:+(23)
  println(newArr.mkString(", "))

  val newArr2 = newArr.+:(44)
  println(newArr2.mkString(", "))

  val newArr3 = newArr2 :+ 50

  val newArr4 = 100 +: 100 +: newArr3 :+ 100
  println(newArr4.mkString(", "))


  /*
    * ==========Mutable Array==========
    * */

  // 创建可变数组
  val arr5: ArrayBuffer[Int] = new ArrayBuffer[Int]()
  // 使用伴生对象中的apply方法创建
  val arr6 = ArrayBuffer(2, 2, 2, 2)
  println(arr5.mkString(", "))
  println(arr6)
  println(arr6.mkString(", "))
  //println(arr5(0)) // 数组越界
  println(arr6(3)) // 2
  arr6(0) = 99
  println(arr6) // ArrayBuffer(99, 2, 2, 2)

  // 向末尾添加元素
  arr5 += 19
  println(arr5) // ArrayBuffer(19)

  // 向起始添加元素
  77 +=: arr5 // 冒号朝着对象
  println(arr5)

  // 阳间做法
  arr5.append(88)
  arr5.prepend(88)
  arr5.insert(1, 10000, 10)
  println(arr5)

  arr5.appendAll(arr2)
  arr5.prependAll(arr2)
  arr5.insertAll(0, arr2)
  println(arr5)

  // 删除元素
  arr5.remove(0)
  arr5.remove(0, 4)
  println(arr5)

  arr5 -= 10000
  println(arr5)

  // 多维数组
  val twoDimArray = Array.ofDim[Int](2, 3) // 两行三列

  twoDimArray(0)(0) = 9// 第一行第一列


  for(i <- 0 until twoDimArray.length; j <- 0 until twoDimArray(i).length) {
    print(twoDimArray(i)(j) + "\t")
    if (j == twoDimArray(i).length - 1) println()
  }

  for (i <- twoDimArray.indices; j <- twoDimArray(i).indices) {
    print(twoDimArray(i)(j) + "\t")
    if (j == twoDimArray(i).length-1) println()
  }

  // foreach方法
  twoDimArray.foreach(line => line.foreach(println))
  twoDimArray.foreach(_.foreach(println))


  /*
  * ==========Immutable List==========
  * */

  // 创建列表
  val list1 = List(1, 2, 3, 4)
  println(list1)

  // 遍历列表
  // list1(0) = 12 不能通过直接访问下标修改元素
  list1.foreach(println)

  // 添加元素
  val list2 = list1 :+ 22
  val list3 = 22 +: list2
  println(list2)
  println(list3)

  // ::号添加元素
  val list4 = list3.::(9)
  println(list4)

  val list5 = Nil.::(99)
  println(list5)

  val list6 = 23 :: 23 :: 23 :: 23 :: Nil

  val list7 = list6 ::: list5
  val list8 = list6 ++ list5

  println(list7)
  println(list8)
  println(list6)


  /*
  * ==========Mutable List==========
  * */

  // 和ArrayBuffer完全一样
  val list9 = new ListBuffer[Int]()
  val list10 = ListBuffer(4, 4, 4, 4)

  // 阳间做法
  list9.append(88)
  list10.prepend(88)
  list10.insert(1, 10000, 10) // 在指定位置添加多个元素

  list10.appendAll(list9)
  list10.prependAll(list9)
  list10.insertAll(0, list9) // 在指定位置处添加一个数组

  println(list9)
  println(list10)


  /*
    * ==========Immutable Set==========
    * */
  val set1 = Set(12, 12, 34, 34, 54)
  println(set1)

  val set2 = set1.+(20)
  val set3 = set2 + 40

  val set4 = set2 ++ set3
  println(set2)
  println(set3)
  println(set4)


  /*
    * ==========Immutable Map==========
    * */

  // 创建map
  val map1 = Map("a" -> 1, "b" -> 2, "c" -> 3)
  println(map1.getClass)

  // 遍历
  map1.foreach(println)
  map1.foreach((kv: (String, Int)) => println(kv))

  // 取map中所有的key/value
  for (key <- map1.keys) {
    println(s"$key --> ${map1.get(key)}")
  }
  println(map1.get("a").get)
  println(map1("a"))
  println(map1.getOrElse("d", 0)) // 没有该键时, 返回一个指定的值


  /*
    * ==========Mutable Map==========
    * */
  val map2 = mutable.Map("a" -> 1, "b" -> 2, "c" -> 3)
  println(map2.getClass)

  // 可变数组, 可向其中添加或删除元素
  map2.remove("a")
  map2.put("d", 4)
  println(map2)

  map2 += (("e", 5)) // two () represent tuple to avoid ambiguity
  map2 -= "c"
  println(map2) // just need key

  // merge Map
  map2 ++= Map("f" -> 10, "g" -> 20, "h" -> 30) // add and will override
  println(map2)


  /*
    * ==========Tuple=========
    * */

  val tuple: (String, Int, Char) = ("Jon", 2, 'a')
  println(tuple._1) // "Jon"
  println(tuple.productElement(0)) // "Jon"

  // 遍历元组数据
  for(elem <- tuple.productIterator) {
    println(elem)
  }

  // Option
  val myMap: Map[String, String] = Map("key1" -> "value")
  val value1: Option[String] = myMap.get("key1")
  val value2: Option[String] = myMap.get("key2")

  println(value1) // Some("value1")
  println(value2) // None

  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }

  val sites = Map("a" -> "This is a", "b" -> "This is b")

  println(show(sites.get("a"))) // This is a
  println(show(sites.get("c"))) // ?

  val a: Option[Int] = Some(5)
  val b: Option[Int] = None

  println("a.getOrElse(0): " + a.getOrElse(0))
  println("b.getOrElse(10): " + b.getOrElse(10))

  val c: Option[Int] = Some(5)
  val d: Option[Int] = None

  println("c.isEmpty: " + c.isEmpty)
  println("d.isEmpty: " + d.isEmpty)
}
