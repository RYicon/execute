package com.example.redis.set;

import com.alibaba.fastjson.JSON;
import com.example.redis.RedisPool;
import redis.clients.jedis.Jedis;

/**
 * set成员不能重复的集合
 */
public class UVDemo {
    static Jedis instance = RedisPool.getInstance();

    public static void access(String site, String userId) {

        //添加item,重复添加无效
        instance.sadd("user_access_uv::" + site, userId);
    }

    public static long totalUV(String site) {
        //输出该集合的成员数量
        return instance.scard("user_access_uv::" + site);
    }

    public static void main(String[] args) {

        instance.del("user_access_uv::baidu");
        instance.del("user_access_uv::aiqiyi");
        instance.del("user_access_uv::baidu_v2");
        for (int i = 0; i < 100; i++) {
            if (i < 80) {
                for (int j = 0; j < 2; j++) {
                    access("baidu", String.valueOf(i));
                }
            }
            if (i > 50) {
                access("aiqiyi", String.valueOf(i));
            }
        }

        System.out.println("百度访问的用户量："+totalUV("baidu"));


        //判断成员是否在集合里
        System.out.println("用户1是否浏览过：" + instance.sismember("user_access_uv::baidu", "1"));

        System.out.println("百度所有用户：" + JSON.toJSONString(instance.smembers("user_access_uv::baidu")));

        System.out.println("移除百度用户无效的浏览记录：" + instance.srem("user_access_uv::baidu", "2"));
        System.out.println("移除百度用户记录后判断用户是否还在：" + instance.sismember("user_access_uv::baidu", "2"));

        System.out.println("移除百度不存的用户浏览记录：" + instance.srem("user_access_uv::baidu", "102"));

        //并集
        System.out.println("两个网站都浏览过的用户：" + instance.sunion("user_access_uv::baidu", "user_access_uv::aiqiyi"));


        //交集
        System.out.println("查看同时点赞两个网站的用户:" + instance.sinter("user_access_uv::baidu", "user_access_uv::aiqiyi"));

        //差集
        System.out.println("只给百度点赞没有给爱奇艺的用户：" + instance.sdiff("user_access_uv::baidu", "user_access_uv::aiqiyi"));
        System.out.println(instance.sscan("user_access_uv::baidu", 1).getStringCursor());

        instance.sunionstore("user_access_uv::baidu_v2", "user_access_uv::baidu","user_access_uv::aiqiyi");
        System.out.println("爱奇艺合到百度了，所以流量都归百度："+instance.smembers("user_access_uv::baidu_v2"));



    }


}
