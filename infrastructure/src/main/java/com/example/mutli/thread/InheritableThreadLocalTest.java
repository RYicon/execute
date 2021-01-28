package com.example.mutli.thread;

import java.util.concurrent.*;

/**
 * 先初始化线程池线程，
 */
public class InheritableThreadLocalTest {


    static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //该行代码执行后，输出null，原因是，子线程中thread的inheritableThreadLocals已经赋值了
//        executorService.submit(() -> {
//        });
        InheritableThreadLocal<String> inh = new InheritableThreadLocal<String>();
        inh.set("叫爸爸");

        FutureTask<String> futureTask = new FutureTask<String>(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Thread.sleep(2000);
                        return inh.get();
                    }
                }
        );
        inh.set("再叫爷爷");

        executorService.submit(futureTask);

        System.out.println(futureTask.get());
        ;
    }


}
