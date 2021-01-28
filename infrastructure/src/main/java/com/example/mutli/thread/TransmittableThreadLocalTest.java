package com.example.mutli.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 *
 */
public class TransmittableThreadLocalTest {

    static ExecutorService executorService = Executors.newFixedThreadPool(1);
    static TransmittableThreadLocal<String> staticContext = new TransmittableThreadLocal<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        testFutureTask1();
//        testRunnable2();
//        testSubRunnable3();

        //测试静态上下文，主线程不清理上下文时，池化的复用线程会
//        testStaticContext1();
//        testStaticContext2();
//        testDisableIgnoreNullValueSemanticsEqualTrue();
        testRunnable3();
    }

    static void testRunnable3() {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        context.set("叫爸爸");


        Runnable runnable = new Runnable() {

            @SneakyThrows
            @Override
            public void run() {

                Thread.sleep(1000);
                Thread thread = Thread.currentThread();
                System.out.println("儿子在" + staticContext.get());

            }
        };
        context.set("叫爷爷");

        TtlRunnable ttlRunnable = TtlRunnable.get(runnable);
        ttlRunnable.getRunnable().run();


    }

    static void testFutureTask1() throws ExecutionException, InterruptedException {

        executorService.submit(() -> {
        });

        TransmittableThreadLocal<String> ttl = new TransmittableThreadLocal<>();
        ttl.set("叫爸爸");

        FutureTask<String> futureTask = new FutureTask<String>(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Thread.sleep(2000);
                        return "儿子在" + ttl.get();
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

    static void testRunnable2() {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        context.set("叫爸爸");


        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("儿子在" + context.get());
            }
        };
        context.set("叫爷爷");

        TtlRunnable ttlRunnable = TtlRunnable.get(runnable);
        ttlRunnable.getRunnable().run();


    }

    static void testSubRunnable3() {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        context.set("叫爸爸");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Runnable runnable1 = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("儿子在" + context.get());
                    }
                };

                TtlRunnable ttlRunnable2 = TtlRunnable.get(runnable1);
                ttlRunnable2.getRunnable().run();
            }
        };
        context.remove();

        TtlRunnable ttlRunnable = TtlRunnable.get(runnable);
        ttlRunnable.getRunnable().run();


    }

    /**
     * 设置成false时不会重放
     */
    static void testDisableIgnoreNullValueSemanticsEqualTrue(){
//        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>(false);
//        context.set("叫爸爸");

        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<String>(false){
            @Override
            protected String initialValue() {
                return "叫爸爸";
            }
        };

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                System.out.println("儿子在" + context.get());
            }
        };
        context.set(null);

        Thread thread = new Thread(TtlRunnable.get(runnable));
        thread.start();


    }

    static void testStaticContext1() {


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                staticContext.set("叫爸爸");
                Runnable runnable1 = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("儿子在" + staticContext.get());
                    }
                };

                TtlRunnable ttlRunnable2 = TtlRunnable.get(runnable1);
                ttlRunnable2.getRunnable().run();
            }
        };

        TtlRunnable ttlRunnable = TtlRunnable.get(runnable);
        ttlRunnable.getRunnable().run();
    }

    static void testStaticContext2() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Runnable runnable1 = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("儿子在" + staticContext.get());
                    }
                };

                TtlRunnable ttlRunnable2 = TtlRunnable.get(runnable1);
                ttlRunnable2.getRunnable().run();
            }
        };

        TtlRunnable ttlRunnable = TtlRunnable.get(runnable);
        ttlRunnable.getRunnable().run();
    }
}
