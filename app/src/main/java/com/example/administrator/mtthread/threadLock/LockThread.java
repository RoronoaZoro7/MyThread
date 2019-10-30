package com.example.administrator.mtthread.threadLock;

import android.util.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2019/10/21.
 * 使用lock关闭，和开启线程 并且使用Condition阻塞，唤醒线程。和wait的区别是，Condition区分了Km，和Site参数，可以进行单独唤醒
 * signal是唤醒其中调用者的线程的其中一个线程，signalAll是唤醒调用者线程的所有
 */

public class LockThread {
    private static final String TAG = "LockThread";
    public static final String CITY = "CITY";
    private int km;
    private String site;
    private Lock lock = new ReentrantLock();
    private Condition kMCondition = lock.newCondition();
    private Condition siteCondition = lock.newCondition();

    public LockThread(int KM, String site) {
        this.km = KM;
        this.site = site;
    }

    //变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理
    public void changeKm() {
        lock.lock();
        try {
            this.km = 101;
            kMCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    //变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public void changeSite() {
        lock.lock();
        try {
            this.site = "beijing";
            siteCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    //当快递的里程数大于100时更新数据库
    public void waitKm() {
        lock.lock();
        try {
            while (this.km < 100) {
                try {
                    kMCondition.await();
                    Log.e(TAG, "check km thread["
                            + Thread.currentThread().getName() + "] is be notifyed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        Log.e(TAG, "the Km is " + this.km + ",I will change db");

    }

    //当快递到达目的地时通知用户
    public void waitSite() {
        lock.lock();

        try {
            while (this.site.endsWith(this.CITY)) {
                try {
                    siteCondition.await();
                    Log.e(TAG, "check site thread[" + Thread.currentThread().getId()
                            + "] is be notifed.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        Log.e(TAG, "the site is " + this.site + ",I will call user");
    }
}
