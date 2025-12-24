package com.example.todolist.exception;

public class IdNotFoundException extends RuntimeException{

    public IdNotFoundException(){
        super("ERROR: ID NOT FOUND");
    }

    public IdNotFoundException(String msg){
        super(msg);
    }
}
