package com.example.alttodolist.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
