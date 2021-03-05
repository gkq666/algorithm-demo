package cn.gkq.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author GKQ
 * @Classname CyclicBarrierDemo
 * @Description CyclicBarrier的字面意思是可循环(Cyclic) 使用的屏障(barrier).
 * 它要做的事情是,让一组线程到达一个屏障(也可以叫做同步点)时被阻塞,
 * 知道最后一个线程到达屏障时,屏障才会开门,所有被屏障拦截的线程才会继续干活,
 * 线程进入屏障通过CyclicBarrier的await()方法

 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6, () -> {
            System.out.println("把6个碗洗完了才睡觉");
        });

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "洗完");
            }, "线程"+i).start();
        }
    }
}
