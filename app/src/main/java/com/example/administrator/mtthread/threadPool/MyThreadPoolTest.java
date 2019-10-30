package com.example.administrator.mtthread.threadPool;

import android.util.Log;

import java.util.Random;

/**
 * Created by Administrator on 2019/10/22.
 */

public class MyThreadPoolTest {
    private static final String TAG = "MyThreadPoolTest";

    public static void main()  {
        // 创建3个线程的线程池
        MyThreadPool t = new MyThreadPool(3, 0);
        t.execute(new MyTask("testA"));
        t.execute(new MyTask("testB"));
        t.execute(new MyTask("testC"));
        t.execute(new MyTask("testD"));
        t.execute(new MyTask("testE"));
        Log.e(TAG,t.toString());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.destroy();// 所有线程都执行完成才destory
        Log.e(TAG,t.toString());
    }

    // 任务类
    static class MyTask implements Runnable {

        private String name;
        private Random r = new Random();

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {// 执行任务
            try {
                Thread.sleep(r.nextInt(1000) + 2000);
            } catch (InterruptedException e) {
                Log.e(TAG,Thread.currentThread().getId() + " sleep InterruptedException:"
                        + Thread.currentThread().isInterrupted());
            }
            Log.e(TAG,"任务 " + name + " 完成");
        }
    }
}
