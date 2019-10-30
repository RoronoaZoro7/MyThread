package com.example.administrator.mtthread.threadLock;

/**
 * Created by Administrator on 2019/10/21.
 *  线程signal，await验证类
 */

public class LockThreadTest {
    public static void main(){
        for (int i = 0; i < 3; i++) {
            KmThread kmThread = new KmThread();
            kmThread.start();
        }

        for (int i = 0; i < 3; i++) {
            SiteThread siteThread = new SiteThread();
            siteThread.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lockThreadTest.changeKm();
    }

   static LockThread lockThreadTest = new LockThread(0,LockThread.CITY);

    public static class KmThread extends Thread{
        @Override
        public void run() {
            super.run();
            lockThreadTest.waitKm();
        }
    }

    public static class SiteThread extends Thread{
        @Override
        public void run() {
            super.run();
            lockThreadTest.waitSite();
        }
    }


}
