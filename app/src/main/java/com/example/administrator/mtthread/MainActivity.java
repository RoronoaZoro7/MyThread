package com.example.administrator.mtthread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mtthread.reentrantLock.reentrantLock;
import com.example.administrator.mtthread.reentrantReadWriteLock.UerReentrantReadWite;
import com.example.administrator.mtthread.threadLocal.ThreadLocal;
import com.example.administrator.mtthread.threadPool.MyThreadPoolTest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //线程初始化方法
//        NewThreadTest.main();
        //线程的停止方法
//        StopThreadTest.main();
        //线程的锁
//        SyncThread.main();
        //使用wait,notify 等待，和唤醒线程
//        CooperationThreadTest.main();
        //使用signal，awai 来实现等待，和唤醒线程
//        LockThreadTest.main();
        //使用ThreadLocal
//        ThreadLocal.main();
        //测试重入锁
//        reentrantLock.main();
        //测试读写锁  1是读写锁，2是对象锁
//        UerReentrantReadWite.main(2);
        //线程池封装
        MyThreadPoolTest.main();
    }

}
