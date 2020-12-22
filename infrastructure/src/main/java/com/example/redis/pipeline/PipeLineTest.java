package com.example.redis.pipeline;

import com.example.redis.RedisPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PipeLineTest {
    static Jedis jedis = RedisPool.getInstance();

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            jedis.set(String.valueOf(i), String.valueOf(i));
        }
        long end = System.currentTimeMillis();
        System.out.println("the jedis total time is:" + (end - start));

        Pipeline pipe = jedis.pipelined(); // 先创建一个 pipeline 的链接对象
        long start_pipe = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            pipe.set(String.valueOf(i), String.valueOf(i));
        }
        pipe.sync(); // 获取所有的 response
        long end_pipe = System.currentTimeMillis();
        System.out.println("the pipe total time is:" + (end_pipe - start_pipe));

        BlockingQueue<String> logQueue = new LinkedBlockingQueue<String>();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            logQueue.put("i=" + i);
        }
        long stop = System.currentTimeMillis();
        System.out.println("the BlockingQueue total time is:" + (stop - begin));
    }
}
