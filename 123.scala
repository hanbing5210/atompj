object test{
  def main(args: Array[String]): Unit = {
    // var arr = Array("hello 寒冰 你好hello 你好寒冰","你好 Tom Tom Tom")
    // arr.flatMap(_.split(" ")).groupBy(x=>x).mapVlues(x=>x.length).toList.sortBy(x=>x._2).foreach(println)
    var arr=Array("hello jerry hello tom hello duanduan hello taotao","hello tom hello jerry hello hello james")
    arr.flatMap(_.split(" ")).groupBy(x=>x).mapValues(x=>x.length).toList.sortBy(x => - x._2)
  }

}
