package com.niuzj.test;

import com.niuzj.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息,通过fanout路由
     * 消息会发送到所有队列
     */
    @Test
    public void test01(){
        rabbitTemplate.convertAndSend("exchange01", "", "测试消息");
    }

    @Test
    public void test02(){
        User user = new User();
        user.setUsername("niuzj");
        user.setPassword("root");
        rabbitTemplate.convertAndSend("exchange01", "", user);
    }

    @Test
    public void test03(){
        Map<String, String> map = new HashMap<>();
        map.put("username", "niuzj");
        map.put("password", "root");
        rabbitTemplate.convertAndSend("exchange02", "yasuo", map);

    }
}
