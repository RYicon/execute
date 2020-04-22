package com.example.lock.aqs;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.locks.LockSupport;

/**
 * 阻塞线程，真正的使线程停止，aqs的基石
 */
public class ParkTest {
    public static void main(String[] args) {
        Thread thread = new Thread() {

            @Override
            public void run() {
                System.out.printf("开始");
                LockSupport.park();
                System.out.println("结束");
            }
        };

        thread.start();


    }
}
