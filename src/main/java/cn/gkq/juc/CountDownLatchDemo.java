package cn.gkq.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author GKQ
 * @Classname CountDownLatchDemo
 * @Description 闭锁是一个同步工具类， 这个类使线程等待其他线程各自执行完毕后再执行
 * 一种同步方法，可以延迟线程的进度直到线程到达某个终点状态。通俗的讲就是，一个闭锁相当于一扇大门，在大门打开之前所有线程都被阻断，
 * 一旦大门打开所有线程都将通过，
 * 但是一旦大门打开，所有线程都通过了，
 * 那么这个闭锁的状态就失效了，门的状态也就不能变了，
 * 只能是打开状态。也就是说闭锁的状态是一次性的，
 * 它确保在闭锁打开之前所有特定的活动都需要在闭锁打开之后才能完成
 *
 * 应用场景：
 *  1、确保某个计算在其需要的所有资源都被初始化之后才继续执行。
 *  二元闭锁（包括两个状态）可以用来表示“资源R已经被初始化”，而所有需要R的操作都必须先在这个闭锁上等待；
 *
 * 2、确保某个服务在其依赖的所有其他服务都已经启动之后才启动；
 *
 * 3、等待直到某个操作的所有参与者都就绪在继续执行。（例如：多人游戏中需要所有玩家准备才能开始）；
 *
 */
public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 准备开始做菜!");
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " 做好了");
            }, "线程"+i).start();
        }
        countDownLatch.await();
        System.out.println("6道菜终于做好了");
    }

}
