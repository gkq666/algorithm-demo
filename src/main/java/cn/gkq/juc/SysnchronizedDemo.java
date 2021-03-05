package cn.gkq.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author GKQ
 * @Classname SysnchronizedDemo
 * @Description TODO
 */
public class SysnchronizedDemo {

    public static void main(String[] args) throws InterruptedException {
        SysnchronizedDemo sysnchronizedDemo = new SysnchronizedDemo();
        Thread thread = new Thread(() -> {
            sysnchronizedDemo.waitThread();
        }, "A线程");
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("开始B线程");
        new Thread(() -> sysnchronizedDemo.notifyThread(), "B线程").start();
    }

    /**
     * 线程等待
     */
    public void waitThread() {
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName() + "is coming！");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "is end！");
        }

    }

    /**
     * 唤醒等待
     */
    public void notifyThread() {
        synchronized (this) {
            System.out.println("唤醒线程");
            notify();
        }

    }

}
