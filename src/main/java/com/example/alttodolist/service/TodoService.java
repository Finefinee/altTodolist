package com.example.alttodolist.service;

import com.example.alttodolist.DTO.TodoDTO;
import com.example.alttodolist.entity.TodoEntity;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<TodoEntity> findAllTodos();

    Optional<TodoEntity> findTodoEntity(Long id);

    void saveTodo(TodoDTO dto);

    void editTodo(Long id, TodoDTO dto);

    void deleteTodo(Long id);
}