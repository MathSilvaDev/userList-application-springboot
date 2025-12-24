package com.example.todolist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDTO {

    private UUID id;
    private String email;
    private String userName;
    private String password;
    private Instant createdAt;
}
