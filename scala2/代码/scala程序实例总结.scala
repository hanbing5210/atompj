//1.WordCount
//下面的一行代码是运行Linux上的（spark程序）
sc.textFile("g:\\atompj\\scala2\\xx.txt").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).collect
var arr1=Array("hello","hello","tom","jerry","tom","hello")
var arr=Array("hello jerry hello tom hello duanduan hello taotao","hello tom hello jerry hello hello james")
arr.flatMap(_.split(" ")).groupBy(x=>x).mapValues(x=>x.length).toList.sortBy(x => - x._2)
arr.flatMap(_.split(" ")).groupBy(x=>x).mapValues(x=>x.length).toList.sortBy(x => - x._2)
arr1.groupBy(x=>x).mapValues(x=>x.length).toList.sortBy(x => - x._2)
//定义变量
var price=1888.9d      //值可更改
var b:Int=6  //值可更改
val age:Int=7  //值不可更改
val name="张三"      //值不可更改

//格式化输出
/*'f'*/
printf("%s 学费 %4.2f",name,price)//无换行
println(f"$name%s 学费 $price%4.2f")//有换行
/*'s'*/
println(s"name=$name,price=$price")
/*条件表达式*/
if（b>8）B else 200
val score =76
val res4={
  if(score>60&&score<70)"及格"
  else if（score>70&&score<80） "良好"
  else "优秀"
}
/*循环*/
val array = Array(1,2,3,4,5,6)
for (e <- array if e%2==0) yield e
/*方法的定义与调用*/
def ml(x: Int, y: Int): Int = {
  x*y
}//define method
//use methodBy1
val r=ml(45,123)
//use methodBy2
printf(ml(12,456))
ml _ //method to function
//define function and use
//1
val f1=(x: Int)=>x*10
//2
val f2:(Int,Int)=>Int =(x,v)=>x*v
//
//传值调用和传名调用
def currentTime():Long = {
  println("打印系统时间,单位是纳秒")
  System.nanoTime()
}
def delayed(f:=>Long): Unit = {
  println("delayed==============")
  println("time = "+f)
}
def delayed1(time: Long) = {
  println("delayed1 ================ ")
  println("time1 = " + time)
}
// 调用方式一
delayed(currentTime)
println("--------------------华丽闷骚的分割线------------------")
// 调用方式二
val time =currentTime()
delayed1(time)

// 可变参数
def methodManyParams(a: String*) = {
  for(p <- a) {
    println(p)
  }
}
// 调用
methodManyParams("中华","人民","共和","国")
// 默认参数值函数
def add(g: Int=12,q: Int=0) = {
  println(s"g + q = ${g + q}")
  g+q
}
// 调用
add(2) // 带有默认值g的参数，调用时可不传
add(q=45)// 指定传入参数的位置，这种方式传入参数时无视参数顺序

// 高阶函数
def apply(f: Int=> String,v) = {
  f(v)
}
def layout(x: Int) = {
  "[" + x.toString() + "]"
}
println(apply(layout,10))


// 部分参数应用函数
def log(date: Date,message: String) = {
  println(s"$date,$message")
}

val logBoundDate: (String) => Unit log(date,_:String)

logBoundDate("fuck jerry")


// 柯里化
def add(x: Int,y: Int) = {
  x+y
}
def add(x: Int) = (y: Int) => {
  x+y
}

// 偏函数
def func1: PartialFunction[String,Int] = {
  case "one" => 1
  case "two" => 2
  case _ => -1
}
def main(args: Array[String]): Unit = {
  println(func1("one"))
}
// 数组的定义
// 数组的定义, 定义一个固定长度的数组, 长度可变, 内容可变
var x:Array[String] = new Array[String](3)
// 或者
var y = new Array[String](3)
// 定义定长数组, 长度不可变, 内容可变
val z = Array(1,2,3)
// 修改第 0 个元素的内容
z(0) = 100

