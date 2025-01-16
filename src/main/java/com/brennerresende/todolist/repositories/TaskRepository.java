package com.brennerresende.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brennerresende.todolist.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
