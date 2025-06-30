package com.example.alttodolist.repository;

import com.example.alttodolist.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

}