package com.learning.todo_list.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskForm {
    private String name;
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime closedOn;
    private Boolean completed;
}
