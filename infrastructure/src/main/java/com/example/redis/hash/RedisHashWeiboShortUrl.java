package com.example.redis.hash;

import com.example.redis.RedisPool;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

public class RedisHashWeiboShortUrl {
    //http

    private static String shortUrlAccessCountKey = "short_url_access_count";
    private static String urlMappingKey = "url_mapping";

    private static final String X36 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String[] X36_ARRAY = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");


    public static void main(String[] args) {

        Jedis instance = RedisPool.getInstance();

        String longUrl = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E6%AF%94%E4%BA%9A%E8%BF%AA&fenlei=256&rsv_pq=bab1774e000ee8cb&rsv_t=c6efvFS5bw6WrM649KU9DaUD46IIRFL1FE1VV%2FdrYVkyzMZOauXj0RgeSPI&rqlang=cn&rsv_dl=tb&rsv_sug3=7&rsv_sug1=6&rsv_sug7=100&rsv_sug2=0&rsv_btype=i&prefixsug=%25E6%25AF%2594%25E4%25BA%259A%25E8%25BF%25AA&rsp=4&inputT=4714&rsv_sug4=4711";
        String shortUrl = instance.hexists(urlMappingKey, longUrl)
                ? instance.hget(urlMappingKey, longUrl)
                : getShortUrl(longUrl);


        System.out.println("百度xxx短域名：" + shortUrl);

        System.out.println("浏览次数+1：" + incrementShortUrlAccessCount(shortUrl));
        System.out.println("浏览次数+1：" + incrementShortUrlAccessCount(shortUrl));
        System.out.println("浏览次数+1：" + incrementShortUrlAccessCount(shortUrl));

        System.out.println("浏览次数：" + getShortUrlAccessCount(shortUrl));


        instance.hdel(shortUrlAccessCountKey,shortUrl);


    }

    public static String getShortUrl(String url) {

        Jedis jedis = RedisPool.getInstance();
        Long short_url_seed = jedis.incrBy("short_url_seed",11111111111L);

        StringBuffer sBuffer = new StringBuffer();

        if (short_url_seed == 0) {
            sBuffer.append("0");
        }
        while (short_url_seed > 0) {
            sBuffer.append(X36_ARRAY[(int) (short_url_seed % 36)]);
            short_url_seed = short_url_seed / 36;
        }

        String shortUrl = "https://" + sBuffer.reverse().toString();

        jedis.hset(shortUrlAccessCountKey, shortUrl, "1");
        jedis.hset(urlMappingKey, url, shortUrl);


        jedis.expire(shortUrlAccessCountKey,-1);
        jedis.expire(urlMappingKey,-1);
        return shortUrl;
    }


    public static Long incrementShortUrlAccessCount(String shortUrl) {

        return RedisPool.getInstance().hincrBy(shortUrlAccessCountKey, shortUrl, 1);
    }


    public static Long getShortUrlAccessCount(String shortUrl) {

        return Long.valueOf(RedisPool.getInstance().hget(shortUrlAccessCountKey, shortUrl));
    }


}
