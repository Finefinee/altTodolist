package com.example.alttodolist.domain.user.repository;

import com.example.alttodolist.domain.user.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(@NotBlank String username);
    Optional<UserEntity> findByUsername(String username);
}
