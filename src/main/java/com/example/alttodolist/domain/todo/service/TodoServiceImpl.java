package com.example.alttodolist.domain.todo.service;

import com.example.alttodolist.domain.todo.dto.TodoDTO;
import com.example.alttodolist.domain.todo.converter.TodoConverter;
import com.example.alttodolist.domain.todo.entity.TodoEntity;
import com.example.alttodolist.global.exception.NotFoundException;
import com.example.alttodolist.domain.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    public TodoEntity getTodoOrElseThrow(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("todoEntity Not Found :)"));
    }

    @Override
    public List<TodoDTO> findAllTodos() {
        List<TodoEntity> entities = todoRepository.findAll();

        return entities.stream().map(TodoConverter::entityToDto).toList();
    }

    @Override
    public TodoDTO findTodo(Long id) {
        TodoEntity todoEntity = getTodoOrElseThrow(id);

        return TodoConverter.entityToDto(todoEntity);
    }

    @Override
    public void saveTodo(TodoDTO dto) {
        TodoEntity todoEntity = TodoConverter.dtoToEntity(dto);
        todoRepository.save(todoEntity);
    }

    @Override
    @Transactional
    public void editTodo(Long id, TodoDTO dto) {
        TodoEntity todoEntity = getTodoOrElseThrow(id);

        if (dto.getTodo() != null) {
            todoEntity.setTodo(dto.getTodo());
        }

        if (dto.getComplete() != null) {
            todoEntity.setComplete(dto.getComplete());
        }
    }

    @Override
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new NotFoundException("todoEntity Not Found :)");
        }
        todoRepository.deleteById(id);
    }
}