package com.example.todolist.user.entity;

import com.example.todolist.todo.entity.Todo;
import com.example.todolist.validations.ValidationConstants;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(nullable = false, length = ValidationConstants.USERNAME_MAX)
    private String userName;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private Instant createdAt;

    @OneToMany(mappedBy = "user")
    private List<Todo> todos;

    public User(String email, String userName, String password){
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
}
