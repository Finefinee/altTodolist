package com.example.alttodolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequest {

    @NotBlank(message = "할 일 내용은 비어 있을 수 없습니다.")
    @Size(max = 100, message = "할 일 내용은 100자 이하여야 합니다.")
    private String todo;

    @NotNull(message = "완료 여부는 반드시 입력해야 합니다.")
    private Boolean complete;
}
