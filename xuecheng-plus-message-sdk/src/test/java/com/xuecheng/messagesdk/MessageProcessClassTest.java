package com.xuecheng.messagesdk;

import com.tunan.messagesdk.service.MessageProcessClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.messagesdk
 * @className: MessageProcessClassTest
 * @author: Jack
 * @description: TODO
 * @date: 2023/9/25 16:06
 * @version: 1.0
 */

@SpringBootTest
public class MessageProcessClassTest {

    @Autowired
    MessageProcessClass messageProcessClass;

    @Test
    public void test() throws InterruptedException {

        System.out.println("开始执行-----》" + LocalDateTime.now());
        messageProcessClass.process(0, 1, "test", 5, 30);
        System.out.println("结束执行-----》" + LocalDateTime.now());
        Thread.sleep(9000000);
    }
}
