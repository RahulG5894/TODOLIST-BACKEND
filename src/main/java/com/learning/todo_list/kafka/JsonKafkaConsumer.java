package com.learning.todo_list.kafka;

import com.learning.todo_list.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "topic-json", groupId = "myGroup")
    public void consume(Task task) {
        LOGGER.info("Json message received -> {}", task.toString());

    }
}
