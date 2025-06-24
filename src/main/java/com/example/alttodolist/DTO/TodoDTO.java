package com.example.alttodolist.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDTO {
    private Long id;
    private String todo;
    private boolean complete;
}