package cn.hjr

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

/**
 *  2.12.6版本
 */

//    val conf = new SparkConf()
//    conf.setAppName("wordcount")
//    conf.setMaster("local")
//    val sc = new SparkContext(conf)
//    val line = sc.textFile("G:\\个人项目\\Poetry01.txt")
//    line.flatMap(_.split("。")).map((_, 1)).reduceByKey(_+_).saveAsTextFile("e:\\MyHadoop\\ScalaOut\\")
//    sc.stop()
object MyRouteMain  {

  def main(args: Array[String]) {

   var da=("1",2,33)
    var i=("2",3,4)
    var y="99"
    println(da._2)
    var heiSet = Set("dfaa",11)
    heiSet+=i

    var heiMap = Map("1"->2,3->"dsf")
    for((k,y) <- heiMap){
      println("key"+k)
      println("value"+y)
    }


    var iterator=Iterator("1",2,3)
//    while(iterator.hasNext){
//      println(iterator.next())
//    }
//
    for(kkey<-iterator){
      println(kkey)
    }


    var mys=new myScala

  }


  class myScala extends Thread{
    override def start(): Unit = {
      println("hello")
    }


  }

  object myScala {

  }

}

