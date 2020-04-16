package cn.byhieg.threadtutorial.char06;

import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * @Author: hushuai
 * @Date: 2020/4/16 4:12 下午
 * @Description 业务抛出异常需要发送邮件，但一段时间内（5s）最多发送3封邮件
 */
public class SemphoreDemo implements Runnable {
    private final Semaphore semaphore = new Semaphore(3);

    @Override
    public void run() {
        try {
            throw new Exception();
        } catch (Exception e) {
            if (semaphore.tryAcquire()) {
                try {
                    System.err.println(new Date() + "发送邮件...");
                    Thread.sleep(5000L);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    semaphore.release();
                }
            } else {
                System.out.println("限定时间内发送邮件数量达到上限");
            }
        }
    }
}
