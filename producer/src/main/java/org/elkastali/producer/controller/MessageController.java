package org.elkastali.producer.controller;

import org.elkastali.producer.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {


    private RabbitMQProducer producer;

    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }


    @GetMapping("/v1/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        producer.sendMessage(message);
        return ResponseEntity.ok("First message sent to RabbitMQ ...");
    }


    @PostMapping("/v2/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestParam("message") String message){
        producer.sendMessage2(message);
        return ResponseEntity.ok("Second message sent to RabbitMQ ...");
    }
}
