package com.example.mutli.thread;

import java.util.concurrent.TimeUnit;

public class VolatileTest {
    private static boolean flag;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (!flag) {
                    i++;
                }
            }
        };
        thread.start();

        TimeUnit.SECONDS.sleep(1);

        flag = true;

    }
    //不加volatile被优化为；
    //if (!flag)
    //       while (true)
    // i++;
}
