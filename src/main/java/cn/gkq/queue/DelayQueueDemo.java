package cn.gkq.queue;

import cn.gkq.entity.Message;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author GKQ
 * @Classname DelayQueueDemo
 * @Description TODO
 * @Date 2021/1/25
 */
public class DelayQueueDemo {


    public static void main(String[] args) {
        LinkedBlockingDeque deque = new LinkedBlockingDeque(12);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6, 12, 10, TimeUnit.SECONDS, deque);
        DelayQueue<Message> delayQueue = new DelayQueue<>();
        produce(delayQueue);
        long start = System.currentTimeMillis();
        threadPoolExecutor.execute(new Thread(() -> consume(delayQueue, start), "线程1"));
        threadPoolExecutor.execute(new Thread(() -> consume(delayQueue, start), "线程2"));
        threadPoolExecutor.shutdown();


    }

    private static void consume(DelayQueue<Message> delayQueue, long start) {
        try {

            Message message = delayQueue.take();
            StringBuilder sb = new StringBuilder();
            sb.append(Thread.currentThread().getName())
                    .append("，消费消息：{}").append(message.toString());
            long interval = TimeUnit.SECONDS.convert(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
            System.out.println(sb.toString() + "执行时间：" + interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void produce(DelayQueue<Message> delayQueue) {
        Message message1 = new Message(3L, "吃饭消息体1", 6000L);
        Message message2 = new Message(2L, "吃饭消息体2", 16000L);
        delayQueue.offer(message1);
        delayQueue.offer(message2);

    }

}
