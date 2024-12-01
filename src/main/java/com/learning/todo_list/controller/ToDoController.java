package com.learning.todo_list.controller;

import com.learning.todo_list.kafka.JsonKafkaProducer;
import com.learning.todo_list.kafka.KafkaProducer;
import com.learning.todo_list.model.Task;
import com.learning.todo_list.model.TaskForm;
import com.learning.todo_list.service.ToDoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ToDoController {

    private final ToDoService toDoService;
    private final KafkaProducer kafkaProducer;

    private final JsonKafkaProducer jsonKafkaProducer;

    public ToDoController(ToDoService toDoService, KafkaProducer kafkaProducer, JsonKafkaProducer jsonKafkaProducer) {
        this.toDoService = toDoService;
        this.kafkaProducer = kafkaProducer;
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

    @GetMapping("/task-list")
    public ResponseEntity<List<Task>> todoList() {
        return new ResponseEntity<>( toDoService.getTaskList(), HttpStatus.OK);
    }

    @PostMapping("/create-task")
    public ResponseEntity<String> createTask(HttpServletRequest httpReq, @RequestBody TaskForm task) {
        Task t = toDoService.createTask(task);
        kafkaProducer.sendMessage("Task Details: " + t.toString());
        jsonKafkaProducer.sendMessage(t);
        return new ResponseEntity<>("Task created successfully!!!", HttpStatus.CREATED);
    }

    @PutMapping("/complete-task")
    public ResponseEntity<String> completeTask(@RequestParam("taskid") int taskId, @RequestParam("completed") boolean completed) {
        toDoService.completeTask(taskId, completed);
        return new ResponseEntity<>("Task Completed Successfully!!", HttpStatus.OK);
    }
}
