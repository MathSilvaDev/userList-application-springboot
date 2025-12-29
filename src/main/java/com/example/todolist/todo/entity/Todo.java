package com.example.todolist.todo.entity;

import com.example.todolist.todo.dto.request.UpdateTodoRequest;
import com.example.todolist.user.entity.User;
import com.example.todolist.validations.ValidationConstants;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_todos")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = ValidationConstants.TODOTITLE_MAX)
    private String title;

    @Column(nullable = false, length = ValidationConstants.TODODESCRIPTION_MAX)
    private String description;

    private boolean completed;

    @CreationTimestamp
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Todo(String title, String description, boolean completed, User user){
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.user = user;
    }

    public void update(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }
}
