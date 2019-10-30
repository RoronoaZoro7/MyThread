package com.example.administrator.mtthread.threadCooperation;

/**
 * Created by Administrator on 2019/10/21.
 * 线程wait，notify验证类
 */

public class CooperationThreadTest {
    public static void main(){

        for(int i=0;i<3;i++){//三个线程,等待快递到达地点的变化
            new CheckSite().start();
        }

        for(int i=0;i<3;i++){//三个线程,等待里程数的变化
            new CheckKm().start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        express.changeKm();//快递里程数变化
    }

    private static CooperationThread express = new CooperationThread(0,CooperationThread.CITY);

    /*检查里程数变化的线程,不满足条件，线程一直等待*/
    private static class CheckKm extends Thread{
        @Override
        public void run() {
            express.waitKm();
        }
    }

    /*检查地点变化的线程,不满足条件，线程一直等待*/
    private static class CheckSite extends Thread{
        @Override
        public void run() {
            express.waitSite();
        }
    }

}
