package com.example.lock.jol;

import com.example.lock.jol.data.A;
import org.openjdk.jol.info.ClassLayout;

public class Hash和偏向锁互斥 {

    static A a;

    public static void main(String[] args) throws Exception {
        Thread.sleep(5000);
        a = new A();
        int i = a.hashCode();


        System.out.println("befre lock："+ Integer.toHexString(i));
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        a = new A();


        Thread t1 = new Thread() {
            public void run() {
                synchronized (a) {
                    try {
                        synchronized (a) {
                            System.out.println("lock ed");
                            System.out.println(ClassLayout.parseInstance(a).toPrintable());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

        };

        t1.start();

    }
}
