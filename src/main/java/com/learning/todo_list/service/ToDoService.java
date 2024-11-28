package com.learning.todo_list.service;

import com.learning.todo_list.model.Task;
import com.learning.todo_list.model.TaskForm;
import com.learning.todo_list.repository.ITaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ToDoService {

    @Autowired
    private ITaskRepo taskRepo;

    public List<Task> getTaskList() {
        return taskRepo.findAll();
    }

    public Task createTask(TaskForm task) {
        Task t = Task.builder()
                .name(task.getName())
                .description(task.getDescription())
                .createdOn(LocalDateTime.now())
                .build();
        if(t == null) {
            throw new RuntimeException("Unable to create Task!");
        }
        taskRepo.save(t);
        return t;
    }

    public void completeTask(int taskId, boolean completed) {
        Task t = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found!!"));
        if(t != null) {
            t.setClosedOn(LocalDateTime.now());
            t.setCompleted(completed);
            taskRepo.save(t);
        }
    }
}
