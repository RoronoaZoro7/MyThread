package com.example.administrator.mtthread.threadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2019/10/21.
 * 线程的初始化方法
 * 三种方法可以开启线程
 * join（）可以控制线程的优先级，在A线程的run方法里调用 B.jion(),会先运行完B在运行A
 */

public class NewThreadTest {
    public static void main(){
        new MyThread().start();
        new Thread(new RunnableThread()).start();
        FutureTask<String> stringFutureTask = new FutureTask<>(new CallThread());
        new Thread(stringFutureTask).start();
        //do my work....
        try {
            //获取线程的返回值
            String s = stringFutureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    static class  MyThread extends java.lang.Thread{
        @Override
        public void run() {
            super.run();
        }
    }


   static class RunnableThread implements Runnable{
        @Override
        public void run() {

        }
    }



    static class CallThread implements Callable<String>{
        @Override
        public String call() throws Exception {
            return "我是可以返回结果的线程";
        }
    }
}
