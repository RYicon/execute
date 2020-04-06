package com.example.mutli.thread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CountDownLatch {
    public static void main(String[] args) {

        String a="xx{";
        System.out.println(a.split("}").length);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,10,10L,TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10));
    }
}
