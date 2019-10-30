package com.example.administrator.mtthread.threadLocal;

import android.util.Log;

/**
 * Created by Administrator on 2019/10/21.
 * 为了做数据分离 当多个线程访问一个数据时，并且需要每个线程都拥有这个数据的单独使用权就用到了threadLocal
 * 这样可以每一个线程都拥有自己单独的threadLocal，里面的值都是各自线程自己拥有的
 */

public class ThreadLocal {
    private static final String TAG = "threadLocal";

    public static void main(){
        ThreadLocal test = new ThreadLocal();
        test.StartThreadArray();
    }

    public void StartThreadArray(){
        Thread[] runs = new Thread[3];
        for(int i=0;i<runs.length;i++){
            runs[i]=new Thread(new TestThread(i));
        }
        for(int i=0;i<runs.length;i++){
            runs[i].start();
        }
    }

    public static class TestThread implements Runnable{
        int id;
        public TestThread(int id){
            this.id = id;
        }
        public void run() {
            Log.e(TAG,Thread.currentThread().getName()+":start");
            Integer s = threadLocal.get();
            s = s+id;
            threadLocal.set(s);
            Log.e(TAG,Thread.currentThread().getName()+" :"
                    +threadLocal.get());
            //threadLocal.remove();
        }
    }


    public final static java.lang.ThreadLocal<Integer> threadLocal = new java.lang.ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 100;
        }
    };



}
