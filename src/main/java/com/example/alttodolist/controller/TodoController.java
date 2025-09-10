package com.example.alttodolist.controller;

import com.example.alttodolist.dto.TodoDto;
import com.example.alttodolist.entity.TodoEntity;
import com.example.alttodolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 조회
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoEntity> entities = todoService.findAllTodos();

        List<TodoDto> todoDtoList = entities.stream().map(entity -> {
            TodoDto dto = new TodoDto();
            dto.setTodo(entity.getTodo());
            dto.setComplete(entity.isComplete());
            return dto;
        }).toList();

        return ResponseEntity.ok(todoDtoList);
    }

    // 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id) {
        TodoEntity entity = todoService.findTodoEntity(id)
                .orElseThrow(() -> new RuntimeException("Todo Not Found :)"));

        TodoDto dto = new TodoDto();
        dto.setTodo(entity.getTodo());
        dto.setComplete(entity.isComplete());

        return ResponseEntity.ok(dto);
    }

    // 등록
    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody TodoDto dto) {
        System.out.println(dto.isComplete());
        todoService.saveTodo(dto);
        return ResponseEntity.ok("등록 성공");
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable Long id, @RequestBody TodoDto dto) {
        todoService.editTodo(id, dto);
        return ResponseEntity.ok("수정 성공");
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("삭제 성공");
    }
}
