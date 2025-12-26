package com.example.todolist.dto.request;

import com.example.todolist.validations.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateUserNameRequest {

    @NotBlank
    @Size(max = ValidationConstants.USERNAME_MAX)
    private String userName;
}
