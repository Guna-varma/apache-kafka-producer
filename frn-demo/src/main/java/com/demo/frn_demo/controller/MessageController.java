package com.demo.frn_demo.controller;

import com.demo.frn_demo.model.MessageRequest;
import com.demo.frn_demo.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/publish")
public class MessageController {

    @Autowired
    private KafkaService service;

    // AtomicInteger to maintain an incrementing task number
    private static final AtomicInteger taskCounter = new AtomicInteger(1);


    @PostMapping("/taskUpdate")
    public ResponseEntity<?> updateTask(){

        // Increment the counter and get the updated value
        int taskNumber = taskCounter.getAndIncrement();

        // Call service with the updated message
        String message = "This is your FRN Update to Vedoc Message Count is: " + taskNumber;
        service.updateFRNMessage(message);

        // Return a response
        return ResponseEntity.ok("Task updated: " + message);
    }

    @PostMapping("/randomMessage")
    public ResponseEntity<String> publishMessageToVedoc(@RequestParam String topic, @RequestParam String message) {
        service.sendMessageToVedoc(topic, message);
        return ResponseEntity.ok("Message sent to Kafka topic: " + topic);
    }

    @PostMapping("/producer")
    public ResponseEntity<String> produceMessage(@RequestBody MessageRequest messageRequest) {
        service.sendMessage(messageRequest.getTopic(), messageRequest.getMessage());
        return ResponseEntity.ok("Message sent to Kafka topic: " + messageRequest.getTopic());
    }

}
