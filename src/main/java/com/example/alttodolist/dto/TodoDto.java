package com.example.alttodolist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDto {
    private Long id;
    private String todo;
    private boolean complete;
}