#  RDD的总结
RDD是一个基本抽象类,操作RDD就相当于操作本地鸡和一样,降低了编程的复杂度

RDD的算子分为两类,一类是Transformation(lazy[该类及运行时不会提交任务]),一类是Action(该类运行时会触发任务执行)

RDD中不存放真正计算的数据,而是记录了RDD的转关系(调用了什么方法,传入可什么函数)

创建RDD有哪些方式呢?
1. 通过外部的存储系统创建RDD
2. 将Driver的Scala集合通过并行化的方式变成RDD(实验,测试)
3. 调用的一个已存在的RDD的Transformation,会生成一个新的RDD

RDD的Transformation的特点
1. lazy (不会是任务立即执行)
2. 生成新的RDD

RDD分区的数据取决于哪些因素?
1. 如果是将Driver端Scala集合并行化创建RDD,并且没有指定RDD的分区,RDD的分区就是噶APP分配中的核数
2. 如果是将hdfs中读取数据创建RDD,并且设置了最小的分区数量是1,那么RDD的分区数据即是输入切片的数据,如果不设置最小分区数量,即spark调用textFile时默认传入2,那么RDD的分区数量会>=输入切片的数量

---
RDD中的map方法,在Executor执行时,是一条条的将数据拿来处理的

mapPartitionsWithIndex一次拿出一个分区(该分区中并没有数据,而是记录要读取那些数据真正生成task时会读取多条数据),并且可将分区的编号取出来

功能:取分区中对应的数据时,还可以将分区的编号取出来,这样就可以知道数据是属于那个分区的(哪个分区对应task的数据)

```scala
//该函数的功能是将对应分区中的数据取出来,并且带上分区编号
val func =(index: Int,it:Integer[Int] => {
    it.map(e => s"part:$index,ele:$e")
}
```
