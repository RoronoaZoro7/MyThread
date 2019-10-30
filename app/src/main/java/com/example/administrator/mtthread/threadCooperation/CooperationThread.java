package com.example.administrator.mtthread.threadCooperation;

import android.util.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2019/10/21.
 * 实现线程的等待，和通知
 */

public class CooperationThread {
    private static final String TAG = "CooperationThread";
    public static final String CITY = "CITY";
    private int km;
    private String site;

    public CooperationThread(int KM, String site) {
        this.km = KM;
        this.site = site;
    }

    //变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理
    public synchronized void changeKm() {
        this.km = 101;
        notifyAll();//notify只会唤醒工作域的其中一个线程，如果是多线程的情况下建议使用notifyAll
    }

    //变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public synchronized void changeSite() {
        this.site = "BeiJing";
        notifyAll();//notify只会唤醒工作域的其中一个线程，如果是多线程的情况下建议使用notifyAll
    }

    //当快递的里程数大于100时更新数据库
    public synchronized void waitKm() {
        while (this.km < 100) {
            try {
                wait();
                Log.e(TAG, "check km thread["
                        + Thread.currentThread().getName() + "] is be notifyed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "the km is" + this.km + ",I will change db.");

    }

    //当快递到达目的地时通知用户
    public synchronized void waitSite() {
        while (CITY.endsWith(this.site)) {
            try {
                wait();
                Log.e(TAG, "check site thread["
                        + Thread.currentThread().getName() + "] is be notifyed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "the site is" + this.site + ",I will call user.");
    }
}
