Github仓库地址:https://github.com/Huangyijie2003/2024_training

# Scala Basics

## Scala Type Hierarchy and Data Type

![img](https://h1mrfl4covt.feishu.cn/space/api/box/stream/download/asynccode/?code=NjgxMzdmYjE3OGNjNWQyZjlmMDUwMWY5YmJjZjAwMTVfSjRPRmdOUld0ejVDUXpKUzZLMlBReVNKZ29FWXg1Wk1fVG9rZW46Wk41MWJnUHp5b3lHdVJ4OVlVU2NSdEFQbk9mXzE3MTc4MzQ1OTg6MTcxNzgzODE5OF9WNA)

Any是scala中所有类型的父型, scala中一切数据都是对象, 不管是值类型还是引用类型. 

### AnyVal

#### Type casting

AnyVal包含基础的数据类型, Scala遵守低精度的类型向高精度的类型进行自动转换(隐式转换).

![img](https://h1mrfl4covt.feishu.cn/space/api/box/stream/download/asynccode/?code=YzA3ZjE2NDQ4YWE0ZDQxNTk2MzVhZmZlNzQ1ZTMzZjJfbzg0RXJPTjJ0STdSV1FxY1R6eDNuR1Nnd0pNNHRZa0FfVG9rZW46VkY2NWJoWGtmb0g0NGF4OHc2TmN2OGh3bkllXzE3MTc4MzQ1OTg6MTcxNzgzODE5OF9WNA)

上图为类型转换时的默认规则,注意Char会直接转成Int类型.

| Byte   | 1 字节 |
| ------ | ------ |
| Short  | 2 字节 |
| Int    | 4 字节 |
| Long   | 8 字节 |
| Float  | 4 字节 |
| Double | 8 字节 |
| Char   | 2 字节 |

#### Unit

`Unit` (空值)相当于其他编程语言中Void. `Unit` is a value type which carries no meaningful information.

```Scala
// Unit type, like void in C, returns nothing
  def myUnitFunction(): Unit = {
    println("I like unit type")
  }
  println(myUnitFunction()) // The value of Unit type is ()
}
```

#### Nothing

`Nothing` 是所有类型的子类型(包括`Null`), 是 Scala 类型系统中最底层的类型.

`Nothing`通常用来表示没有正常值的方法返回类型, 例如抛出异常的方法.

因为 `Nothing` 是所有类型的子类型, 所以它可以被用在需要返回任何类型的地方, 这使得它非常有用. 例如, 一个方法总是抛出异常, 可以定义为返回 `Nothing` 类型.

```Scala
def error(message: String): Nothing = {
  throw new RuntimeException(message)
}
```

### AnyRef

AnyRef包含三个子类:List(不可变的有序集合), Option(容器类型)和YourClass(用户自定义的类). 它类似于Java的Object类, 我们可以用new关键字实例化引用类. 

#### Null

`Null`是所有引用类型的子类, 它有且只有一个实例就是`null`(空引用). 

`Unit`表示空值, `Null`表示空引用.

```Scala
var student: Student = new Student("Jon", 20)
student = null
```

## 基本语法

**Scala里面一切皆是对象.**

### Define Values

常量

```Scala
// 1. constant value
val myConstVal: Int = 20
```

变量

```Scala
// 2. variable value
var myVariableVal: Double = 2.0
```

### 模版字符串

使用规则为在普通字符串前加`s`, 里面用`$`传值.

`$`也可以传入表达式, 用`{}`包住即可. 例如`s"The answer is `**`${1\*2}`**`"`.

```Scala
val myVariableVal = 10
var str = s"The answer is $myVariableVal" // The answer is 10
```

### Code blocks

代码块的作用:

**定义****作用域****:** 代码块内定义的变量只在该代码块内有效.

**计算表达式的值:** 代码块的最后一个表达式的值是整个代码块的值.

```Scala
/* Code blocks */
val myCodeBlock = {
  var num = 1
  num + 1 // The return value of the code block is the last expression of the block
}
println(myCodeBlock) // 2
```

### Everything is Expression

```Scala
val ifStatement = if (myConstVal > 0) 12 else 20
println(ifStatement)
val anExpression = 50 + 50
println(anExpression)
```

Everything in scala is expression, which means structures that can be reduced to a value.

### Define Function

#### 匿名函数

```Scala
// 1. Anonymous function
(x: Int) => x + 1
```

#### 箭头函数

```Scala
// 2. Arrow function
val addOne = (x: Int) => x + 1
println(addOne(1))
val plusNum = (x: Int, y: Int) => x + y
println(plusNum(2, 3))
val getTheAnswer = () => s"The answer is $myVariableVal" // String interpolation
println(getTheAnswer())
```

#### 使用def定义函数

```Scala
// 3. Using def
def myFunction(x: Int, y: String): String = {
  y + " " + x
}
println(myFunction(10, "The answer is"))
```

#### 传入多参数列表

```Scala
// Receive multiple argument list
def addThenMultiply(x: Int, y: Int)(multiplier: Int): Int = {
  (x + y) * multiplier
}
println(addThenMultiply(1, 2)(3)) // 9
```

#### 递归

很重要, 在函数式编程语言中, 用的都是递归. 

```Scala
// Recursive
def recursiveFunction(x: Int): Int = {
  if(x <= 1) 1
  else x * recursiveFunction(x - 1)
}
println(recursiveFunction(4))
```

#### 返回值为Unit

```Scala
// Unit type, like void in C, returns ()
  def myUnitFunction(): Unit = {
    println("I like unit type")
  }
  println(myUnitFunction()) // The value of Unit type is ()
}
```

## For Loop

### 范围遍历(常见)

`<-`和`to`是一个方法调用, 使i从0到9依次取值, `(i <- 0 to 9)`也可以写成`(i <- 0.to(9))`.

```Scala
// include 0 and 9
for (i <- 0 to 9){
    println(i + "Hello World") // 0-9
}
```

### Until:不想包括上下边界

```Scala
// not include 0 and 9
for (i <- 0 until 9){
    println(i + "Hello World") // 1-8
}
```

### 集合遍历

本质上`<-`后面可以是任意集合, 因此我们可以遍历任何集合类型.

```Scala
for (i <- Array(12, 34, 53)){
    println(i)
}
for (i <- List(12, 34, 53)){
    println(i)
}
for (i <- Set(12, 34, 53)){
    println(i)
}
```

### Loop Guard

在for循环中加入条件判断式, 类似于continue.

```Scala
// skip i=2
for (i <- 0 to 3 if i != 2){
    println(i + "Hello World") // 0 1 3
}
```

### 循环步长

注意如果步长为小数时, 需要使用`BigDecimal()`方法.

```Scala
for (i <- 0 to 9 by 2){
    println(i + "Hello World") // 0 2 4 6 8
}

for (i <- 9 to 0 by -2){
  println(i + "Hello World") // 9 7 5 3 1
}

for (i <- BigDecimal(0.0) to BigDecimal(2.0) by BigDecimal(0.5)){
    println(i + "Hello World") // 0.0 0.5 1.0 1.5 2.0
}
```

### 嵌套循环

普通版本

```Scala
for (i <- 1 to 3){
    for (j <- 1 to 3){
        println("i=" + i + "j=" + j)
    }
}
```

Scala版本

```Scala
for (i <- 1 to 3; j <- 1 to 4){
    println("i=" + i + "j=" + j)
}
/*
打印九九乘法表
*/
for (i <- 1 to 9; j <- 1 to i){
    print(s"$i * $j = ${i*j} \t")
    if (j == i) println() // Line breaking
}
```

### 引入变量

和嵌套循环不同, 后面定义的j是一个和i相关联的变量, 而不是控制循环的变量. 

多个表达式用`;`隔开

```Scala
for (i <- 1 to 3; j = i - 1; k = j + i){
    println("i=" + i + "j=" + j)
}
```

### 循环返回值

Scala中, for循环的默认返回值都是`Unit`.

```Scala
val res:Unit = for (i <- 0 to 9){
    println(i) 
}
println(res) // ()
```

但是我们可以利用关键字`yield`在当前for循环中生成一个集合类型作为for循环的返回值.

```Scala
val res = for (i <- 0 to 9) yield i
println(res) // Vector(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
```

我们可以利用这个特性对一个集合里面的所有元素进行批量操作

```Scala
val res = for (i <- 0 to 9) yield i * i
println(res) // Vector(0, 1, 4, 9, 16, 25, 36, 49, 64, 81)
```

### 循环中断

Scala中使用`breakable`来中断循环, Scala封装了一个类`Breaks`来实现此类功能, 我们可以调用这个类中的方法`Breaks.breakable()`.

```Scala
import scala.util.control.Breaks._
breakable(
    for (i <- 0 to 9){
        if(i == 3)
            break()
        println(i)
    }
)
```

> 注意要先导入Breaks库

## While Loop

Scala的while循环, 需要在循环外部定义变量.(推荐使用for循环)

```Scala
/* while */
var a = 10
while(a >= 1){
    println(a)
    a -= 1
}

/* do while */
var b = 0
do {
    println(b)
    b -= 1
}while(b > 0)
```

# 函数式编程

## 函数式编程对比命令式编程

1. 命令式编程:
   1. 面向过程: 按照步骤解决问题.
   2. 面向对象: 分解对象, 行为, 属性, 通过对象关系以及行为调用解决问题.
2. 函数式编程:

但是函数式编程不关心具体运行过程, 而是关心数据之间的映射. 纯粹的函数式编程语言中没有变量, 所有量都是常量, 计算过程就是不停的表达式求值的过程, 每一段程序都有返回值. 不关心底层实现, 对人来说更好理解, 相对地编译器处理就比较复杂. 

这里的"函数"是数学意义上的函数, 也就是映射, 集合和集合之间的关系, 强调数据之间的映射关系

函数式编程优点: 编程效率高, 函数式编程的不可变性, 对于函数特定输入输出是特定的, 与环境上下文等无关. 函数式编程无副作用, 利于并行处理, 所以Scala特别利于应用于大数据处理, 比如Spark, Kafka框架.

## 函数传参

可变参数

```Scala
def f1(int: Int, str: String*): Unit = {
  println(str, int)
}
f1(23, "One", "Two") // (ArraySeq(One, Two),23)
```

> 当参数有多个时, 可变参数必须在最后面.

参数默认值

```Scala
def f1(int: Int = 23): Unit = {
  println(int)
}
f1() // 23
f1(100) // 100
```

带名函数

```Scala
  def f1(name: String = "Jon", age: Int): Unit = {
    println(s"$name is $age years old.")
  }
  f1(age = 23) // Jon is 23 years old.
```

> 适用于第一个参数有默认值时, 可直接显式命名以传入第二个参数的值.

## **匿名函数****再述**

之前提到的匿名函数:`(`*`x`*`: `*`Int`*`) => `*`x `*`+ 1`,  我们可以用一个变量来命名这个函数:

```Scala
val fun = (x: Int) => x + 1 // Type val: intToInt
fun(1) // 2
```

首先匿名函数本身是一个表达式, 我们将其赋值给了变了`fun`, `fun`的类型是`intToInt`, 之后我们便可以正常调用该函数.

以往普通的函数在传参时, 都是将数据作为参数传入, 函数体为具体的操作, 比如`(`*`x`*`: `*`Int`*`) => `*`x `*`+ 1`.

通过匿名函数, 我们将函数作为参数传入, 相当于把一个具体的"操作"作为值传入函数, 函数体内部则为数据.

```Scala
/*
########函数作为参数传递########
*/
def function(操作){
    数据
}
```

以下示例就是我们需要以不同的运算规则操作整数1和2, 我们就可以定义多个操作`add`和`minus`, 将其作为值传入函数`func`, 而函数`func`内部则是调用了传入的函数.

```Scala
def func(funcPara: (Int, Int)=>Int): Int = {
  funcPara(1, 2)
}
val add = (x: Int, y: Int) => x + y
val minus = (x: Int, y: Int) => x - y
println(func(add)) // 3
println(func(minus)) // -1
```

> `def func(`*`funcPara`*`: (`*`Int`*`, `*`Int`*`) => `*`Int`*`)`中的`(`*`Int`*`, `*`Int`*`) => `*`Int`*`)`即为需要传入的函数的类型.

## 高阶函数

### 函数作为值进行传递

我们先定义一个函数`addOne,` 功能就是接受一个整数并把它加一:

```Scala
def addOne(x: Int): Int = {
  println("f is called.")
  x + 1
}
```

我们可以将该函数作为值进行传递:

```Scala
val f1 = addOne _
val f2: Int=>Int = addOne
println(f1) //com.rockthejvm.Basics$$$Lambda$21/1456208737@d041cf (是f1的引用地址)
```

> `addOne`中的"`_`"表示将函数addOne转换为函数值(即函数对象), 它告诉编译器, 你不是在调用`addOne`函数, 而是获取它的函数值(即函数对象)并赋值给变量`f1`. 第二种写法之所以不用加"`_`", 是因为我们在前面已经说明了`f2`是一个函数值类型的变量, 因而编译器已经知道了`addOne`应该是一个函数值, 而不是在调用它.

### 函数可以作为参数传递

在[这里](https://h1mrfl4covt.feishu.cn/docx/QZujdwfNkoYfgHxkU1XcTFntncg#RNAKd7DeHoYuKxxIoDycQqtxnmd)其实已经把函数作为参数传入另一个函数了, 但是我们的数据是定义死了的, 那能不能把操作和数据都作为参数传入呢?

```Scala
def dualEval(op: (Int, Int)=>Int, a: Int, b: Int): Int = {
  op(a, b)
}

def add(a: Int, b: Int): Int = {
  a + b
}

def minus(a: Int, b: Int): Int = {
  a - b
}

println(dualEval(add, 2, 3)) // 5
println(dualEval(minus, 2, 3)) // -1
```

### 函数可以作为函数的返回值返回

```Scala
def f3(x:Int):(Int, Int)=>Unit = {
  def f4(a:Int, b:Int):Unit = {
    val m = a + b + x
    println(m)
  }
  f4
}
println(f3(100)) // com.rockthejvm.Basics$$$Lambda$25/240650537@1cd072a9
val f5 = f3(100) // 再次调用
f5(1, 2) // 103
```

> 首先`f3`函数的返回值是一个函数值, 类型为`(`*`Int`*`, `*`Int`*`)=>`*`Unit`*(即`f4`的函数类型). `f3`内部定义了`f4`, `f3`末尾的`f4`相当于"`f4 _`", 由于前面已经申明了`f3`的返回类型是一个函数, 此处的"`_`"可以省略. 直接打印`f3`得到的只会是`f4`的引用地址, 我们需要再次调用下才能打印出计算结果. 也可以直接写成`f3(100)(1, 2)`

### 高阶函数应用于对集合的操作示例

以上的高阶函数的使用方法可用于对集合类型的操作:

```Scala
val arr: Array[Int] = Array(1, 2, 3, 4, 5)

def addTwo(a:Int): Int = {
  a + 2
}

def arrayOperation(array:Array[Int], op: Int=>Int): Array[Int] = {
  for (elem <- array) yield op(elem)
}

val newArr:Array[Int] = arrayOperation(arr, addTwo)

println(newArr.mkString(","))
```

## 函数柯里化和闭包

### 闭包

- 闭包: 如果一个函数, 访问到了它的外部(局部)变量的值, 那么这个函数和他所处的环境, 称为闭包.

因为外层调用结束返回内层函数后, 经过堆栈调整(比如在C中主调或者被调清理), 外层函数的参数已经被释放了, 所以内层是获取不到外层的函数参数的. 为了能够将环境(函数中用到的并非该函数参数的变量和他们的值)保存下来, 这时会将执行的环境打一个包保存到堆里面.

下面举一个应用场景: 我们首先定义了一个两数相加的函数add:

```Scala
def add(a:Int, b:Int):Int = {
  a + b
}
```

如果我们想固定其中一个参数, 比如将函数变成某个数加上10, 我们可以这样写:

```Scala
def addByTen(a:Int): Int = {
  a + 10
}
```

那如果我们又想实现一个某个数加上5的功能呢? 我们可以再定义一个`addByFive`, 但是这样做会又去定义一个新的函数, 很麻烦. 我们其实可以用闭包实现这个功能:

```Scala
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
```

我们也可以通过lambda表达式, 简化该函数:

```Scala
// Lambda expression
def addByAny2(a: Int): Int => Int = a + _

val addThree = addByAny2(3)

println(addThree(7)) // 10
```

### 柯里化

- 函数柯里化:把一个参数列表的多个参数, 变成多个参数列表

首先回到之前的函数式编程的概念, 所谓函数, 是集合和集合之间的关系, 我们以往定义函数, 总是会引入多个参数, 比如之前定义加法函数, 它传入两个参数, 并返回这两个参数的和. 该函数是输入多个自变量, 输出一个因变量的过程.

但是函数式编程是想达成输入一个自变量, 得到一个因变量这种一一映射关系(因为要兼容Java, 所以没有只能传入一个参数的限制). 有了柯里化, 我们就可以实现每一层调用函数时, 都只会传入唯一一个参数. 

```Scala
def addCurrying(a:Int)(b:Int): Int = {
  a + b
}
```

> 柯里化的底层一定是用了闭包

## 递归

- 太常见了, 不用过多介绍.
- 方法调用自身.
- 递归要有结束逻辑.
- 调用自身时, 传递参数要有规律.
- scala中递归定义函数必须声明返回值类型, 因为无法通过推导获得.
- 纯函数式语言比如Haskell, 连循环都没有, 很多操作都需要通过递归来做, 性能比较依赖尾递归优化。

下面是一个递归的典型例子:

```Scala
def factorial(n: Int): Int = {
  if (n == 0)
    return 1
  factorial(n - 1) * n
}
```

递归有一个缺点就是当递归深度很大时, 可能会导致栈溢出(stack overflow). 在scala中可以用尾递归优化：

```Scala
// Tail recusion implementation of factorial
def tailFact(n: Int): Int = {
  if (n < 0)
    return -1
  @annotation.tailrec
  def loop(n: Int, curRes: Int): Int = {
    if (n == 0)
      return curRes
    loop(n - 1, curRes * n)
  }
  loop(n, 1)
}
```

> 每次调用 loop(n - 1, curRes * n) 时, 编译器会:
>
> - 释放当前的堆栈帧, 因为没有其他操作需要完成.
> - 重用当前的堆栈帧并将其参数更新为 n - 1 和 curRes * n.
> - 直接跳转到 loop 函数的入口.

假设我们有以下调用链：

```Scala
loop(5, 1)
  loop(4, 5)
    loop(3, 20)
      loop(2, 60)
        loop(1, 120)
          loop(0, 120)
```

在普通的递归情况下, 每次调用都会创建一个新的栈帧, 并且在递归完成之前, 这些栈帧都会保留在内存中.

但是在尾递归的情况下, 递归调用是函数的最后一个操作. 例如, 当执行 loop(4, 5) 时, 它会直接调用 loop(3, 20), 且没有其他操作需要在 loop(3, 20) 返回之后完成(普通递归在此返回之后有个乘法运算). 因此, 编译器可以优化这个过程:

1. 调用 loop(5, 1).
2. 发现 n != 0, 准备调用 loop(4, 5).
3. 在调用 loop(4, 5) 之前, 编译器会识别这是一个尾递归调用. 
4. 编译器会释放当前的栈帧 loop(5, 1), 并直接用更新后的参数调用 loop(4, 5).
5. 这个过程会持续进行, 下一步调用 loop(3, 20) 时会释放 loop(4, 5) 的栈帧, 以此类推.

假设在某个递归步骤：

```Scala
loop(5, 1)
  loop(4, 5)  // 这个栈帧将被释放
    loop(3, 20)  // 当前栈帧
      ...
```

在调用 loop(3, 20) 时：

- 编译器释放 loop(4, 5) 的栈帧, 因为 loop(4, 5) 之后没有其他操作需要执行.
- 然后直接跳转到 loop(3, 20) 的入口, 使用新的参数.

这样一来, 每次递归调用都不会增加新的栈帧, 而是重用当前的栈帧, 从而保证了递归调用使用恒定的栈空间, 这就是为什么尾递归可以防止堆栈溢出的原因.

## 控制抽象

普通函数传参: 传的是值, 比如`add(a:Int, b:Int)`. 而控制抽象是把整个代码块作为参数传递给一个函数.

先来看普通的传值参数的例子:

```Scala
def f6(a: Int): Unit = {
  println("a:" + a)
  println("a:" + a)
}
f6(23)

def f7(): Int = {
  println("f1 called")
  12
}

f6(f7()) // f7 called a:12 a:12
```

传名参数的例子:

```Scala
def f8(a: =>Int): Unit = {
  println("a:" + a)
  println("a:" + a)
}

f8(f7()) //f7 called a:12 f7 called a:12

f8({
  println("This is a code block")
  9
}) // This is a code block a:9 This is a code block a:9
```

> `=>Int`是一个返回值为`Int`类型的代码块, 其和`Int`, `Int=>Int`一样都属于返回值类型. `f7`会被调用两次, 因为`f7`相当于是作为参数`a`传入了`f8`, `f7`调用多少次取决于`f8`中`a`调用了多少次. 我们也可以直接传入一个返回值为`Int`的代码块.

案例: 实现While循环功能

```Scala
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
```

我们可以使用柯里化来简化:

```Scala
def myWhileCurrying(condition: => Boolean)(op: =>Unit): Unit = {
  if (condition) {
    op
    myWhileCurrying(condition)(op)
  }
}
var k = 10
myWhileCurrying(k >= 1) {
  println(k)
  k -= 1
```

## 惰性加载

当函数返回值被声明为lazy时, 函数的执行将会被推迟, 直到我们首次对此取值, 该函数才会执行.

```Scala
// Lazy
lazy val result = sum(13, 17)

println("1. function called")
println("3. result = " + result)

def sum(a: Int, b: Int): Int = {
  println("2. sum called")
  a + b
}
// 1. function called
// 2. sum called
// 3. result = 30
```

# OOP

## Scala 包

基本语法

```Scala
package 包名
```

三大作用

1. 区分相同名字的类
2. 当类很多时, 可以很好的管理类
3. 控制访问范围

scala中的两种包管理方式:

1. Java风格, 每个源文件声明一个包, 写在源文件最上方. 但源文件位置不需要和包名目录层级一致, 只代表逻辑层级关系, 不像java一样源文件也必须按照包名目录层级关系放置. 当然惯例是和java一样按照包名目录层级来放置.
2. 用`{}`嵌套风格定义包：

```Scala
package com {
    // code in com packageobject Outer {
        var name = "Outer"
    }
    package inner {
        // code in com.inner packagepackage scala {
            // code in com.innner.scala packageobject Inner {
                def main(args: Array[String]):Unit = {
                    println(Outer.name)
                    Outer.name = "Inner"
                    println(Outer.name)
                }
            }
        }
    }
} 
```

> 第二种风格的特点:
>
> - 一个源文件可以声明多个并列的最顶层的包.
> - 子包中的类可以访问父包中的内容, 无需导入. 但外层是不能直接访问内层的, 需要导入.

包对象:

- 为scala包定义一个同名的单例包对象, 定义在包对象中的成员, 作为其对应包下的所有类和对象的共享变量, 可以被直接访问, 无需导入.
- 关键字`package object`, 需要和包在同一层级下. 比如为`com.inner`包定义包对象的话, 必须在`com`包中, 定义形式`package obejct inner { ... }`.

包的导入:

```Scala
import users._      // 导入包 users 中的所有成员
import users.User   // 导入类 User
import users.{User, UserPreferences}      // 仅导入选择的成员
import users.{UserPreferences => UPrefs}  // 导入类并且设置别名
import users.{User => _, _}               // 导入出User类以外的所有users包中的内容
```

- 可以在任意位置导入(作用于代码块), 可以设置别名, 可以选择性导入想要导入的内容, 可以屏蔽某个类.
- 所有scala源文件默认导入:

```Scala
import java.lang._
import scala._
import scala.Predef._
```

## 面向对象

### 封装

封装就是把抽象出的数据和对数据的操作封装在一起, 数据被保护在内部, 程序的其它部分只有通过被授权的操作(成员方法), 才能对数据进行操作. Java 封装操作如下:

1. 将属性进行私有化
2. 提供一个公共的`set`方法, 用于对属性赋值.
3. 提供一个公共的`get`方法, 用于获取属性的值.

Scala中的 public 属性, 底层实际为 private, 并通过`get`方法(`obj.field()`)和`set`方法

`obj.field_=(value)`对其进行操作. 所以 Scala 并不推荐将属性设为private, 再为其设置public的`get`和`set`方法的做法. 但由于很多Java 框架都利用反射调用getXXX和setXXX方法, 有时候为了和这些框架兼容, 也会为Scala 的属性设置getXXX和setXXX方法(通过`@BeanProperty`注解实现).

#### 访问权限

- Java中`private protected public`和默认包访问权限.
- scala中属性和方法默认公有, 并且不提供`public`关键字.
- `private`私有, 类内部和伴生对象内可用.
- `protected`保护权限, scala中比java中严格, 只有同类, 子类可访问, 同包无法访问. (因为java中说实话有点奇怪)
- `private [pacakgeName]`增加包访问权限, 在包内可以访问.

#### 构造器

就是Scala中的构造方法. 包括主构造器和辅助构造器. 

```Scala
object Constructor {
    def main(args: Array[String]): Unit = {
        val p: Person = new Person()
        p.Person() // call main constructor

        val p1 = new Person("alice")
        val p2 = new Person("bob", 25)
        p1.Person()
    }
}
class Person {
    var name: String = _
    var age: Int = _
    println("call main construtor")

    def this(name: String) {
        this()
        println("call assist constructor 1")
        this.name = name
        println(s"Person: $name $age")
    }

    def this(name: String, age: Int) {
        this(name)
        this.age = age
        println("call assist constructor 2")
        println(s"Person: $name $age")
    }

    // Just a common method, not constructor
    def Person(): Unit = {
        println("call Person.Person() method")
    }
}
```

- 特点:
  - 主构造器写在类定义上, 一定是构造时最先被调用的构造器, 方法体就是类定义, 可以在类中方法定义的同级编写逻辑, 都是主构造器一部分, 按顺序执行.
  - 辅助构造器用`this`定义.
  - 辅助构造器必须直接或者间接调用主构造器, 调用其他构造必须位于第一行.
  - 主构造器和辅助构造器是重载的方法, 所以参数列表不能一致.
  - 可以定义和类名同名方法, 就是一个普通方法.
- 主构造器中形参三种形式: 不使用任何修饰, `var`修饰, `val`修饰。
  - 不使用任何修饰那就是一个形参(局部变量), 但此时在类内都可以访问到这个变量. 逻辑上不是一个成员(报错信息这么写), 但是可以访问. WTF???
  - 使用`var val`修饰那就是定义为类成员, 分别是变量和常量, 不需要也不能在类内再定义一个同名字段. 调用时传入参数就直接给到该成员, 不需要再显式赋值.
  - 主构造器中的`var val`成员也可以添加访问修饰符.
  - 不加参数列表相当于为空, `()`可以省略.
  - 主构造器的访问修饰符添加到参数列表`()`前.
- 实践指南：
  - 推荐使用scala风格的主构造器`var val`修饰参数的编写方法, 而不要被Java毒害!
  - 如果需要多种重载的构造器那么就添加新的的辅助构造器.

```Scala
class Person(private var name: String) {
    var age: Int = _
    println("call main construtor")

    def this(name: String, age: Int) = {
        this(name)
        this.age = age
        println("call assist constructor 2")
        println(s"Person: $name $age")
    }

    // J ust a common method, not constructor
    def Person(): Unit = {
        println("call Person.Person() method")
    }
}
```

### 继承

- `class ChildClassName[(argList1)] extends BaseClassName[(args)] { body }`
- 子类继承父类属性和方法。
- 可以调用父类构造器, 但感觉好像很局限, 子类中只可能调用到主构造或者辅助构造中的其中一个构造器. 那如果父类有多种构造方式, 子类想继承也没有办法? 只能是其中一种.

### 多态

- java中属性静态绑定, 根据变量的引用类型确定, 方法是动态绑定.
- 但scala中**属性和方法都是动态绑定.** 就属性而言, 其实也不应该在子类和父类中定义同名字段.
- 同java一样, 所有实例方法都是虚方法, 都可以被子类覆写.
- `override`关键字覆写.
- scala中**属性（字段）也可以被重写,** 加`override`关键字.

### 抽象类

- `abstract calss ClassName`
- 抽象属性: `val/var name: Type`, 不给定初始值.
- 抽象方法: `def methodName(): RetType`, 只声明不实现.
- 子类如果没有覆写全部父类未定义的属性和方法, 那么就必须定义为抽象类. 老生常谈了.
- 重写非抽象方法属性必须加`override`, 重写抽象方法则可以不加`override`.
- 子类调用父类中方法使用`super`关键字.
- 子类重写父类抽象属性, 父类抽象属性可以用`var`修饰, `val var`都可以. 因为父类没有实现嘛, 需要到子类中来实现.
- 如果是**重写非抽象属性,** 则父类非抽象属性只支持`val`, 不支持`var`. 因为`var`修饰为可变量, 子类继承后可以直接使用修改, 没有必要重写. `val`不可变才有必要重写.

```Scala
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
```

### 匿名子类

- 和java如出一辙. 重写所有抽象字段和方法.

```Scala
val person2: Person2 = new Person2 {
  override var age: Int = 18

  override def sleep(): Unit = {
    println("This is the anonymous class")
  }
}

person2.sleep() // This is the anonymous class
person2.eat() // Eating!
```

### 单例对象(伴生对象)

- 取代`static`语义.
- 编译后其实会生成两个类, 伴生对象是伴生类(类名为对应类后加`$`符号)的单例对象.

```Scala
class Student3(val name: String, val age: Int){
  def printInfo(): Unit = {
    println(s"Name: $name, age: $age, school: ${Student3.school}")
  }
}

object Student3 {
  var school: String = "CUG" // 伴生对象里的属性可以当成静态属性来用
}

val student3 = new Student3("Jon",21)
student3.printInfo()
```

- `obejct`, 名称和类一致, 必须放同一个文件, 前面已经说过了.
- 常见用法: 构造器私有化, 用伴生对象中的工厂方法(Factory Method Pattern). 和静态工厂方法使用起来也没有什么区别. 

```Scala
class Student3 private(val name: String, val age: Int){
  def printInfo(): Unit = {
    println(s"Name: $name, age: $age, school: ${Student3.school}")
  }
}

object Student3 {
  var school: String = "CUG" // 伴生对象里的属性可以当成静态属性来用

  def newStudent(name: String, age: Int): Student3 = new Student3(name, age)
}

val student3 = Student3.newStudent("Jon", 21)

student3.printInfo() // Name: Jon, age: 21, school: CUG
```

- 伴生对象实现`apply`方法后调用时可以省略`.apply`, 直接使用`className(args)`. 库中很多这种用法创建实例, 是一个语法糖(Syntactic Sugar).

```Scala
class Student3 private(val name: String, val age: Int){
  def printInfo(): Unit = {
    println(s"Name: $name, age: $age, school: ${Student3.school}")
  }
}

object Student3 {
  var school: String = "CUG" // 伴生对象里的属性可以当成静态属性来用

  def apply(name: String, age: Int): Student3 = new Student3(name, age)
}

val student3 = Student3("Jon", 21) // Student3.apply("Jon", 21)

student3.printInfo()
```

- 测试伴生对象时就在该对象内定义`main`函数编译时会出现的奇怪的访问权限问题. 可能对包含入口的伴生对象做了特殊处理, 具体细节尚不清楚. 最好将`main`定义在单独的伴生对象内.
- 一个简单的单例设计模式:

```Scala
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

println(singleton) // OOP$Singleton@6bf256fa
println(singleton2) // OOP$Singleton@6bf256fa (只能有一个对象实例, 因此引用地址完全一样)
```

> 单例设计模式(Singleton Design Pattern)是一种创建型设计模式, 其主要目的是确保一个类在整个程序运行期间只有一个实例, 并提供一个全局的访问点. 这种模式常用于需要全局共享资源的场景, 如配置管理, 日志记录, 线程池等.

### 特质(Trait)

Scala中采用特质Trait来代替接口的概念, 也就是说, 多个类具有相同的特质时, 就可以将这个特质独立出来, 采用关键字`trait`声明. 

- 替代java接口的概念. 但比接口更为灵活, 一种实现多继承的手段.
- 多个类具有相同的特征时, 就可以将这个特征提取出来, 用继承的方式来复用.
- 用关键字`trait`声明.

```Scala
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

class Student4 extends Person4 with Young {
  // 重写冲突的属性name, 不然会报错
  override val name: String = "Jon"
  // 实现抽象方法
  override def dating(): Unit = {
    println(s"Student $name is dating")
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
student4.sayHello() //Hello from: Jon Hello from student Jon
student4.study() // Jon is studying
student4.play() // Young people Jon is playing
student4.dating() // Student Jon is dating
```

- 匿名子类也可以引入特征:

```Scala
trait Talent {
  def singing(): Unit
  def dancing(): Unit
}
// 动态混入
val studentWithTalent = new Student4 with Talent {
  override def singing(): Unit = {
    println(s"$name is singing")
  }

  override def dancing(): Unit = {
    println(s"$name is dancing")
  }
}

studentWithTalent.singing() // Jon is singing
```

- 特征和基类或者多个特征中重名的属性或方法需要在子类中覆写以解决冲突, 最后因为动态绑定, 所有使用的地方都是子类的字段或方法. 属性的话需要类型一致, 不然提示不兼容. 方法的话参数列表不一致会视为重载而不是冲突.
- 如果基类和特征中的属性或方法一个是抽象的, 一个非抽象, 且兼容, 那么可以不覆写. 很直观, 就是不能冲突不能二义就行.
- 多个特征和基类定义了同名方法的, 就需要在子类重写解决冲突. 其中可以调用父类和特征的方法, 此时`super.methodName`指代按照顺序最后一个拥有该方法定义的特征或基类. 也可以用`super[baseClassOrTraitName].methodName`直接指代某个基类的方法, 注意需要是直接基类, 间接基类则不行.
- 也就是说基类和特征基本是同等地位.
- 其实特征的多继承和C++的多继承已经很像了, 只是名称冲突的解决方式不一样, 菱形继承的解决方式也不一样, 而且不能访问间接基类.
- scala**单****继承****多实现,** 实现体现在特征上. 基类主要用于一个对象比较核心比较本质的部分上.
- **继承****特征与类的区别:** 特征构造时不能给参数. 其他都是同样的, 都可以实现多态.

### 自类型(Self Type)

- 可实现**依赖注入**的功能.
- 一个类或者特征指定了自身类型的话, 它的对象和子类对象就会拥有这个自身类型中的所有属性和方法.
- 是将一个类或者特征插入到另一个类或者特征中, 属性和方法都就像直接复制插入过来一样, 能直接使用. 但不是继承, 不能用多态.
- 语法, 在类或特征中:`_: SelfType =>`, 其中`_`的位置是别名定义, 也可以是其他, `_`指代`this`. 插入后就可以用`this.xxx`来访问自身类型中的属性和方法了.
- 注入进来的目的是让你能够使用, 可见, 提前使用应该拥有的属性和方法. 最终只要自身类型和注入目标类型同时被继承就能够得到定义了.

```Scala
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
```

### 类型检查和转换

运行时类型识别RTTI:

- 判断类型: `obj.isInstanceOf[T]`, 确切匹配的类型或者父类都返回true.
- 转换类型: `obj.asInstance[T]`, 转换为目标类型.
- 获取类名: `classOf[T]`, 得到类对应的`Class`对象`Class[T]`, 转字符串结果是`class package.xxx.className`.
- 获取对象的类: `obj.getClass`

### 枚举类

- 继承`Enumeration`.
- 用`Value`类型定义枚举值.

```Scala
object WorkDay extends Enumeration {
    val MONDAY = Value(1, "Monday")
    val TUESDAY = Value(2, "Tuesday")
    val THURSDAy = Value(3, "Thrusday")
}

object EnumClass {
    def main(args: Array[String]): Unit = {
        println(WorkDay.MONDAY)
        println(WorkDay.TUESDAY)
    }
}
```

### 应用类

- 继承`App`, 包装了`main`方法, 就不需要显式定义`main`方法了, 可以直接执行.

```Scala
object TestApp extends App {
    println("hello,world!")
}
```

# 集合

Scala集合三大类型:

- 序列`Seq`, 集合`Set`, 映射`Map`, 所有集合都扩展自`Iterable`.
- 对于几乎所有集合类, 都同时提供**可变和不可变**版本. 
  - 不可变集合: `scala.collection.immutable`
  - 可变集合: `scala.collection.mutable`
  - 两个包中可能有同名的类型, 需要注意区分是用的可变还是不可变版本, 避免冲突和混淆.
- 对于不可变集合, 指该集合长度数量不可修改, 每次修改(比如增删元素)都会返回一个新的对象, 而不会修改源对象.
- 可变集合可以对源对象任意修改, 一般也提供不可变集合相同的返回新对象的方法, 但也可以用其他方法修改源对象.
- **建议:** 操作集合时, 不可变用操作符, 可变用方法. 操作符也不一定就会返回新对象, 但大多是这样的, 还是要具体看.
- 不可变指的是对象大小不可变, 但是可以修改元素的值(不能修改那创建了也没有用对吧), 需要注意这一点. 而如果用了`val`不变量存储, 那么指向对象的地址也不可变.
- 不可变集合在原集合上个插入删除数据是做不到的, 只能返回新的集合.

## 数组

### 不可变数组

- 创建, 访问数组:

```Scala
// 创建数组
val arr = new Array[Int](5)
// 使用伴生对象中的apply方法创建
val arr2 = Array(1, 2, 3, 4, 5) // Array.apply(1, 2, 3, 4, 5)
println(arr.mkString(", ")) // 0, 0, 0, 0, 0
println(arr2.mkString(", ")) // 1, 2, 3, 4, 5
println(arr2(0)) // 访问具体元素
arr(0) = 20 // 赋值
println(arr.mkString(", "))
```

- 遍历数组:

```Scala
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
arr2.foreach((elem: Int)=>(println(elem))) // 传入的是一个匿名函数
arr2.foreach(println) // 简化版
```

- 添加元素:

```Scala
// +号在前面就是往最前面插入元素, 后面就是在最后面插入元素
val newArr = arr2.:+(23)
println(newArr.mkString(", ")) // 1, 2, 3, 4, 5, 23

val newArr2 = newArr.+:(44)
println(newArr2.mkString(", ")) // 44, 1, 2, 3, 4, 5, 23

// add element, return a new array, : should toward to object
val newArr3 = newArr2 :+ 50

val newArr4 = 100 +: 100 +: newArr3 :+ 100
println(newArr4.mkString(", ")) // 100, 100, 44, 1, 2, 3, 4, 5, 23, 50, 100
```

> 由于是不可变数组, 添加元素时调用`:+`方法后, 实际上是返回了一个新的数组, 所以必须要用一个新的变量来接受. 因为是方法调用, 所以可以省略`.`, 以空格的形式使用, 其中要记住的一点是`:`必须朝着对象一端.

### 可变数组

对于不可变数组`Array`, 添加元素时必须创建一个新的array, 而对于可变数组`ArrayBuffer`, 不需创建新的对象.

- 创建, 访问数组:

```Scala
// 创建可变数组
val arr5: ArrayBuffer[Int] = new ArrayBuffer[Int]()
// 使用伴生对象中的apply方法创建
val arr6 = ArrayBuffer(2, 2, 2, 2)
println(arr5.mkString(", ")) // 啥都没有
println(arr6) // ArrayBuffer(2, 2, 2, 2) 默认调用toString()方法
println(arr6.mkString(", ")) // 2, 2, 2, 2
// println(arr5(0)) // 数组越界!
println(arr6(3)) // 2
arr6(0) = 99
println(arr6) // ArrayBuffer(99, 2, 2, 2)
```

- 遍历数组, 和不可变数组是一样的.
- 添加元素:

```Scala
// 向末尾添加元素
arr5 += 19
println(arr5) // ArrayBuffer(19)

// 向起始添加元素
77 +=: arr5 // 冒号朝着对象
println(arr5)

// 阳间做法
arr5.append(88)
arr5.prepend(88)
arr5.insert(1, 10000, 10) // 在指定位置添加多个元素

arr5.appendAll(arr2)
arr5.prependAll(arr2)
arr5.insertAll(0, arr2) // 在指定位置处添加一个数组
```

> 由于直接用下标访问空的arr会直接报错数组越界, 宝因此不能用下标直接向空的数组中添加元素.

- 删除元素:

```Scala
// 按照索引删除元素
arr5.remove(0)
arr5.remove(0, 4) // 从0开始连续删除4个数

// 按照元素值删除元素, 注意如果没有该元素的话, 不会报错, do nothing
arr5 -= 10000
```

### 多维数组

- 创建一个二维数组:

```Scala
// 多维数组
val twoDimArray = Array.ofDim[Int](2, 3) // 两行三列的Int型数组

twoDimArray(0)(0) = 9// 第一行第一列
```

- 遍历二维数组:

```Scala
for(i <- 0 until twoDimArray.length; j <- 0 until twoDimArray(i).length) {
  print(twoDimArray(i)(j) + "\t")
  if (j == twoDimArray(i).length - 1) println()
} // 9        0        0        
  // 0        0        0

for (i <- twoDimArray.indices; j <- twoDimArray(i).indices) {
  print(twoDimArray(i)(j) + "\t")
  if (j == twoDimArray(i).length-1) println()
} // 9        0        0        
  // 0        0        0
  
// foreach方法
twoDimArray.foreach(line => line.foreach(println))
twoDimArray.foreach(_.foreach(println))
```

## 列表

### 不可变列表

```Scala
// 创建列表
val list1 = List(1, 2, 3, 4)

// 遍历列表
// list1(0) = 12 不能通过直接访问下标修改元素
list1.foreach(println)

// 添加元素
val list2 = list1 :+ 22
val list3 = 22 +: list2

// ::号添加元素
val list4 = list3.::(9)

val list5 = Nil.::(99)

val list6 = 23 :: 23 :: 23 :: 23 :: Nil

// 合并两个列表
val list7 = list6 ::: list5 // :::号
val list8 = list6 ++ list5 // ++号
```

### 可变列表

```Scala
// 和ArrayBuffer完全一样
val list9 = new ListBuffer[Int]()
val list10 = ListBuffer(4, 4, 4, 4)

// 阳间做法
list9.append(88)
list10.prepend(88)
list10.insert(1, 10000, 10) // 在指定位置添加多个元素

list10.appendAll(list9)
list10.prependAll(list9)
list10.insertAll(0, list9) // 在指定位置处添加一个列表
```

## Set集合

### 不可变集合

- 数据无序, 不可重复.
- 可变和不可变都叫`Set`, 需要做区分. 默认`Set`定义为`immutable.Set`别名.
- 创建时重复数据会被去除, 可用来去重.
- 添加元素: `set + elem`
- 合并: `set1 ++ set2`
- 移除元素: `set - elem`
- 不改变源集合.

### 可变集合

- 操作基于源集合做更改.
- 为了与不可变集合区分, `import scala.collection.mutable`并用`mutable.Set`。
- 不可变集合有的都有. 
- 添加元素到源上: `set += elem` `add`
- 删除元素: `set -= elem` `remove`
- 合并: `set1 ++= set2`

## Map集合

### 不可变Map

```Scala
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
println(map1.getOrElse("d", 0)) // 没有该键时, 返回一个指定的值

// 简单写法
println(map1("a"))
```

### 可变Map

```Scala
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
```

## 元组

- `(elem1, elem2, ...)` 类型可以不同.
- 最多只能22个元素, 从`Tuple1`定义到了`Tuple22`.
- 使用`_1 _2 _3 ...`访问.
- 也可以使用`productElement(index)`访问, 下标从0开始.
- `->`创建二元组.
- 遍历: `for(elem <- tuple.productIterator)`
- 可以嵌套, 元组的元素也可以是元组.

```Scala
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
```

## 集合常用函数

### 基本属性和常用操作

- 获取集合长度:线性序列才有长度`length`, 所有集合类型都有大小`size`.
- 遍历`for (elem <- collection)`, 迭代器`for (elem <- collection.iterator)`.
- 生成字符串`toString` `mkString`, 像`Array`这种是隐式转换为scala集合的, `toString`是继承自`java.lang.Object`的, 需要自行处理.
- 是否包含元素`contains`.

### 衍生集合

- 获取集合的头元素`head`(元素)和剩下的尾`tail`(集合).
- 集合最后一个元素`last`(元素)和除去最后一个元素的初始数据`init`(集合).
- 反转`reverse`.
- 取前后n个元素`take(n) takeRight(n)`
- 去掉前后n个元素`drop(n) dropRight(n)`
- 交集`intersect`
- 并集`union`, 线性序列的话已废弃用`concat`连接.
- 差集`diff`, 得到属于自己, 不属于传入参数的部分.
- 拉链`zip`, 得到两个集合对应位置元素组合起来构成二元组的集合, 大小不匹配会丢掉其中一个集合不匹配的多余部分.
- 滑窗`sliding(n, step = 1)`, 框住特定个数元素, 方便移动和操作. 得到迭代器, 可以用来遍历, 每个迭代的元素都是一个n个元素集合.步长大于1的话最后一个窗口元素数量可能个数会少一些.

### 集合的简单计算操作

- 求和`sum` 求乘积`product` 最小值`min` 最大值`max`
- `maxBy(func)`支持传入一个函数获取元素并返回比较依据的值, 比如元组默认就只会判断第一个元素, 要根据第二个元素判断就返回第二个元素就行`xxx.maxBy(_._2)`.
- 排序`sorted`, 默认从小到大排序. 从大到小排序`sorted(Ordering[Int].reverse)`.
- 按元素排序`sortBy(func)`, 指定要用来做排序的字段. 也可以再传一个隐式参数逆序`sortBy(func)(Ordering[Int].reverse)`
- 自定义比较器`sortWith(cmp)`, 比如按元素升序排列`sortWith((a, b) => a < b)`或者`sortWith(_ < _)`, 按元组元素第二个元素升序`sortWith(_._2 > _._2)`.

```Scala
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
```

### 集合的高级计算操作

- 大数据的处理核心就是映射(map)和规约(reduce).
- 映射操作(广义上的map):
  - 过滤:自定义过滤条件, `filter(Elem => Boolean)`
  - 转化/映射(狭义上的map): 自定义映射函数, `map(Elem => NewElem)`
  - 扁平化(flatten): 将集合中集合元素拆开, 去掉里层集合, 放到外层中来. `flatten`
  - 扁平化+映射: 先映射, 再扁平化, `flatMap(Elem => NewElem)`
  - 分组(group): 制定分组规则, `groupBy(Elem => Key)`得到一个Map, key根据传入的函数运用于集合元素得到, value是对应元素的序列. 

```Scala
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
println(flattenMapList) // List(hello, world, world, hello, scala, hello, xiaomi)

// 分组
// 分成寄偶两组
val groupMap: Map[Int, List[Int]] = list.groupBy(_%2) // groupBy(data => if (data % 2 == 0) "even" else "odd")
val groupMap2: Map[String, List[Int]] = list.groupBy(data => if (data % 2 == 0) "even" else "odd")

println(groupMap) // Map(1 -> List(1, 3, 5), 0 -> List(2, 4, 6))
println(groupMap2) // Map(odd -> List(1, 3, 5), even -> List(2, 4, 6))

// 给定一组词汇, 按照单词首字母进行分组
val worldList = List("Duke", "UW", "NYU", "CMU", "Emory", "Columbia", "UMich")
println(worldList.groupBy(_.charAt(0))) // Map(E -> List(Emory), N -> List(NYU), U -> List(UW, UMich), C -> List(CMU, Columbia), D -> List(Duke))
```

- 规约操作(广义的reduce):
  - 简化/规约(狭义的reduce): 对所有数据做一个处理, 规约得到一个结果(比如连加连乘操作). `reduce((CurRes, NextElem) => NextRes)`, 传入函数有两个参数, 第一个参数是第一个元素(第一次运算)和上一轮结果(后面的计算), 第二个是当前元素, 得到本轮结果, 最后一轮的结果就是最终结果. `reduce`调用`reduceLeft`从左往右, 也可以`reduceRight`从右往左(实际上是递归调用, 和一般意义上的从右往左有区别).
  - 折叠(fold): `fold(InitialVal)((CurRes, Elem) => NextRes)`相对于`reduce`来说其实就是`fold`自己给初值, 从第一个开始计算, `reduce`用第一个做初值, 从第二个元素开始算. `fold`调用`foldLeft`, 从右往左则用`foldRight`(翻转之后再`foldLeft`). 具体逻辑还得还源码. 从右往左都有点绕和难以理解, 如果要使用需要特别注意.

```Scala
/* list: (1, 2, 3, 4, 5, 6) */

// reduce
println(list.reduce(_ + _)) // (a:Int, b:Int)=>(a + b) 21
println(list.reduceLeft(_-_)) // 从左往右减 -19
println(list.reduceRight(_-_)) // 从右往左减 1-(2-(3-(4-(5-6)))) = -3, a little confusing

// fold
println(list.fold(10)(_+_)) // 指定初始值
println(list.fold(10)(_ + _)) // 10+1+2+3+4+5+6 = 31
println(list.foldRight(10)(_ - _)) // 1-(2-(3-(4-(5-(6-10))))) = 7, a little confusing
```

- 应用: 将两个map中的相同键的值相加

```Scala
// 将两个map中的相同键的值相加
val map1 = Map("a" -> 1, "b" -> 3, "c" -> 6)
val map2 = mutable.Map("a" -> 6, "b" -> 2, "c" -> 9, "d" -> 3)

val map3 = map1.foldLeft(map2)((mergedMap, kv)=>{
  val key = kv._1
  val value = kv._2
  mergedMap(key) = mergedMap.getOrElse(key, 0) + value
  mergedMap
})

println(map3)
```

> 首先要注意的是我们是以map2为基准, 把map1的值加到map2里面去, 所以要将map设为mutable, 这样才能修改. 这里要用`foldLeft`而不是`fold`. `fold`源码:`def fold[A1 >: A](`*`z`*`: A1)(`*`op`*`: (A1, A1) => A1): A1 = foldLeft(`*`z`*`)(`*`op`*`)`, `foldLeft`源码:`def foldLeft[B](`*`z`*`: B)(`*`op`*`: (B, A) => B)`.我们可以看出`foldLeft`中的`op`需要传入的参数类型可以不一样, 而`fold`必须要一样. 而这里我们是对map中的键值对,也就是元组进行操作, 第二个参数类型是元组, 而第一个参数是map, 故此处只能用`foldLeft`.

- 应用: WordCount案例

```Scala
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
val countList = groupedList.map((kv) => (kv._1, kv._2.length))
println(countList) // Map(world -> 1, flink -> 1, spark -> 1, scala -> 3, from -> 2, hello -> 5)

// 排序
val sortList: List[(String, Int)] = countList.toList.sortWith(_._2 > _._2).take(3) // 降序排序, 取前三个
println(sortList) // List((hello,5), (scala,3), (from,2))
```