// map|flatten|flatMap|foreach 方法的使用
// 定义一个数组
val array = Array[Int](2,4,6,9,3)
// map 方法是将 array 数组中的每个元素进行某种映射操作, (x: Int) => x * 2 为
一个匿名函数, x 就是 array 中的每个元素
val y = array map((x: Int) => x * 2)
// 或者这样写, 编译器会自动推测 x 的数据类型
val z = array.map(x => x*2)
// 亦或者, _ 表示入参, 表示数组中的每个元素值
val x = array.map(_ * 2)
println(x.toBuffer)
println("--------骚气的分割线--------")
// 定义一个数组
val words = Array("hello tom hello jim hello jerry", "hello Hatano")
// 将数组中的每个元素进行分割
// Array(Array(hello, tom, hello, jim, hello, jerry), Array(hello,
Hatano))
val splitWords: Array[Array[String]] = words.map(wd => wd.split(" "))
// 此时数组中的每个元素进过 split 之后变成了 Array, flatten 是对 splitWords
里面的元素进行扁平化操作
// Array(hello, tom, hello, jim, hello, jerry, hello, Hatano)
val flattenWords = splitWords.flatten
// 上述的 2 步操作, 可以等价于 flatMap, 意味先 map 操作后进行 flatten 操作
val result: Array[String] = words.flatMap(wd => wd.split(" "))
// 遍历数组, 打印每个元素
result.foreach(println)



// 定长数组和变长数组
import scala.collection.mutable.ArrayBuffer
object ArrayTest {
def main(args: Array[String]) {
//初始化一个长度为 8 的定长数组， 其所有元素均为 0
val arr1 = new Array[Int](8)
//直接打印定长数组， 内容为数组的 hashcode 值
println(arr1)
//将数组转换成数组缓冲， 就可以看到原数组中的内容了
//toBuffer 会将数组转换长数组缓冲
println(arr1.toBuffer)
//注意： 如果 new， 相当于调用了数组的 apply 方法， 直接为数组赋值
//初始化一个长度为 1 的定长数组
val arr2 = Array[Int](10)
println(arr2.toBuffer)
//定义一个长度为 3 的定长数组
val arr3 = Array("hadoop", "storm", "spark")
//使用()来访问元素
println(arr3(2))
//////////////////////////////////////////////////
//变长数组（ 数组缓冲）
// 如 果 想 使 用 数 组 缓 冲 ， 需 要 导 入 import
scala.collection.mutable.ArrayBuffer 包
val ab = ArrayBuffer[Int]()
//向数组缓冲的尾部追加一个元素
//+=尾部追加元素
ab += 1
//追加多个元素
ab += (2, 3, 4, 5)
//追加一个数组++=
ab ++= Array(6, 7)
//追加一个数组缓冲
ab ++= ArrayBuffer(8,9)
//打印数组缓冲 ab
//在数组某个位置插入元素用 insert
ab.insert(0, -1, 0)
//删除数组某个位置的元素用 remove
ab.remove(8, 2)
println(ab)
}
}




// seq

object ImmutListTest {
def main(args: Array[String]) {
//创建一个不可变的集合
val lst1 = List(1,2,3)
//将 0 插入到 lst1 的前面生成一个新的 List
val lst2 = 0 :: lst1
val lst3 = lst1.::(0)
val lst4 = 0 +: lst1
val lst5 = lst1.+:(0)
//将一个元素添加到 lst1 的后面产生一个新的集合
val lst6 = lst1 :+ 3
val lst0 = List(4,5,6)
//将 2 个 list 合并成一个新的 List
val lst7 = lst1 ++ lst0
//将 lst0 插入到 lst1 前面生成一个新的集合
val lst8 = lst1 ++: lst0
//将 lst0 插入到 lst1 前面生成一个新的集合
val lst9 = lst1.:::(lst0)
println(lst9)
}
}
// 注意： :: 操作符是右结合的， 如 9 :: 5 :: 2 :: Nil 相当于 9 :: (5 :: (2 :: Nil))
// 可变的序列 import scala.collection.mutable._
import scala.collection.mutable.ListBuffer
object MutListTest extends App{
//构建一个可变列表， 初始有 3 个元素 1,2,3
val lst0 = ListBuffer[Int](1,2,3)
//创建一个空的可变列表
val lst1 = new ListBuffer[Int]
//向 lst1 中追加元素， 注意： 没有生成新的集合
lst1 += 4
lst1.append(5)
//将 lst1 中的元素最近到 lst0 中， 注意： 没有生成新的集合
lst0 ++= lst1
//将 lst0 和 lst1 合并成一个新的 ListBuffer 注意： 生成了一个集合
val lst2= lst0 ++ lst1
//将元素追加到 lst0 的后面生成一个新的集合
val lst3 = lst0 :+ 5
}
//将 0 插入到 lst1 的前面生成一个新的 List
val lst2 = 0 :: lst1
val lst3 = lst1.::(0)
val lst4 = 0 +: lst1
val lst5 = lst1.+:(0)
//将一个元素添加到 lst1 的后面产生一个新的集合
val lst6 = lst1 :+ 3
val lst0 = List(4,5,6)
//将 2 个 list 合并成一个新的 List
val lst7 = lst1 ++ lst0
//将 lst0 插入到 lst1 前面生成一个新的集合
val lst8 = lst1 ++: lst0
//将 lst0 插入到 lst1 前面生成一个新的集合
val lst9 = lst1.:::(lst0)
println(lst9)
}
}

