package com.example.mutli.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.*;

/**
 *
 */
public class TransmittableThreadLocalTest {

    static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        executorService.submit(() -> {
        });

        TransmittableThreadLocal<String> ttl = new TransmittableThreadLocal<>();
        ttl.set("叫爸爸");

        FutureTask<String> futureTask = new FutureTask<String>(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Thread.sleep(2000);
                        return "儿子在"+ttl.get();
                    }
                }
        );
        ttl.set("叫爷爷");
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(executorService);
        ttlExecutorService.submit(futureTask);

        System.out.println(futureTask.get());
        ;
        ttl.remove();

    }
}
