package com.javaee.dmgv.projetofinal.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaee.dmgv.projetofinal.config.RabbitMQConfig;
import com.javaee.dmgv.projetofinal.domain.Message;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(Message message) {
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_MESSAGES, message);
    }
}
