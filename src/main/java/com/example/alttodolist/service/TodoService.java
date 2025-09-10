package com.example.alttodolist.service;

import com.example.alttodolist.dto.TodoDto;
import com.example.alttodolist.entity.TodoEntity;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<TodoEntity> findAllTodos();

    Optional<TodoEntity> findTodoEntity(Long id);

    void saveTodo(TodoDto dto);

    void editTodo(Long id, TodoDto dto);

    void deleteTodo(Long id);
}