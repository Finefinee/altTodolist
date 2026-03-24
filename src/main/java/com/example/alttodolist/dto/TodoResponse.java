package com.example.alttodolist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoResponse {
    private final Long id;
    private final String todo;
    private final boolean complete;
}
