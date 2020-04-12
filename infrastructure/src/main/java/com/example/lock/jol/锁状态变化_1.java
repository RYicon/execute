package com.example.lock.jol;

import com.example.lock.jol.data.A;
import org.openjdk.jol.info.ClassLayout;

public class 锁状态变化_1 {
    static A a;

    public static void main(String[] args) throws Exception {

//Thread.sleep(5000);

        a = new A();

        System.out.println("befre lock");

        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        Thread t1 = new Thread() {

            public void run() {

                synchronized (a) {

                    try {

                        Thread.sleep(5000);

                        System.out.println("t1 release");

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                }

            }

        };

        t1.start();

        Thread.sleep(1000);

        System.out.println("t1 lock ing");

        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        sync();

        System.out.println("after lock");

        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        System.gc();

        System.out.println("after gc()");

        System.out.println(ClassLayout.parseInstance(a).toPrintable());

    }

    public static void sync() throws InterruptedException {

        synchronized (a) {

            System.out.println("t1 main lock");

            System.out.println(ClassLayout.parseInstance(a).toPrintable());

        }

    }
}
