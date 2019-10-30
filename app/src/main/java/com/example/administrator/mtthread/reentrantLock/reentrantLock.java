package com.example.administrator.mtthread.reentrantLock;

/**
 * Created by Administrator on 2019/10/21.
 * synchronized关键字隐式的支持重进入，比如一个synchronized修饰的递归方法，在方法执行时，
 * 执行线程在获取了锁之后仍能连续多次地获得该锁。ReentrantLock在调用lock()方法时，
 * 已经获取到锁的线程，能够再次调用lock()方法获取锁而不被阻塞
 */

public class reentrantLock {

    private static Test test;

    public static void main() {
        test = new Test();
        TestThread testThread = new TestThread(test);
        testThread.start();

    }

    public static class TestThread extends Thread {
        Test test;

        public TestThread(Test test) {
            this.test = test;
        }

        @Override
        public void run() {
            super.run();
            test.useSyn1();
        }
    }


}
