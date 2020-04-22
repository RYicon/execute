package com.example.lock.reent;

import sun.awt.UNIXToolkit;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock implements Runnable {
	private AtomicReference<Thread> owner =new AtomicReference<>();
	public void lock(){
		Thread current = Thread.currentThread();
		while(!owner.compareAndSet(null, current)){
		}
	}
	
	public void unlock (){
		Thread current = Thread.currentThread();
		owner.compareAndSet(current, null);
	}

	public void get() {
		lock(); //第一次调用lock()
		System.out.println(Thread.currentThread().getId());
		set();
		unlock();
	}

	public void set() {
		lock(); //第二次调用lock()，而且会成功，说明lock是可重入锁
		System.out.println(Thread.currentThread().getId());
		unlock();
	}

	public static void main(String[] args) {
		SpinLock spinLock = new SpinLock();

		new Thread(spinLock).start();
//		new Thread(spinLock).start();
//		new Thread(spinLock).start();
	}

	@Override
	public void run() {
		get();
	}


}