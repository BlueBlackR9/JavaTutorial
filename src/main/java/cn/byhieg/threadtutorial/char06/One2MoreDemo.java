package cn.byhieg.threadtutorial.char06;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: hushuai
 * @Date: 2020/4/16 11:27 上午
 * @Description 三个线程按顺序打印1~100的数字，能被2整除的数字由线程A打印，能被3整除的数字由线程B打印，其他的由线程C打印
 */
public class One2MoreDemo {


    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(100);

        new Thread(new Product(blockingQueue)).start();
        new Thread(new CustomeA(blockingQueue), "ThreadA").start();
        new Thread(new CustomeB(blockingQueue), "ThreadB").start();
        new Thread(new CustomeC(blockingQueue), "ThreadC").start();
    }

}

/**
 * 生产消息
 */
class Product implements Runnable {

    private BlockingQueue<Integer> blockingQueue;

    public Product(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            try {
                blockingQueue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 打印能被2整除的数字
 */
class CustomeA implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;

    public CustomeA(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (blockingQueue) {
                    if (blockingQueue.peek() != null && blockingQueue.peek() % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + blockingQueue.take());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 打印能被3整除的数字
 */
class CustomeB implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;

    public CustomeB(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (blockingQueue) {
                    if (blockingQueue.peek() != null && blockingQueue.peek() % 3 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + blockingQueue.take());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 打印不能被2或3整除的数字
 */
class CustomeC implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;

    public CustomeC(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (blockingQueue) {
                    if (blockingQueue.peek() != null && blockingQueue.peek() % 2 != 0 && blockingQueue.peek() % 3 != 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + blockingQueue.take());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
