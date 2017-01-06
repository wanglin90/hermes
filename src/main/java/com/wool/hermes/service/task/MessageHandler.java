package com.wool.hermes.service.task;

import com.wool.hermes.model.MessageTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by wanglin on 17-1-6.
 */
@Component
public class MessageHandler {

    private static final Logger log = LoggerFactory.getLogger(MessageHandler.class);

    @RabbitListener(queues = "test.aa")
    public void handMessage(Message message) {
        MessageTask msg = MessageTask.fromAmqpMessage(message);
        log.info("receive message: {}", msg.getPayload());
    }

}
