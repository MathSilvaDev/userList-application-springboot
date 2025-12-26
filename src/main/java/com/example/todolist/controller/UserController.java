package com.example.todolist.controller;

import com.example.todolist.dto.request.CreateUserRequest;
import com.example.todolist.dto.request.UpdateUserNameRequest;
import com.example.todolist.dto.request.UpdateUserPasswordRequest;
import com.example.todolist.dto.response.UserResponse;
import com.example.todolist.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers(){
        return ResponseEntity
                .ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable UUID id){
        return ResponseEntity
                .ok(userService.findUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(dto));
    }

    @PatchMapping("/{id}/username")
    public ResponseEntity<UserResponse> updateUserNameById(@PathVariable UUID id,
                                                           @Valid @RequestBody UpdateUserNameRequest dto){
        return ResponseEntity
                .ok(userService.updateUserNameById(id, dto));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<UserResponse> updateUserPasswordById(@PathVariable UUID id,
                                                           @Valid @RequestBody UpdateUserPasswordRequest dto){
        return ResponseEntity
                .ok(userService.updateUserPasswordById(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id){
        userService.deleteUserById(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
