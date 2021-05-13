package com.wmk.service;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.wmk.pojo.Student;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    private RedisTemplate<String, Object> redisTemplate;
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //测试基本数据类型
    public void m() {
        redisTemplate.opsForValue().set("a", new Student("wmk", 25));

        redisTemplate.opsForList().leftPush("b", new Student("w", 25));
        redisTemplate.opsForList().leftPush("b", new Student("m", 25));
        redisTemplate.opsForList().leftPush("b", new Student("k", 25));

        redisTemplate.opsForHash().put("c", "c", new Student("k", 25));

        redisTemplate.opsForSet().add("d", new Student("w", 25));
        redisTemplate.opsForSet().add("d", new Student("m", 25));
        redisTemplate.opsForSet().add("d", new Student("k", 25));

        redisTemplate.opsForZSet().add("e", new Student("w", 25), 100);
        redisTemplate.opsForZSet().add("e", new Student("m", 25), 100);
        redisTemplate.opsForZSet().add("e", new Student("k", 25), 99);
    }

    //测试bitmap
    public void m1() {
        stringRedisTemplate.opsForValue().set("a", "abc");
        stringRedisTemplate.opsForValue().setBit("a", 6, true);
        stringRedisTemplate.execute(connection -> connection.bitCount("a".getBytes()), true);
        stringRedisTemplate.execute(connection -> {
            connection.openPipeline();
            //connection.setBit();
            return null;
        }, true);
    }

    //测试布隆过滤器 (可利用redis位图实现)
    public void m2() {
        BloomFilter<Integer> integerBloomFilter = BloomFilter.create(Funnels.integerFunnel(), 10000);

    }

    //分布式锁
    public boolean m3() {
        String lockKey = "lockKey";
        String lockValue = UUID.randomUUID().toString();

        try {
            //设置过期时间，避免还没释放锁就系统宕机
            Boolean res = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 10, TimeUnit.SECONDS);
            if (!res) {
                //System.out.println("获取锁失败！！！！");
                return false;
            }else {
                System.out.println("执行业务逻辑"+(i++));
                return true;
            }
        } finally {
            // 避免错误释放锁
            if (lockValue.equals(stringRedisTemplate.opsForValue().get(lockKey))){
                // 系统抛异常也可以释放锁
                redisTemplate.delete(lockKey);
            }
        }

    }

    public static int i = 0;


    @PostConstruct
    public void init(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.40.3:6379");
        redissonClient = Redisson.create(config);
    }

    private RedissonClient redissonClient;

    // Redisson 分布式锁
    public void m4(){

        RLock lock = redissonClient.getLock("key");
        try {
            lock.lock();  // 锁续命

            System.out.println("执行业务逻辑。。。"+(i++));

        } finally {
            lock.unlock();
        }

    }
}












