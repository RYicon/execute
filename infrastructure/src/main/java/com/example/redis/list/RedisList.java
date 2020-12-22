package com.example.redis.list;

import com.alibaba.fastjson.JSON;
import com.example.redis.RedisPool;
import redis.clients.jedis.Jedis;

public class RedisList {
    public static void main(String[] args) {
        Jedis instance = RedisPool.getInstance();


        instance.rpush("list", "11");
        instance.rpush("list", "22");
        instance.rpush("list", "33");
        instance.rpush("list", "44");
        instance.rpush("list", "55");


        System.out.println(JSON.toJSONString(instance.lrange("list", 0L, -1)));
        System.out.println(instance.llen("list"));
        ;


        instance.lpop("list");
        System.out.println(JSON.toJSONString(instance.lrange("list", 0L, -1)));

        while (instance.llen("list") > 0) {
            instance.lpop("list");
        }

        System.out.println(JSON.toJSONString(instance.lrange("list", 0L, -1)));

        instance.blpop(5, "list");
        System.out.println("无可弹出元素时阻塞5s");
    }
}
