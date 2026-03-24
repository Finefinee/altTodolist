package com.example.alttodolist.controller;

import com.example.alttodolist.dto.TodoRequest;
import com.example.alttodolist.dto.TodoResponse;
import com.example.alttodolist.entity.TodoEntity;
import com.example.alttodolist.service.TodoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Validated
public class TodoController {

    private final TodoService todoService;

    // 조회
    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos() {
        List<TodoEntity> entities = todoService.findAllTodos();

        List<TodoResponse> todoResponseList = entities.stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(todoResponseList);
    }

    // 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable @Positive(message = "id는 1 이상의 값이어야 합니다.") Long id) {
        TodoEntity entity = todoService.findTodoEntity(id);
        return ResponseEntity.ok(toResponse(entity));
    }

    // 등록
    @PostMapping
    public ResponseEntity<String> createTodo(@Valid @RequestBody TodoRequest request) {
        todoService.saveTodo(request);
        return ResponseEntity.ok("등록 성공");
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodo(
            @PathVariable @Positive(message = "id는 1 이상의 값이어야 합니다.") Long id,
            @Valid @RequestBody TodoRequest request
    ) {
        todoService.editTodo(id, request);
        return ResponseEntity.ok("수정 성공");
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable @Positive(message = "id는 1 이상의 값이어야 합니다.") Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("삭제 성공");
    }

    private TodoResponse toResponse(TodoEntity entity) {
        return new TodoResponse(entity.getId(), entity.getTodo(), entity.isComplete());
    }
}
