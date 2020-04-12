package com.example.lock.jol;


import com.example.lock.jol.data.NullClass;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * 无锁 低位 001
 */
public class JavaLayout {
    public static void main(String[] args) throws InterruptedException {
//        Thread.sleep(5000);
        System.out.printf(VM.current().details());
        NullClass nullClass = new NullClass();
        System.out.printf(ClassLayout.parseInstance(nullClass).toPrintable());
//      System.out.printf(ClassLayout.parseInstance(NullClass.class).toPrintable());
        //测试别的
        synchronized (nullClass){
            System.out.println("反编译");
        }
    }
}
