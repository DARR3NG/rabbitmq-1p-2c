package org.elkastali.producer.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name.first}")
    private String first_Queue;

    @Value("${rabbitmq.queue.name.second}")
    private String second_Queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key_1}")
    private String routingKey_1;

    @Value("${rabbitmq.routing.key_2}")
    private String routingKey_2;

    @Bean
    public Queue firstQueue(){
        return new Queue(first_Queue);
    }


    @Bean
    public Queue secondQueue(){
        return new Queue(second_Queue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding_1(){
        return BindingBuilder
                .bind(firstQueue())
                .to(exchange())
                .with(routingKey_1);
    }

    @Bean
    public Binding binding_2(){
        return BindingBuilder
                .bind(secondQueue())
                .to(exchange())
                .with(routingKey_2);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
