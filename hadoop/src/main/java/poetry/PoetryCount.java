package poetry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class PoetryCount {
    public static void main(String[] args) {
        try {
            Configuration conf = new Configuration();
            Job job = new Job(conf);
            job.setJarByClass(PoetryCount.class);
            job.setJobName("PoetryCount");
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);
            job.setPartitionerClass(PoetryPartitioner.class);
            job.setNumReduceTasks(4);//分三个区
            job.setMapperClass(PoetryMapper.class);
            job.setReducerClass(PoetryReduce.class);
            job.setInputFormatClass(TextInputFormat.class);
            job.setOutputFormatClass(TextOutputFormat.class);

            FileInputFormat.addInputPath(job,new Path("e:\\MyHadoop\\input\\Poetry"));
            FileOutputFormat.setOutputPath(job, new Path("e:\\MyHadoop\\out\\Poetry"));
//            FileInputFormat.addInputPath(job,new Path(args[0]));
//            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            job.waitForCompletion(true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
