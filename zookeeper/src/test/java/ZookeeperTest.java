import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class ZookeeperTest {

    public static void main(String[] args) throws Exception {
        String conn = "192.168.40.3:2181";  // 如果是集群，则用逗号隔开
        ZooKeeper zooKeeper = new ZooKeeper(conn, 100000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("全局WatchedEvent"+watchedEvent.getPath());
                System.out.println("全局WatchedEvent"+watchedEvent);
            }
        });

        List<String> children = zooKeeper.getChildren("/www", true);
        System.out.println(children);

        Stat exists = zooKeeper.exists("/www", true);
        System.out.println(exists);
//
        byte[] data = zooKeeper.getData("/www", true, null);
        System.out.println(new String(data));




        System.in.read();
    }
}
