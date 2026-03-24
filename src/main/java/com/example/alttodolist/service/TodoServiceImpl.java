package com.example.alttodolist.service;

import com.example.alttodolist.dto.TodoRequest;
import com.example.alttodolist.entity.TodoEntity;
import com.example.alttodolist.exception.TodoNotFoundException;
import com.example.alttodolist.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    @Override
    public List<TodoEntity> findAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public TodoEntity findTodoEntity(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    @Override
    public void saveTodo(TodoRequest request) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTodo(request.getTodo());
        todoEntity.setComplete(request.getComplete());
        todoRepository.save(todoEntity);
    }

    @Override
    @Transactional
    public void editTodo(Long id, TodoRequest request) {
        TodoEntity todoEntity = findTodoEntity(id);

        todoEntity.setTodo(request.getTodo());
        todoEntity.setComplete(request.getComplete());
    }

    @Override
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException(id);
        }
        todoRepository.deleteById(id);
    }
}
