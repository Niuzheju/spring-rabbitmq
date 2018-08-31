package com.niuzj.consumer.listener;

import com.baidu.logging.util.Slf4jUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 普通文本消息监听器
 */
public class TextMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(new String(message.getBody(), "utf-8"));
        } catch (Exception e) {
            Slf4jUtil.error(e);
        }
    }
}
