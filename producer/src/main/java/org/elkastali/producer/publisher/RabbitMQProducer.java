package org.elkastali.producer.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key_1}")
    private String routingKey;

    @Value("${rabbitmq.routing.key_2}")
    private String routingKey2;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessage(String message){
        LOGGER.info(String.format("First message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }


    public void sendMessage2(String message){
        LOGGER.info(String.format("Second message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey2, message);
    }
}
