package cn.gkq.queue;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author GKQ
 * @Classname QueueTest
 * @Description TODO
 * @Date 2021/3/5
 */
public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.put(4);
//        queue.add(4);
//        queue.add(5);
//        queue.add(6);



    }

}
