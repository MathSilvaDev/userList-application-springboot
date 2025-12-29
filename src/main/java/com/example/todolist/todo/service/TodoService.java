package com.example.todolist.todo.service;

import com.example.todolist.exception.IdNotFoundException;
import com.example.todolist.todo.dto.request.CreateTodoRequest;
import com.example.todolist.todo.dto.request.UpdateTodoRequest;
import com.example.todolist.todo.dto.response.TodoResponse;
import com.example.todolist.todo.entity.Todo;
import com.example.todolist.todo.repository.TodoRepository;
import com.example.todolist.user.entity.User;
import com.example.todolist.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserService userService;

    public TodoService(TodoRepository todoRepository,
                       UserService userService){
        this.todoRepository = todoRepository;
        this.userService = userService;
    }

    public List<TodoResponse> findMyTodos(){
        User user = userService.getAuthenticatedUser();

        return todoRepository.findAllByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TodoResponse create(CreateTodoRequest dto){
        User user = userService.getAuthenticatedUser();
        Todo todo = new Todo(
                dto.getTitle(),
                dto.getDescription(),
                false,
                user
        );

        todoRepository.save(todo);
        return toResponse(todo);
    }

    @Transactional
    public TodoResponse update(UUID id, UpdateTodoRequest dto){
        User user = userService.getAuthenticatedUser();
        Todo todo = getTodoByIdAndUser(id, user);

        todo.update(
                dto.getTitle(),
                dto.getDescription(),
                dto.isCompleted()
        );
        return toResponse(todo);
    }

    public void delete(UUID id){
        User user = userService.getAuthenticatedUser();
        Todo todo = getTodoByIdAndUser(id, user);

        todoRepository.delete(todo);
    }

    private TodoResponse toResponse(Todo todo){
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted(),
                todo.getCreatedAt()
        );
    }

    private Todo getTodoByIdAndUser(UUID id, User user){
        return todoRepository.findByIdAndUser(id, user)
                .orElseThrow(IdNotFoundException::new);
    }


}
