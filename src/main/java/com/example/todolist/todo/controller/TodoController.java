package com.example.todolist.todo.controller;

import com.example.todolist.todo.dto.request.CreateTodoRequest;
import com.example.todolist.todo.dto.request.UpdateTodoRequest;
import com.example.todolist.todo.dto.response.TodoResponse;
import com.example.todolist.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todos/me")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> findMyTodos(){
        return ResponseEntity
                .ok(todoService.findMyTodos());
    }

    @PostMapping
    public ResponseEntity<TodoResponse> create(@Valid @RequestBody CreateTodoRequest dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoService.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable UUID id,
                                               @Valid @RequestBody UpdateTodoRequest dto){
        return ResponseEntity
                .ok(todoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
