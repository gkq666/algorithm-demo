package cn.gkq.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author GKQ
 * @Classname LockSupportDemo1
 * @Description 为什么唤醒两次后阻塞两次，但最终结果还会阻塞线程?
 * (因为凭证的数量最多为1，连续调用两次unpark和调用一次unpark效果一样，
 * 只会增加一个凭证;而调用两次park却需要消费两个凭证，证不够，不能放行)
 */
public class LockSupportDemo1 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " 被线程唤醒了");

            System.out.println("-------------");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " 被线程唤醒了");
        }, "A线程");
        thread1.start();
        TimeUnit.SECONDS.sleep(3L);
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            LockSupport.unpark(thread1);
            LockSupport.unpark(thread1);
            System.out.println(Thread.currentThread().getName() + "执行唤醒功能成功");
        }, "B线程");
        thread2.start();
        Thread thread3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            LockSupport.unpark(thread1);
            LockSupport.unpark(thread1);
            System.out.println(Thread.currentThread().getName() + "执行唤醒功能成功");
        }, "C线程");
        thread3.start();
    }

}
