package cn.gkq.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * @author GKQ
 * @Classname LockSupportDemo3
 * @Description 为什么可以先唤醒线程后阻塞线程?
 * (因为unpark获得了一个凭证，之后再调用park方法，就可以名正言顺的凭证消费，故不会阻塞)
 */
public class LockSupportDemo3 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " 开始执行");
        LockSupport.unpark(Thread.currentThread());
        System.out.println("唤醒成功");
        System.out.println("开始阻塞");

        LockSupport.park();
        System.out.println("执行");






    }
}
