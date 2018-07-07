package poetry;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
import java.util.StringTokenizer;

public class PoetryMapper extends Mapper<LongWritable,Text,Text,Text> {
    private final static String KEY="月";


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        FileSplit fileSplit=(FileSplit)context.getInputSplit();
        String filename=fileSplit.getPath().getName();
        StringTokenizer stringTokenizer=new StringTokenizer(value.toString(),"。");
        while (stringTokenizer.hasMoreTokens()){
            String text=stringTokenizer.nextToken();
            if(text.indexOf(KEY)>=0){
                context.write(new Text(filename),new Text(text));
            }
        }
    }
}
