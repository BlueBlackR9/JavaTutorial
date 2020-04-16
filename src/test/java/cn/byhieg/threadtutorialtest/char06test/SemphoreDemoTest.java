package cn.byhieg.threadtutorialtest.char06test;

import cn.byhieg.threadtutorial.char06.SemphoreDemo;
import org.junit.Test;

/**
 * @Author: hushuai
 * @Date: 2020/4/16 3:03 下午
 * @Description
 */
public class SemphoreDemoTest {

    @Test
    public void lockDemo() throws InterruptedException {
        SemphoreDemo demo = new SemphoreDemo();
        for (int i = 0; i < 50; i++) {
            new Thread(demo).start();
            Thread.sleep(1000L);
        }
    }
}
