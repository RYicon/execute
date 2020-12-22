package com.example.redis.bit;

import com.example.redis.RedisPool;
import redis.clients.jedis.Jedis;

public class Bit {
    static Jedis instance = RedisPool.getInstance();
    static String baiDuUserBitKey = "bit::baidu_user";


    public static void main(String[] args) {

        instance.setbit(baiDuUserBitKey,1L,true);


        System.out.println(instance.getbit(baiDuUserBitKey,1L));
        System.out.println(instance.getbit(baiDuUserBitKey,2L));
    }

}
