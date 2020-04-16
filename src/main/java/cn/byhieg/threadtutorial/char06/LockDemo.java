package cn.byhieg.threadtutorial.char06;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: hushuai
 * @Date: 2020/4/16 4:11 下午
 * @Description 业务抛出异常需要发送邮件，但一段时间内（5s）不重复发送
 */
public class LockDemo implements Runnable {
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("产生异常...");
            if (lock.tryLock()) {
                try {
                    System.err.println("发送邮件...");
                    Thread.sleep(5000L);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("限定时间内不重复发送邮件");
            }
        }
    }
}
