package com.example.alttodolist.DTO;

import com.example.alttodolist.entity.TodoEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoDTO {
    private Long id;

    @NotNull(message = "todo는 null이면 안됩니다")
    private String todo;

    @NotNull(message = "complete는 null이면 안됩니다")
    private Boolean complete;
}