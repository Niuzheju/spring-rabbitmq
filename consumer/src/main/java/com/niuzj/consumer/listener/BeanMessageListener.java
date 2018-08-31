package com.niuzj.consumer.listener;

import com.baidu.logging.util.Slf4jUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niuzj.model.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class BeanMessageListener implements MessageListener {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(Message message) {
        byte[] bytes = message.getBody();
        try {
            if (bytes != null && bytes.length > 0){
                User user = mapper.readValue(new String(bytes, "utf-8"), User.class);
                if (user == null){
                    return;
                }
                System.out.println(user.getUsername() + ":" + user.getPassword());
            }
        } catch (Exception e) {
            Slf4jUtil.error(e);
        }
    }
}
