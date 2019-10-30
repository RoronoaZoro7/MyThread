package com.example.administrator.mtthread.reentrantLock;

import android.util.Log;

import com.example.administrator.mtthread.SleepTools;

/**
 * Created by Administrator on 2019/10/21.
 */

public class Test {
    int i = 0;
    private static final String TAG = "Test";

    public synchronized  void useSyn1(){
        i++;
        SleepTools.second(1);
        Log.e(TAG,"synInstance is going..."+i);
        useSyn1();
    }

}
