package com.example.alttodolist.domain.todo.converter;

import com.example.alttodolist.domain.todo.dto.TodoDTO;
import com.example.alttodolist.domain.todo.entity.TodoEntity;

public class TodoConverter {
    public static TodoDTO entityToDto(TodoEntity todoEntity) {
        TodoDTO dto = new TodoDTO();
        dto.setId(todoEntity.getId());
        dto.setTodo(todoEntity.getTodo());
        dto.setComplete(todoEntity.getComplete());
        return dto;
    }

    public static TodoEntity dtoToEntity(TodoDTO todoDTO) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setId(todoDTO.getId());
        todoEntity.setTodo(todoDTO.getTodo());
        todoEntity.setComplete(todoDTO.getComplete());
        return todoEntity;
    }
}
