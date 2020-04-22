package com.example.lock.reent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁，也叫做递归锁，指的是多次对同一个锁进行加锁操作，都不会阻塞线程。实现思路：记录当前锁正在被哪个线程使用，采用计数来统计lock和unlock的调用次数。正常情况下，lock和unlock的调用次数应该相等，如果不相等就会死锁。
 */
public class ReentrantLockTest implements Runnable {
	ReentrantLock lock = new ReentrantLock(); //定义一个可重入锁

	public void get() {
		lock.lock(); //第一次调用lock()
		System.out.println(Thread.currentThread().getId());
		set();
		lock.unlock();
	}

	public void set() {
		lock.lock(); //第二次调用lock()，而且会成功，说明lock是可重入锁
		System.out.println(Thread.currentThread().getId());
		lock.unlock();
	}

	@Override
	public void run() {
		get();
	}

	public static void main(String[] args) {
		ReentrantLockTest ss = new ReentrantLockTest();
		new Thread(ss).start();
		new Thread(ss).start();
		new Thread(ss).start();
	}
}