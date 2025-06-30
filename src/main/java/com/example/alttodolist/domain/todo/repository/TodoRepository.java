package com.example.alttodolist.domain.todo.repository;

import com.example.alttodolist.domain.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

}