import scala.collection.mutable

object CollectionCalculation extends App{
  // 求和
  val list = List(1, 2, 3, 4, 5, 6)
  val list2 = List(("a", 5), ("b", 1), ("c", 8), ("d", 2), ("e", 4))
  println(list.sum)

  // 求乘积
  println(list.product)

  // 最大最小值
  println(list.max)
  println(list.min)
  println(list2.maxBy(tuple=>tuple._2)) // 指定元组第二个元素求最值 (c,8)

  // 排序
  println(list2.sortBy(_._2)) // 指定元组第二个元素来排序 List((b,1), (d,2), (e,4), (a,5), (c,8))
  println(list.sortWith((a, b)=>{a < b})) // List(1, 2, 3, 4, 5, 6)
  println(list.sortWith(_ > _)) // List(6, 5, 4, 3, 2, 1)
  println(list2.sortWith(_._2 > _._2)) // List((c,8), (a,5), (e,4), (d,2), (b,1))

  /*
  * ==========Map类==========
  * */
  // 过滤
  // 选取偶数
  val evenList = list.filter(_ % 2 == 0) // filter((elem: Int)=>{elem % 2 == 0})
  println(evenList)
  // 选取奇数
  val oddList = list.filter(_ % 2 == 1)
  println(oddList)

  // map
  // 把集合中的所有数乘2
  println(list.map(_ * 2))
  // 平方
  println(list.map(x => x * x))

  // 扁平化
  val nestedList: List[List[Int]] = List(List(1,2,3), List(4,5), List(6,7,8,9))
  val flatList = nestedList(0) ::: nestedList(1) ::: nestedList(2) // 太麻烦不推荐
  println(flatList)

  val flatList2 = nestedList.flatten
  println(flatList2)

  // 扁平映射
  val strings = List("hello world", "world", "hello scala", "hello xiaomi")
  val splitList: List[Array[String]] = strings.map(_.split(" ")) // map(string => string.split(" "))
  val flattenList = splitList.flatten
  println(flattenList)

  val flattenMapList = strings.flatMap(_.split(" ")) // 以上的简化版实现
  println(flattenMapList)

  // 分组
  // 分成寄偶两组
  val groupMap: Map[Int, List[Int]] = list.groupBy(_%2) // groupBy(data => if (data % 2 == 0) "even" else "odd")
  val groupMap2: Map[String, List[Int]] = list.groupBy(data => if (data % 2 == 0) "even" else "odd")

  println(groupMap)
  println(groupMap2)

  // 给定一组词汇, 按照单词首字母进行分组
  val worldList = List("Duke", "UW", "NYU", "CMU", "Emory", "Columbia", "UMich")
  println(worldList.groupBy(_.charAt(0)))


  /*
   * ==========Reduce类==========
   * */

  /* list: (1, 2, 3, 4, 5, 6) */

  // reduce
  println(list.reduce(_ + _)) // (a:Int, b:Int)=>(a + b) 21
  println(list.reduceLeft(_-_)) // 从左往右减 -19
  println(list.reduceRight(_-_)) // 从右往左减 1-(2-(3-(4-(5-6)))) = -3, a little confusing

  // fold
  println(list.fold(10)(_+_)) // 指定初始值
  println(list.fold(10)(_ + _)) // 10+1+2+3+4+5+6 = 31
  println(list.foldRight(10)(_ - _)) // 1-(2-(3-(4-(5-(6-10))))) = 7, a little confusing



  // 将两个map中的相同键的值相加
  val map1 = Map("a" -> 1, "b" -> 3, "c" -> 6)
  val map2 = mutable.Map("a" -> 6, "b" -> 2, "c" -> 9, "d" -> 3)

  println(map1 ++ map2) // 以map2为准 Map(a -> 6, b -> 2, c -> 9, d -> 3)

  val map3 = map1.foldLeft(map2)((mergedMap, kv)=>{
    val key = kv._1
    val value = kv._2
    mergedMap(key) = mergedMap.getOrElse(key, 0) + value
    mergedMap
  })

  println(map3)


  // Word Count
  val stringList = List(
    "hello",
    "hello world",
    "hello scala",
    "hello spark from scala",
    "hello flink from scala"
  )

  // 对字符串进行切分, 得到一个打散所有单词的列表
  val wordList = stringList.flatMap(_.split(" "))
  println(wordList) // List(hello, hello, world, hello, scala, hello, spark, from, scala, hello, flink, from, scala)

  // 将相同的单词进行分组
  val groupedList: Map[String, List[String]] = wordList.groupBy(word => word)
  println(groupedList) // Map(world -> List(world), flink -> List(flink), spark -> List(spark), scala -> List(scala, scala, scala), from -> List(from, from), hello -> List(hello, hello, hello, hello, hello))

  // 对分组之后的list取长度, 得到每个单词的个数
  val countList = groupedList.map(kv => (kv._1, kv._2.length))
  println(countList) // Map(world -> 1, flink -> 1, spark -> 1, scala -> 3, from -> 2, hello -> 5)

  // 排序
  val sortList: List[(String, Int)] = countList.toList.sortWith(_._2 > _._2).take(3) // 降序排序, 取前三个
  println(sortList) // List((hello,5), (scala,3), (from,2))



}
