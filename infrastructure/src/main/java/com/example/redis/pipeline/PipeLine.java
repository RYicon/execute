package com.example.redis.pipeline;

import com.example.redis.RedisPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;

public class PipeLine {
    static Jedis instance = RedisPool.getInstance();

    public static void main(String[] args) throws IOException {

        String user_id = "user1";
        String key = "pipeline::user1";
        Pipeline pipeline = instance.pipelined();
        try {
            pipeline.watch(key);
            // key如果有变化，后续提交事务直接失败，unwatch可以取消监控
            Response<String> lock_value = pipeline.get(key);

            if (lock_value == null) {
                // 锁已经被释放了
            } else if (lock_value.get() == user_id) {
                // 是自己加的锁，可以释放
                // 先用multi()打开一个事务，事务里的命令一起成功或者一起失败
                pipeline.multi();
                pipeline.del(key);
                pipeline.exec(); // 提交事务，discard可以放弃事务
            } else {
                // 不是自己加的锁，不能让你去释放
            }
        } catch (Exception e) {
            // 如果有异常说明你提交事务的时候有人更新了key
        } finally {
            // 每个流水线都绑定了一个连接
            // 执行完一个流水线，必须执行reset把连接还给连接池
            pipeline.close();
        }
    }
}
