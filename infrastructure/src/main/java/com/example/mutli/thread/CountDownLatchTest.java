package com.example.mutli.thread;

import java.util.concurrent.*;

public class CountDownLatchTest {
    public static void main(String[] args) {

        String a="xx{";
        System.out.println(a.split("}").length);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,10,10L,TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10));
        CountDownLatch countDownLatch = new CountDownLatch(10);
        threadPoolExecutor.execute(()->countDownLatch.countDown());


    }
}
