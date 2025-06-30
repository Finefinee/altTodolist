package com.example.alttodolist.service;

import com.example.alttodolist.DTO.TodoDTO;

import java.util.List;

public interface TodoService {
    List<TodoDTO> findAllTodos();

    TodoDTO findTodo(Long id);

    void saveTodo(TodoDTO dto);

    void editTodo(Long id, TodoDTO dto);

    void deleteTodo(Long id);
}