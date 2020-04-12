package com.example.lock.jol;

import com.example.lock.jol.data.A;

public class 轻量级锁 {
    public static void main(String[] args) throws Exception {
        A a = new A();
        long start = System.currentTimeMillis();
//调用同步方法1000000000L 来计算1000000000L的++，对比偏向锁和轻量级锁的性能
//如果不出意外，结果灰常明显
        for (int i = 0; i < 1000000000L; i++) {
            a.parse();
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("%sms", end-start));
    }
}