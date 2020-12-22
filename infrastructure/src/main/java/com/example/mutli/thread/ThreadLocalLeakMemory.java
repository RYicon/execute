package com.example.mutli.thread;

public class ThreadLocalLeakMemory {
    /**
     * Run with jvm parameters: -Xms200m -Xmx200m
     */

    static class BigObject {
        private byte[] data;

        public BigObject() {
            // 100M
            this.data = new byte[100 << 20];
        }
    }

    public static void main(String[] args) throws InterruptedException {
        foobar();

        try {
            new BigObject();
        } catch (OutOfMemoryError e) {
            // 预计抛出次异常，线程睡眠供外部探查内存使用情况
            e.printStackTrace();
            Thread.sleep(10000000L);
        }
    }

    private static void foobar() {
        new ThreadLocal<BigObject>().set(new BigObject());
    }

}
