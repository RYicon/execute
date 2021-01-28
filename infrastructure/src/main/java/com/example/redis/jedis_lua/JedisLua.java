package com.example.redis.jedis_lua;

import com.example.redis.RedisPool;
import redis.clients.jedis.Jedis;

public class JedisLua {
    public static void main(String[] args) {
        Jedis redis = RedisPool.getInstance();
        System.out.println(redis.eval("redis.call('set','jedis_lua_key','jedis_lua_value' )" +
                "return redis.call('get', 'jedis_lua_key');"));;



    }
}
