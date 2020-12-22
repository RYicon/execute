package com.example.redis.sortset;

import com.alibaba.fastjson.JSON;
import com.example.redis.RedisPool;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.Random;

public class Rank {

    static Jedis instance = RedisPool.getInstance();
    static String rewardRankingKey = "reward_ranking::baidu";

    public static void main(String[] args) {
        instance.del(rewardRankingKey);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            reward(rewardRankingKey, "user"+i, new BigDecimal(random.nextInt(100)));
        }
        //分数从小到大
        System.out.println(JSON.toJSONString(instance.zrangeWithScores(rewardRankingKey, 0, -1)));
        //分数从大到小
        System.out.println(JSON.toJSONString(instance.zrevrangeWithScores(rewardRankingKey, 0, -1)));

        //分数区间
        System.out.println(JSON.toJSONString(instance.zrevrangeByScoreWithScores(rewardRankingKey, 10, 0)));


        System.out.println(instance.zscore(rewardRankingKey, "user56"));
        ;
        System.out.println(getScore(rewardRankingKey, "user2"));


    }

    public static void reward(String rankName, String userId, BigDecimal amount) {
        instance.zincrby(rankName, amount.doubleValue(), userId);
    }

    public static Double getScore(String rankName, String userId) {
        return instance.zscore(rankName, userId);
    }

}
