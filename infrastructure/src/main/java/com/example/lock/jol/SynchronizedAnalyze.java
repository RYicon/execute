package com.example.lock.jol;

import com.example.lock.jol.data.NullClass;
import org.openjdk.jol.info.ClassLayout;

public class SynchronizedAnalyze {
    static NullClass a;

    public static void main(String[] args) throws Exception {

        //偏向锁的膨胀和消除比较复杂，所以jdk8 项目启动后，系统会先加载jvm相关信息，为了不会立马启动偏向锁，而是延迟4s后才会开启偏向锁。
        //通过后面参数消除偏向锁的延迟启用 ‐XX:BiasedLockingStartupDelay=0
        Thread.sleep(5000);

        a=new NullClass();

        System.out.println("befre lock");

        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        sync();

        System.out.println("after lock");

        System.out.println(ClassLayout.parseInstance(a).toPrintable());

    }

    public static void sync() throws InterruptedException {

        synchronized (a){

            System.out.println("我也不知道要打印什么");

        }

    }

}
