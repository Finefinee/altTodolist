package com.example.alttodolist.controller;

import com.example.alttodolist.exception.GlobalExceptionHandler;
import com.example.alttodolist.exception.TodoNotFoundException;
import com.example.alttodolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
@Import(GlobalExceptionHandler.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TodoService todoService;

    @Test
    void getTodoWhenMissingReturnsCustomErrorResponse() throws Exception {
        Long todoId = 1L;
        when(todoService.findTodoEntity(todoId)).thenThrow(new TodoNotFoundException(todoId));

        mockMvc.perform(get("/todos/{id}", todoId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("해당 Todo를 찾을 수 없습니다. id=1"));
    }

    @Test
    void createTodoWhenTodoIsBlankReturnsValidationError() throws Exception {
        String requestBody = """
                {
                  "todo": "   ",
                  "complete": true
                }
                """;

        mockMvc.perform(post("/todos")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("요청 값 검증에 실패했습니다."))
                .andExpect(jsonPath("$.validationErrors.todo").value("할 일 내용은 비어 있을 수 없습니다."));
    }

    @Test
    void createTodoWhenCompleteIsMissingReturnsValidationError() throws Exception {
        String requestBody = """
                {
                  "todo": "장보기"
                }
                """;

        mockMvc.perform(post("/todos")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validationErrors.complete").value("완료 여부는 반드시 입력해야 합니다."));
    }

    @Test
    void getTodoWhenIdIsNotPositiveReturnsValidationError() throws Exception {
        mockMvc.perform(get("/todos/{id}", 0))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("요청 값 검증에 실패했습니다."))
                .andExpect(jsonPath("$.validationErrors.id").value("id는 1 이상의 값이어야 합니다."));
    }

    @Test
    void createTodoWhenPayloadTypeIsInvalidReturnsBadRequest() throws Exception {
        String requestBody = """
                {
                  "todo": "장보기",
                  "complete": "yes"
                }
                """;

        mockMvc.perform(post("/todos")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("요청 본문 또는 파라미터 형식이 올바르지 않습니다."));
    }
}
