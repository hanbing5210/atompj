object ml{
  def main(args: Array[String]): Unit = {
    //匹配字符串内容
    def contentMatch(str: String) = Str match {
      case "hello" => println("hello")
      case "dog" => println("dog")
      case "1" => println("1")
      case _ => println("pipeibushang ")


    }//一旦匹配上就不往下匹配了

    //匹配数据类型
    def typrMatch(tp: Any)=tp match {
      case x: Int=> println("Int $x")
      case y: Long=> println("Int $y")
    }


    //匹配Array
    def arrayMatch(arg: Any) =arg match {
      case Array(0) => println("只有1个0元素的数组")
    }




  }

}
