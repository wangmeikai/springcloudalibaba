import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class CuratorTest {
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("", new RetryNTimes(3, 1000));
        client.start();
        client.create().withMode(CreateMode.PERSISTENT).forPath("/wmk", "".getBytes());
        client.getData().usingWatcher(new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //一次性的
            }
        });

        try (CuratorCache cache = CuratorCache.build(client, "/wmk")) {
            // there are several ways to set a listener on a CuratorCache. You can watch for individual events
            // or for all events. Here, we'll use the builder to log individual cache actions
            CuratorCacheListener listener = CuratorCacheListener.builder()
                    .forCreates(node -> System.out.printf("Node created: [%s]%n", node))
                    .forChanges((oldNode, node) -> System.out.printf("Node changed. Old: [%s] New: [%s]%n", oldNode, node))
                    .forDeletes(oldNode -> System.out.printf("Node deleted. Old value: [%s]%n", oldNode))
                    .forInitialized(() -> System.out.println("Cache initialized"))
                    .build();
            // register the listener   永久的
            cache.listenable().addListener(listener);
            // the cache must be started
            cache.start();
            // now randomly create/change/delete nodes
            for (int i = 0; i < 1000; ++i) {
                client.create().orSetData().creatingParentsIfNeeded().forPath("/path", "contents".getBytes());
                client.delete().quietly().deletingChildrenIfNeeded().forPath("/path");
            }
            Thread.sleep(5);
        }
    }
}
