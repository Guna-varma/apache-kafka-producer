package com.demo.frn_demo.service;

import com.demo.frn_demo.config.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Logger logger = LoggerFactory.getLogger(KafkaService.class);

    public boolean updateFRNMessage(String message){
        this.kafkaTemplate.send(AppConstants.FRN_MESSAGE_UPDATE, message);
        this.logger.info("message produced");
        return true;
    }

    public Boolean sendMessageToVedoc(String topic, String message) {
        this.kafkaTemplate.send(topic,message);
        return true;
    }

    public void sendMessage(String topic, String message) {
        this.kafkaTemplate.send(topic,message);
    }
}
