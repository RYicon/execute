package com.example.lock.jol;

import com.example.lock.jol.data.A;
import org.openjdk.jol.info.ClassLayout;

//wait 阻塞当前线程，使对象上的锁变成重量级锁
public class 锁状态变化_2 {
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

                        synchronized (a) {

                            System.out.println("before wait");

                            System.out.println(ClassLayout.parseInstance(a).toPrintable());

                            a.wait();

                            System.out.println(" after wait");

                            System.out.println(ClassLayout.parseInstance(a).toPrintable());

                        }

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                }

            }

        };

        t1.start();

        Thread.sleep(5000);

        synchronized (a) {

            a.notifyAll();

        }

    }
}
