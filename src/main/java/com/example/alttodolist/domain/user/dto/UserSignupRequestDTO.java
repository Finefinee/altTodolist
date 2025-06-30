package com.example.alttodolist.domain.user.dto;

import com.example.alttodolist.domain.user.entity.role.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private UserRole role;
}