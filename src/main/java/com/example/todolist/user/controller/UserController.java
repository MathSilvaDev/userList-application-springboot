package com.example.todolist.user.controller;

import com.example.todolist.user.dto.request.CreateUserRequest;
import com.example.todolist.user.dto.request.UpdateUserNameRequest;
import com.example.todolist.user.dto.request.UpdateUserPasswordRequest;
import com.example.todolist.user.dto.response.UserResponse;
import com.example.todolist.user.service.UserService;
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

    //----------
    //Mapping Me
    //----------
    @GetMapping("/me")
    public ResponseEntity<UserResponse> findMeUser(){
        return ResponseEntity
                .ok(userService.findMeUser());
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(dto));
    }

    @PatchMapping("/me/username")
    public ResponseEntity<UserResponse> updateMeUserName(@Valid @RequestBody UpdateUserNameRequest dto){
        return ResponseEntity
                .ok(userService.updateMeUserName(dto));
    }

    @PatchMapping("/me/password")
    public ResponseEntity<UserResponse> updateMeUserPassword(@Valid @RequestBody UpdateUserPasswordRequest dto){
        return ResponseEntity
                .ok(userService.updateMeUserPassword(dto));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMeUser(){
        userService.deleteMeUser();

        return ResponseEntity
                .noContent()
                .build();
    }
}
