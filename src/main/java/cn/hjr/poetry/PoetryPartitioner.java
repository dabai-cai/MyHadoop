package cn.hjr.poetry;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

public class PoetryPartitioner extends HashPartitioner<Text,Text> {
    @Override
    public int getPartition(Text text, Text text2, int i) {
        int partitions;
        if(text.toString().indexOf("01")>=0){
            partitions=1;
        }else if(text.toString().indexOf("02")>=0){
            partitions= 2;
        }else if(text.toString().indexOf("03")>=0){
            partitions= 3;
        }else {
            partitions=0;
        }
        return partitions;
    }
}
