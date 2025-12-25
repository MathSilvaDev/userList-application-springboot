package com.example.todolist.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String userName;

    @Size(min = 8)
    private String password;
}
