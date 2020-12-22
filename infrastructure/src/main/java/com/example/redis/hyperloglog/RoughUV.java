package com.example.redis.hyperloglog;

import com.example.redis.RedisPool;
import com.google.common.base.Stopwatch;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * 有0.8%的误差
 */
public class RoughUV {

    static Jedis instance = RedisPool.getInstance();
    static String baiDuHyperUvKey = "hyper_uv::baidu";
    static String aiqiyiHyperUvKey = "hyper_uv::aiyiqi";
    static String baidu_aiqiyiHyperUvKey = "hyper_uv::baidu_aiyiqi";
    static String largeNumberUvKey="hyper_uv::large_number";

    public static void main(String[] args) {

        instance.del(baiDuHyperUvKey, aiqiyiHyperUvKey, baidu_aiqiyiHyperUvKey,largeNumberUvKey);


        for (long i = 0; i < 100L; i++) {
            if (i < 80) {
                for (int j = 0; j < 10; j++) {
                    addView(baiDuHyperUvKey, String.valueOf(i));

                }
            }
            if (i > 10 ) {
                addView(aiqiyiHyperUvKey, String.valueOf(i));
            }

        }


        System.out.println(instance.pfcount(baiDuHyperUvKey));
        System.out.println(instance.pfcount(aiqiyiHyperUvKey));
        instance.pfmerge(baidu_aiqiyiHyperUvKey, baiDuHyperUvKey, aiqiyiHyperUvKey);
        System.out.println(instance.pfcount(baidu_aiqiyiHyperUvKey));

        //可以用来过滤相同内容
        System.out.println("提交未记录的数据："+addView(baiDuHyperUvKey,"user11"));


        Stopwatch started = Stopwatch.createStarted();

        long total=10000000L;
        int pageSize=1000;
        int count=0;

        String[] userIds =new String[pageSize];
        for (long i = 0; i < total; i=i+pageSize) {
            for (int j = 0; j < pageSize && i+j<total; j++) {
               userIds[j]="user"+(i+j);
                count++;
            }
            addView(largeNumberUvKey, userIds);
        }
        started.stop();

        System.out.println("1000w数据处理时间"+started.elapsed(TimeUnit.SECONDS));
        System.out.println("hyper粗略浏览量"+instance.pfcount(largeNumberUvKey));;
        System.out.println("用户真实浏览量"+count);
        started.reset();



    }


    public static Long addView(String site, String... userIds) {
      return  instance.pfadd(site, userIds);
    }
}
