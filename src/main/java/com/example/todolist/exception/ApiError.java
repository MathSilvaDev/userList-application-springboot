package com.example.todolist.exception;

import java.time.Instant;

public record ApiError(
        String message,
        Instant timestamp
){}
