package cn.gkq.queue;

import java.util.ArrayDeque;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author GKQ
 * @Classname ArrayDequeDemo
 * @Description TODO
 */
public class ArrayDequeDemo {

    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(1);
        arrayDeque.add(2);
        arrayDeque.add(3);
        arrayDeque.addFirst(111);
        System.out.println(arrayDeque.getFirst());
        System.out.println(arrayDeque.getLast());
    }

}
