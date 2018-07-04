package cn.hjr.poetry;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PoetryReduce extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder stringBuilder=new StringBuilder();
        for(Text text:values){
            stringBuilder.append(text.toString()).append("--**--");
        }
        context.write(key,new Text(stringBuilder.toString()));
    }
}
