package com.learning.todo_list.kafka;

import com.learning.todo_list.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, Task> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, Task> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Task task) {
        LOGGER.info("Message sent -> {}", task.toString());
        Message<Task> message = MessageBuilder
                .withPayload(task)
                .setHeader(KafkaHeaders.TOPIC, "topic-json")
                .build();
        kafkaTemplate.send(message);
    }
}
