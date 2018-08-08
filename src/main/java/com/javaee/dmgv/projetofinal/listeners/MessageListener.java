package com.javaee.dmgv.projetofinal.listeners;

import com.javaee.dmgv.projetofinal.services.AcaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.javaee.dmgv.projetofinal.config.RabbitMQConfig;
import com.javaee.dmgv.projetofinal.domain.Message;

@Component
public class MessageListener {

    static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private AcaoService acaoService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MESSAGES)
    public void processMessage(Message message) {
        Gson gson = new Gson();
        logger.info("Message Received: " + gson.toJson(message));

//    acaoService.processPurchase(message.getStockPurchase());
        acaoService.processPurchase(message.getComprarAcao());
    }
}
