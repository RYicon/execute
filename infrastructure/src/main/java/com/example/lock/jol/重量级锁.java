package com.example.lock.jol;

import com.example.lock.jol.data.A;

import java.util.concurrent.CountDownLatch;

public class 重量级锁 {
    static CountDownLatch countDownLatch = new CountDownLatch(100000000);

    public static void main(String[] args) throws InterruptedException {

        final A a = new A();

        long start = System.currentTimeMillis();
//调用同步方法1000000000L 来计算1000000000L的++，对比偏向锁和轻量级锁的性能
//如果不出意外，结果灰常明显
        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (countDownLatch.getCount() > 0) {
                        a.parse();
                        countDownLatch.countDown();

                    }
                }
            }.start();
        }

        countDownLatch.await();
        long end = System.currentTimeMillis();

        System.out.println(String.format("%sms", end - start));

    }
}
