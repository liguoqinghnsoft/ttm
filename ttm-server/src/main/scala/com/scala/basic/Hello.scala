import scala.io.Source

object Hello{

  def main(args:Array[String]): Unit ={
    //println("Hello Scala!")
    //println("add()->"+add)
    array();
    //maps()
    //sourceTest()
    //forFun()
  }

  def add():Integer={
    val a=1;
    val b=3;
    a+b;
  }

  def array(): Unit ={
    var array = Array(1,11,2,33,55);
    //array = array.filter(_  % 11 == 0);
    println(array.isEmpty);
    println(array.apply(3));
    array.update(3,333);
    println(array.apply(3));
    for(item <- array){
      println("item->"+item+"\t")
    }
//    for(item <- 1 to 10){
//      print("item->"+item+"\t")
//    }
    val plus = array.map(_*2);
    for(item <- 0 to plus.length-1 if item % 2 == 0){
      //if(plus(item) % 4 == 0)
      println("item->"+plus.apply(item)+"\t")
    }
    var i=0;
    do{
      println("do while "+i+"->"+plus(i))
      i=i+1; //i+=1
    }while(i!=0 && i%3!=0)
  }

  def maps(): Unit ={
    var mp = Map("java"->"1001","scala"->"1002");
    println(mp("java"));
    for((k,v) <- mp){
      println("key is "+k+",value is "+v)
    }
    var sets:Set[String]  = mp.keySet;
    for(k <- sets){
      println("key is "+k)
    }
    for((k,_) <- mp){
      println("key is "+k)
    }

  }

  def sourceTest(): Unit ={
//    val source = Source.fromFile("E:\\fix.txt");
//    val lines:Iterator[String]  = source.getLines();
//    for(line <- lines){
//      println("line is "+line)
//    }
    val tpl = (1,2,3,4,5)
    System.out.println(tpl._1)
  }

  def forFun(): Unit ={
       def add(i:Int)=i+10;
       println(add(2));
       val addItem=(i:Int)=>i+100;
       println(addItem(10))
       for(i <- 1 to 3;j <- 1 to 3 if i<=j)
         println(i*j)
       def fcn(num:Int):Int=if (num<=1) 1 else num*fcn(num-1);
       println(fcn(5))
       def contactStr(str:String*)={
           var tmp = str(0);
           for(i <- 1 to str.length-1){
             tmp = tmp.concat(str(i));
           }
           tmp
       }
      println(contactStr("X","Y","Z"))
  }


}