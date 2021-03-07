package cn.gkq.juc;

import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author GKQ
 * @Classname CLHLockDemo
 * @Description CLH锁是一个基于链表的公平的自旋锁
 * CLH锁原理如下：
 *
 * 首先有一个尾节点指针，通过这个尾结点指针来构建等待线程的逻辑队列，因此能确保线程线程先到先服务的公平性，因此尾指针可以说是构建逻辑队列的桥梁；此外这个尾节点指针是原子引用类型，避免了多线程并发操作的线程安全性问题；
 *
 * 通过等待锁的每个线程在自己的某个变量上自旋等待，这个变量将由前一个线程写入。由于某个线程获取锁操作时总是通过尾节点指针获取到前一线程写入的变量，而尾节点指针又是原子引用类型，因此确保了这个变量获取出来总是线程安全的。
 */
public class CLHLockDemo {

    static int modCount = 0;

    public static void main(String[] args) throws InterruptedException {
        CLHLock clhLock = new CLHLock();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 1; i++) {
            Thread thread = new Thread(() -> {
                clhLock.lock();
                modCount++;
                clhLock.unLock();
                clhLock.lock();

            }, "线程" + i);
            executorService.submit(thread);
        }

        TimeUnit.SECONDS.sleep(12);
        System.out.println("modCount：" + modCount);
        executorService.shutdown();
    }


    /* CLH锁 */
    @Data
    public static class CLHLock {


        private AtomicReference<CLHNode> tailNode;

        private ThreadLocal<CLHNode> predNode;

        private ThreadLocal<CLHNode> curNode;

        public CLHLock() {

            tailNode = new AtomicReference<>(new CLHNode());

            //初始化当前节点
            curNode = ThreadLocal.withInitial(() -> new CLHNode());


            predNode = new ThreadLocal<>();
        }

        /**
         * 获取锁
         **/
        public void lock() {
            String name = Thread.currentThread().getName();
            System.out.println("当前线程：" + name);
            //获取当前线程的 当前节点
            CLHNode currentNode = curNode.get();
            currentNode.setLocked(true);

            CLHNode beforeNode = tailNode.getAndSet(currentNode);
            predNode.set(beforeNode);

            while (beforeNode.locked) {
                System.out.println("线程" + name + "没能获取到锁，进行自旋等待。。。");
            }
            // 能执行到这里，说明当前线程获取到了锁
            System.out.println("线程" + name + "获取到了锁！！！");


        }

        /**
         * 释放锁
         **/
        public void unLock() {
            CLHNode currentNode = curNode.get();
            currentNode.setLocked(false);
            CLHNode clhNode = new CLHNode();
            curNode.set(clhNode);
        }

    }

    @Data
    public static class CLHNode {
        //锁状态默认false 表示线程没有获取到锁
        private volatile boolean locked = false;
    }


}