// set


// 不可变的 Set
import scala.collection.immutable.HashSet
object ImmutSetTest extends App{
val set1 = new HashSet[Int]()
//将元素和 set1 合并生成一个新的 set， 原有 set 不变
val set2 = set1 + 4
//set 中元素不能重复
val set3 = set1 ++ Set(5, 6, 7)
val set0 = Set(1,3,4) ++ set1
println(set0.getClass)
}

// 可变的 Set
import scala.collection.mutable
object MutSetTest extends App{
//创建一个可变的 HashSet
val set1 = new mutable.HashSet[Int]()
//向 HashSet 中添加元素
set1 += 2
//add 等价于+=
set1.add(4)
set1 ++= Set(1,3,5)
println(set1)
//删除一个元素
set1 -= 5
set1.remove(2)
println(set1)
}


// MAP
import scala.collection.mutable
object MutMapTest extends App{
val map1 = new mutable.HashMap[String, Int]()
//向 map 中添加数据
map1("spark") = 1
map1 += (("hadoop", 2))
map1.put("storm", 3)
println(map1)
// 取值 get getOrElse()
//从 map 中移除元素
map1 -= "spark"
map1.remove("hadoop")
println(map1)
}


// 元组

// 定义元组
var t = (1, "hello", true)
// 或者
val tuple3 = new Tuple3(1, "hello", true)
// 访问 tuple 中的元素
println(t._2) // 访问元组总的第二个元素
// 迭代元组
t.productIterator.foreach(println)
// 对偶元组
val tuple2 = (1, 3)
// 交换元组的元素位置, tuple2 没有变化, 生成了新的元组
val swap = tuple2.swap

并行化集合par
//创建一个 List
val lst0 = List(1,7,9,8,0,3,5,4,6,2)
//折叠： 有初始值（ 无特定顺序）
val lst11 = lst0.par.fold(100)((x, y) => x + y)
//折叠： 有初始值（ 有特定顺序）
val lst12 = lst0.foldLeft(100)((x, y) => x + y)
//聚合
val arr = List(List(1, 2, 3), List(3, 4, 5), List(2), List(0))
val result = arr.aggregate(0)(_+_.sum, _+_)


// Map 和 Option

// Option 是 Some 和 None 的父类
// Some 代表有（ 多例） ， 样例类
// None 代表没有（ 单例） ， 样例对象
val mp = Map("a" -> 1, "b" -> 2, "c" -> 3)
val r: Int = mp("d")
// Map 的 get 方法返回的为 Option, 也就意味着 rv 可能取到也有可能没取到
val rv: Option[Int] = mp.get("d")
// 如果 rv=None 时， 会出现异常情况
val r1 = rv.get
// 使用 getOrElse 方法，
// 第一个参数为要获取的 key,
// 第二个参数为默认值， 如果没有获取到 key 对应的值就返回默认值
val r2 = mp.getOrElse("d", -1)
println(r2)


// 案例 wordCount


// 定义一个数组
val words = Array("hello tom hello star hello sheep", "hello tao hellotom")
words.flatMap(_.split(" ")) // 对数组中的每个元素进行切分, 并进行扁平化操作
.map((_, 1)) // 将数组的每个元素转换成一个对偶元组, 元组的第二个元素为 1
.groupBy(_._1) // 对集合中的所有元素进行按单词分组, 相同单词的元组分到一组
.mapValues(_.length) // 对每个 key 的 value 集合进行求长度操作
.toList // 将 map 转换成 List
// 实现方式二
words.flatMap(_.split(" ")).groupBy(x => x).map(t => (t._1,
t._2.length)).toList
