package com.example.todolist.user.dto.request;

import com.example.todolist.validations.ValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = ValidationConstants.USERNAME_MAX)
    private String userName;

    @NotBlank
    @Size(
            min = ValidationConstants.PASSWORD_MIN,
            max = ValidationConstants.PASSWORD_MAX
    )
    private String password;
}
