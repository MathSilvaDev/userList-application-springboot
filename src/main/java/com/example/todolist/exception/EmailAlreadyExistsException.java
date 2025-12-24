package com.example.todolist.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("ERROR: EMAIL ALREADY EXISTS");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
