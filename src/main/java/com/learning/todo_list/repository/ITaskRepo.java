package com.learning.todo_list.repository;

import com.learning.todo_list.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskRepo extends JpaRepository<Task, Integer> {
}
