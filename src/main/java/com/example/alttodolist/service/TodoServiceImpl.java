package com.example.alttodolist.service;

import com.example.alttodolist.dto.TodoDto;
import com.example.alttodolist.entity.TodoEntity;
import com.example.alttodolist.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    @Override
    public List<TodoEntity> findAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<TodoEntity> findTodoEntity(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public void saveTodo(TodoDto dto) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTodo(dto.getTodo());
        todoEntity.setComplete(dto.isComplete());
        todoRepository.save(todoEntity);
    }

    @Override
    @Transactional
    public void editTodo(Long id, TodoDto dto) {
        TodoEntity todoEntity = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("todoEntity Not Found :)"));

        todoEntity.setTodo(dto.getTodo());
        todoEntity.setComplete(dto.isComplete());
    }

    @Override
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("todoEntity Not Found :)");
        }
        todoRepository.deleteById(id);
    }
}
