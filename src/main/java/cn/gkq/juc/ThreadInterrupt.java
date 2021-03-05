package cn.gkq.juc;

/**
 * @author GKQ
 * @Classname ThreadInterrupt
 * @Description TODO
 */
public class ThreadInterrupt {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+" 开始执行");
        System.out.println("第一次 origin " + Thread.interrupted());

        Thread.currentThread().interrupt();
        System.out.println("第一次 " + Thread.interrupted());
        System.out.println("第二次 " + Thread.interrupted());
        System.out.println("第三次 " + Thread.interrupted());
    }

}
