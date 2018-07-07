package spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

public class SparkWordCount {

    public static void main(String[] args) {
        // 第一步：创建SparkConf对象,设置相关配置信息
        SparkConf conf = new SparkConf();
        conf.setAppName("wordcount");
        conf.setMaster("local");

        // 第二步：创建JavaSparkContext对象，SparkContext是Spark的所有功能的入口
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 第三步：创建一个初始的RDD
        // SparkContext中，用于根据文件类型的输入源创建RDD的方法，叫做textFile()方法
        JavaRDD<String> lines = sc.textFile("G:\\个人项目\\Poetry01.txt");

        // 第四步：对初始的RDD进行transformation操作，也就是一些计算操作
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Iterator<String> call(String line) throws Exception {

                return Arrays.asList(line.split("。")).iterator();

            }
        });
        JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {

            /**
             *
             */

            private static final long serialVersionUID = 1L;

            @Override
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<String, Integer>(word, 1);
            }
        });

        JavaPairRDD<String, Integer> wordCounts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {

                return v1 + v2;

            }
        });
        wordCounts.foreach(new VoidFunction<Tuple2<String,Integer>>() {

            /**
             *
             */

            private static final long serialVersionUID = 1L;

            @Override
            public void call(Tuple2<String, Integer> wordCount) throws Exception {
                System.out.println(wordCount._1 + "------" + wordCount._2+"times.");
            }
        });
        sc.close();

    }
}
