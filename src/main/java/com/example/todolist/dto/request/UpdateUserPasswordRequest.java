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
public class UpdateUserPasswordRequest {

    @NotBlank
    @Size(
            min = ValidationConstants.PASSWORD_MIN,
            max = ValidationConstants.PASSWORD_MAX
    )
    private String password;
}
