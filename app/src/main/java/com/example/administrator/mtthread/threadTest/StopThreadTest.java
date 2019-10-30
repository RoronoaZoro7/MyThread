package com.example.administrator.mtthread.threadTest;

import android.util.Log;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2019/10/21.
 * 线程提供了stop/resume/suspend但是已经过期，
 * stop会直接终止线程，如果线程下载一个东西，下载了一半，调用了stop方法，cpu也会毫不留情的终止这个线程，没有给资源释放工作的机会，导致资源下载失败
 * suspend不会释放线程的锁，可能会造成死锁
 * 所以引入了interrupt，isInterrupted，interrupted
 */

public class StopThreadTest {
    private static final String TAG = "StopThreadTest";

    public static void main() {
//        Thread endThread = new MyThread("endThread");
//        endThread.start();
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        endThread.interrupt();

        Thread endThread = new Thread(new RunnableThread(), "endThread666");
        endThread.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endThread.interrupt();
    }

    static class MyThread extends java.lang.Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            String threadName = Thread.currentThread().getName();
            Log.e(TAG, threadName + " interrrupt flag =" + isInterrupted());
            while (!isInterrupted()) {
                //while(!Thread.interrupted()){
                //while(true){
                Log.e(TAG, threadName + " is running");
                Log.e(TAG, threadName + " inner interrrupt flag =" + isInterrupted());
            }

            Log.e(TAG, threadName + " interrrupt flag =" + isInterrupted());
        }
    }


    static class RunnableThread implements Runnable {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            Log.e(TAG, threadName + " interrrupt flag =" + Thread.currentThread().isInterrupted());
            while (!Thread.currentThread().isInterrupted()) {
                //while(!Thread.interrupted()){
                //while(true){
                Log.e(TAG, threadName + " is running");
                Log.e(TAG, threadName + "inner interrrupt flag =" + Thread.currentThread().isInterrupted());
            }
            System.out.println(threadName+" Stop");
            Log.e(TAG, threadName + " Stop");
            Log.e(TAG, threadName + " interrrupt flag =" + Thread.currentThread().isInterrupted());
        }
    }


    static class CallThread implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "我是可以返回结果的线程";
        }
    }
}
