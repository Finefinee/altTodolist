package com.example.alttodolist.domain.todo.service;

import com.example.alttodolist.domain.todo.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    List<TodoDTO> findAllTodos();

    TodoDTO findTodo(Long id);

    void saveTodo(TodoDTO dto);

    void editTodo(Long id, TodoDTO dto);

    void deleteTodo(Long id);
}