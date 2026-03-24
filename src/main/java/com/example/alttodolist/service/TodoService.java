package com.example.alttodolist.service;

import com.example.alttodolist.dto.TodoRequest;
import com.example.alttodolist.entity.TodoEntity;

import java.util.List;

public interface TodoService {
    List<TodoEntity> findAllTodos();

    TodoEntity findTodoEntity(Long id);

    void saveTodo(TodoRequest request);

    void editTodo(Long id, TodoRequest request);

    void deleteTodo(Long id);
}
