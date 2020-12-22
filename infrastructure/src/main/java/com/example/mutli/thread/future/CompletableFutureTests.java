package com.example.mutli.thread.future;

import java.rmi.ServerException;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * User: laizhenwei
 * Date: 2018-01-30 Time: 22:26
 * Description:
 */

//@SpringBootTest
public class CompletableFutureTests {

   static ExecutorService executor = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws ServerException {
        testMethod();
        myFunc();
    }


    public static void myFunc() throws ServerException {
        // Some code
        CompletableFuture<ServerException> exception = new CompletableFuture<>();
        CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> {
            try { throw new ServerException("111"); }
            catch(ServerException ex) {
                exception.complete(ex);
                throw new CompletionException(ex);
            }
        });
        // Some code running in parallel to someFunc()

        Integer resultOfA;
        try {
            resultOfA = a.join();
        }
        catch(CompletionException ex) {
            if(exception.isDone()) throw exception.join();
            throw ex;
        }

        // some code using resultOfA
    }


    public  static void testMethod() {
        String[] orders = {"1", "2", "3", "4", "5", "6"};
        Arrays.stream(orders).forEach(id -> CompletableFuture.supplyAsync(() -> submit(id), executor).exceptionally(e -> {
            return false;
        }));

        executor.shutdown();
        while (!executor.isTerminated()) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Boolean submit(String order) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("抛一个异常" + order);
    }

}