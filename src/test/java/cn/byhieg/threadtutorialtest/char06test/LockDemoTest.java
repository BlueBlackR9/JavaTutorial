package cn.byhieg.threadtutorialtest.char06test;

import cn.byhieg.threadtutorial.char06.LockDemo;
import org.junit.Test;

/**
 * @Author: hushuai
 * @Date: 2020/4/16 3:03 下午
 * @Description
 */
public class LockDemoTest {

    @Test
    public void lockDemo() throws InterruptedException {
        LockDemo demo = new LockDemo();
        for (int i = 0; i < 50; i++) {
            new Thread(demo).start();
            Thread.sleep(1000L);
        }
    }
}
