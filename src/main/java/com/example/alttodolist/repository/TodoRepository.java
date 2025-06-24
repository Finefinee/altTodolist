package com.example.alttodolist.repository;

import com.example.alttodolist.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    Optional<TodoEntity> findById(Long id);

    // '어노테이션이 없는 매개변수가 @NonNullApi 매개변수를 재정의합니다' 가 뭐지 처음 보는데
}