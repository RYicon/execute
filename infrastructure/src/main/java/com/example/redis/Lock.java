package com.example.redis;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

public class Lock {
    static Jedis instance = RedisPool.getInstance();
    static String rewardRankingKey = "lock::";

    public static void main(String[] args) throws InterruptedException {
        try {
            System.out.println(getLock(rewardRankingKey));
            ;
            System.out.println(getLock(rewardRankingKey));
            Thread.sleep(1000L);
            System.out.println(getLock(rewardRankingKey));
            ;
        } finally {
            unLock(rewardRankingKey);
        }


    }
    public static Boolean getLock(String key){
       return getLock(key,1);
    }

    public static Boolean getLock(String key ,int lockTime){
        Long setnx = instance.setnx(key, "1");
        if (setnx>0){
            instance.expire(key,lockTime);
        }
        return setnx>0;
    }

    public static Boolean unLock(String key){
        return instance.del(key)>0;
    }
}
