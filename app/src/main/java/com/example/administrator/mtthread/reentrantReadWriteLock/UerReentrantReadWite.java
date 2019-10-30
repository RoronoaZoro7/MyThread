package com.example.administrator.mtthread.reentrantReadWriteLock;

import android.util.Log;

import com.example.administrator.mtthread.SleepTools;

import java.util.Random;

/**
 * Created by Administrator on 2019/10/21.
 */

public class UerReentrantReadWite {

    private static final String TAG = "UerReentrantReadWite";

    static final int readWriteRatio = 10;//读写线程的比例
    static final int minthreadCount = 3;//最少线程数
    private static GoodsService goodsService;

    //读操作
    private static class GetThread implements Runnable{

        private GoodsService goodsService;
        public GetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            for(int i=0;i<100;i++){//操作100次
                goodsService.getNum();
            }
           Log.e(TAG,Thread.currentThread().getName()+"读取商品数据耗时："
                    +(System.currentTimeMillis()-start)+"ms");

        }
    }

    //写操做
    private static class SetThread implements Runnable{

        private GoodsService goodsService;
        public SetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            Random r = new Random();
            for(int i=0;i<10;i++){//操作10次
                SleepTools.ms(50);
                goodsService.setNum(r.nextInt(10));
            }
            Log.e(TAG,Thread.currentThread().getName()
                    +"写商品数据耗时："+(System.currentTimeMillis()-start)+"ms---------");

        }
    }

    public static void main(int ii) {
        GoodsInfo goodsInfo = new GoodsInfo("Cup",100000,10000);

        if(ii==1){
            //读写锁
            goodsService = new UseRwLock(goodsInfo);
        }else if(ii==2){
            goodsService = new UseSyn(goodsInfo);//对象锁
        }

        for(int i = 0;i<minthreadCount;i++){
            Thread setT = new Thread(new SetThread(goodsService));
            for(int j=0;j<readWriteRatio;j++) {
                Thread getT = new Thread(new GetThread(goodsService));
                getT.start();
            }
            SleepTools.ms(100);
            setT.start();
        }
    }

}
