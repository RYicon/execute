package com.example.lock.aqs;

import org.checkerframework.checker.units.qual.C;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Test1 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Semaphore semaphore = new Semaphore(3);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        cyclicBarrier.reset();

    }
}
