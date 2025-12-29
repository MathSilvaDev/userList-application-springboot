package com.example.todolist.todo.repository;

import com.example.todolist.todo.entity.Todo;
import com.example.todolist.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {
    List<Todo> findAllByUser(User user);

    Optional<Todo> findByIdAndUser(UUID id, User user);
}
