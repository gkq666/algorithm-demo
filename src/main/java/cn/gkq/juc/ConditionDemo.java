package cn.gkq.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author GKQ
 * @Classname ConditionDemo
 * @Description TODO
 */
public class ConditionDemo {



    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        new Thread(() -> {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+ " is coming !");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
            System.out.println(Thread.currentThread().getName()+ " end !");
        }, "A线程").start();

        TimeUnit.SECONDS.sleep(3);


        new Thread(() -> {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+ " is coming !");
            try {
                condition.signal();
            } finally {
                reentrantLock.unlock();
            }
        }, "B线程").start();
    }

}
