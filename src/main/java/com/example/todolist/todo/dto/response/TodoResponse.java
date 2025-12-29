package com.example.todolist.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class TodoResponse {

    private UUID id;
    private String title;
    private String description;
    private boolean completed;
    private Instant createdAt;
}
