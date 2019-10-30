package com.example.administrator.mtthread.threadSyn;

import android.util.Log;

import com.example.administrator.mtthread.SleepTools;

/**
 * Created by Administrator on 2019/10/21.
 * java锁机制，
 */

public class SyncThread {

    private static final String TAG = "SyncThread";

    public static void main(){
        //使用类锁
//        SynThread1 syncThread1 = new SynThread1();
//        syncThread1.start();
//        SynThread1 syncThread2 = new SynThread1();
//        syncThread2.start();
        //使用对象锁
        SyncThread syncThread1 = new SyncThread();
        SynThread1 synThread1 = new SynThread1(syncThread1);
        SyncThread syncThread2 = new SyncThread();
        SynThread1 synThread2 = new SynThread1(syncThread1);
        synThread1.start();
        synThread2.start();
    }

    //使用对象锁
    private static class SynThread1 extends Thread{
        SyncThread syncThread;

        public SynThread1(SyncThread syncThread) {
            this.syncThread = syncThread;
        }

        @Override
        public void run() {
            super.run();
            Log.e(TAG,"TestClass is running..."+syncThread);
            syncThread.useSyn2();
        }
    }
    //对象锁的第一个使用方法
    private synchronized  void useSyn1(){
        SleepTools.second(3);
        Log.e(TAG,"synInstance is going..."+this.toString());
        SleepTools.second(3);
        Log.e(TAG,"synInstance ended "+this.toString());
    }

    //对象锁的第二个使用方法
    private  void useSyn2(){
        synchronized(SyncThread.this){
            SleepTools.second(3);
            Log.e(TAG,"synInstance is going..."+this.toString());
            SleepTools.second(3);
            Log.e(TAG,"synInstance ended "+this.toString());
        }
    }

    //使用类锁
    private static class SynThread2 extends Thread{
        @Override
        public void run() {
            super.run();
            Log.e(TAG,"TestClass is running...");
            synClass1();
        }
    }
    //类锁的第一个使用方法
    public static synchronized void synClass1() {
        SleepTools.second(1);
        Log.e(TAG,"synClass going...");
        SleepTools.second(1);
        Log.e(TAG,"synClass end");
    }
    //类锁的第二种使用方法
    public static void synClass2() {
        synchronized(SyncThread.class){
            SleepTools.second(1);
            Log.e(TAG,"synClass going...");
            SleepTools.second(1);
            Log.e(TAG,"synClass end");
        }
    }

}
