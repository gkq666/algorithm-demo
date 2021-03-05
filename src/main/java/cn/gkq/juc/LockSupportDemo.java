package cn.gkq.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author GKQ
 * @Classname LockSupportDemo
 * @Description TODO
 */
public class LockSupportDemo {

    static Object object = new Object();

    static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (object) {
                System.out.println(getName() + " is coming");
                //阻塞当前线程
                LockSupport.park();

                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(getName() + " 被打断了");
                }
                System.out.println(getName() + " 继续执行");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread1 = new MyThread("A线程");
        MyThread myThread2 = new MyThread("B线程");
        myThread1.start();
        TimeUnit.SECONDS.sleep(1L);
        myThread2.start();
        TimeUnit.SECONDS.sleep(3L);
        myThread1.interrupt();
        LockSupport.unpark(myThread2);
        myThread1.join();
        myThread2.join();
    }

}
