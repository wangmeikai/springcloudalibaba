package com.wmk;

import com.wmk.pojo.Student;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class SpringDataRedisTest {

    StringRedisTemplate stringRedisTemplate;

    RedisTemplate<String, Student> redisTemplate;

    public void contextLoads(){
        stringRedisTemplate.opsForValue().set("wang","abc");

        String[] ss = new String[]{"aa","bb","cc","dd"};
        stringRedisTemplate.opsForList().rightPushAll("mei",ss);

        stringRedisTemplate.opsForList().range("mei", 0, -1);

//        redisTemplate.opsForValue().set("kai",new Student("wmk",25));
//        System.out.println(redisTemplate.opsForValue().get("kai"));
    }
}
