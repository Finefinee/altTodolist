package com.example.alttodolist.global.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException (String message) {
        super(message);
    }
}
