package com.example.alttodolist.domain.todo.controller;

import com.example.alttodolist.domain.todo.dto.TodoDTO;
import com.example.alttodolist.global.exception.NotFoundException;
import com.example.alttodolist.domain.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 조회
    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todoDTOList = todoService.findAllTodos();
        return ResponseEntity.ok(todoDTOList);
    }

    // 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTodo(@PathVariable Long id) {
        try {
            TodoDTO dto = todoService.findTodo(id);
            return ResponseEntity.ok(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 등록
    @PostMapping
    public ResponseEntity<String> createTodo(@Valid @RequestBody TodoDTO dto) {
            todoService.saveTodo(dto);
            return ResponseEntity.ok("등록 성공");
    }

    // 할 일 수정
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateTodo(@Valid @PathVariable Long id, @RequestBody TodoDTO dto) {
        try {
            todoService.editTodo(id, dto);
            return ResponseEntity.ok("수정 성공");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.ok("삭제 성공");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
