package com.example.todolist.todo.dto.request;

import com.example.todolist.validations.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateTodoRequest {

    @NotBlank
    @Size(max = ValidationConstants.TODOTITLE_MAX)
    private String title;

    @NotBlank
    @Size(max = ValidationConstants.TODODESCRIPTION_MAX)
    private String description;

    private boolean completed;
}
