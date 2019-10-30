package com.example.administrator.mtthread.reentrantReadWriteLock;

import com.example.administrator.mtthread.SleepTools;

/**
 * Created by Administrator on 2019/10/21.
 */

public class UseSyn implements GoodsService {

    private GoodsInfo goodsInfo;

    public UseSyn(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized GoodsInfo getNum() {
        SleepTools.ms(5);
        return this.goodsInfo;
    }

    @Override
    public synchronized void setNum(int number) {
        SleepTools.ms(5);
        goodsInfo.changeNumber(number);

    }
}