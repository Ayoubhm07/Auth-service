//package com.cognicrafters.authservice.Service;
//
//import com.cognicrafters.authservice.Model.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UsersProducer {
//
//    @Value("users")
//    private String topicJsonName;
//    private static final Logger LOGGER = LoggerFactory.getLogger(UsersProducer.class);
//
//    private KafkaTemplate<String, User> kafkaTemplate;
//
//    public UsersProducer(KafkaTemplate<String, User> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendMessage(User data){
//
//        LOGGER.info(String.format("Message sent -> %s", data.toString()));
//
//        Message<User> message = MessageBuilder
//                .withPayload(data)
//                .setHeader(KafkaHeaders.TOPIC, topicJsonName)
//                .build();
//
//        kafkaTemplate.send(message);
//    }
//}
