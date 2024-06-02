Github仓库地址:https://github.com/Huangyijie2003/2024_training

# Scala Basics

## Scala Type Hierarchy and Data Type

![img](https://h1mrfl4covt.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWJhMmY0Yzg5MjYyMzFhOWI3ODZlMDQ4NDllMTcwZWJfVjUwOGhNMk4zUDlvRnY4aGJnY2sybTA0RGh6b3h6c3NfVG9rZW46Wk41MWJnUHp5b3lHdVJ4OVlVU2NSdEFQbk9mXzE3MTczMzgwMTk6MTcxNzM0MTYxOV9WNA)

Any是scala中所有类型的父型, scala中一切数据都是对象, 不管是值类型还是引用类型. 

### AnyVal

#### Type casting

AnyVal包含基础的数据类型, Scala遵守低精度的类型向高精度的类型进行自动转换(隐式转换).

![img](https://h1mrfl4covt.feishu.cn/space/api/box/stream/download/asynccode/?code=YmZjOTZhYzZiMmZlZmQ0N2ZjOTFiMTI0ODYzMDE4MGVfcjY4UlpENU9tV1ZPTVRVNGp1bWFsbFdITm9yZ1pHOEdfVG9rZW46VkY2NWJoWGtmb0g0NGF4OHc2TmN2OGh3bkllXzE3MTczMzgwMTk6MTcxNzM0MTYxOV9WNA)

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

**定义作用域:** 代码块内定义的变量只在该代码块内有效.

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

## **匿名函数再述**

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