package com.example.alttodolist.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Map;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    private final int status;
    private final String error;
    private final String message;
    private final Map<String, String> validationErrors;

    private ErrorResponse(int status, String error, String message, Map<String, String> validationErrors) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.validationErrors = validationErrors;
    }

    public static ErrorResponse of(int status, String error, String message) {
        return new ErrorResponse(status, error, message, null);
    }

    public static ErrorResponse of(int status, String error, String message, Map<String, String> validationErrors) {
        return new ErrorResponse(status, error, message, validationErrors);
    }
}
