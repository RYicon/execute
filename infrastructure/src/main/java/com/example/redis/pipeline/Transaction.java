package com.example.redis.pipeline;

import com.example.redis.RedisPool;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Transaction {

    static Jedis instance = RedisPool.getInstance();

    public static void main(String[] args) throws IOException {


        String key_prefix = "pipeline::user::";


        ArrayList<String> list =new ArrayList<String>(Collections.nCopies(1000, "1")) ;
        for (int i = 0; i <list.size() ; i++) {
            list.set(i, key_prefix + i);
        }
        String[] keys = list.toArray(new String[list.size()]);



        for (int i = 0; i < keys.length; i++) {
            instance.set(keys[i],"1");
        }


        Stopwatch stopwatch = Stopwatch.createStarted();

        Pipeline pipeline = instance.pipelined();
        try {
            pipeline.watch(keys);
            // key如果有变化，后续提交事务直接失败，unwatch可以取消监控

            for (int i = 0; i < keys.length; i++) {
                pipeline.get(keys[i]);

            }
            System.out.println("事务状态："+pipeline.exec());


        } catch (Exception e) {
            // 如果有异常说明你提交事务的时候有人更新了key
        } finally {
            // 每个流水线都绑定了一个连接
            // 执行完一个流水线，必须执行reset把连接还给连接池
            pipeline.close();
        }
        stopwatch.stop();
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));

    }
}
