package com.example.hk_ht;

import com.example.hk_ht.Util.Timing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sql.DataSource;

@SpringBootTest
class HkHtApplicationTests {
    @Autowired   //自动配置
    private DataSource source;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void conn() {
        try {
            source.getConnection("root", "123456");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Autowired
    private Timing timing;

    @Test
    public void TimingTest(){
        timing.testTiming();
    }


}
