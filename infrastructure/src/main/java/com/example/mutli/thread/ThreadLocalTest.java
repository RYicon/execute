package com.example.mutli.thread;

public class ThreadLocalTest {

    static ThreadLocal<String> threadLocal=new ThreadLocal<>();
    static ThreadLocal<String> threadLocal2=new ThreadLocal<>();
    public static void main(String[] args) {
        threadLocal.set("1dsds");
        threadLocal.get();
        System.out.println(threadLocal.get());
        threadLocal.set(null);
        System.gc();
        threadLocal.get();

    }
}
