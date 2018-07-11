package cn.hjr.pv;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class TopoMain {

    public static void main(String[] args){
        try {
        TopologyBuilder builder=new TopologyBuilder();

        builder.setSpout("log-reader", new LogReader(),1);

        builder.setBolt("log-stat", new LogStat(), 2).fieldsGrouping("log-reader",new Fields("user"));

        builder.setBolt("log-writer", new LogWriter(),1).shuffleGrouping("log-stat");

        Config config=new Config();
        config.setNumWorkers(5);
        config.put(Config.TOPOLOGY_MAX_TASK_PARALLELISM, 1);
        LocalCluster cluster = new LocalCluster();
            try {
                cluster.submitTopology("log-topology", config,builder.createTopology());
                Thread.sleep(10000);       //10秒后kill掉提交的拓扑的线程
                cluster.killTopology("log-topology");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                cluster.shutdown();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
