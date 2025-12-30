package com.example.todolist.user.service;

import com.example.todolist.config.security.AuthUtil;
import com.example.todolist.user.dto.request.UpdateUserNameRequest;
import com.example.todolist.user.dto.request.UpdateUserPasswordRequest;
import com.example.todolist.exception.AccessDeniedException;
import com.example.todolist.exception.EmailAlreadyExistsException;
import com.example.todolist.user.dto.request.CreateUserRequest;
import com.example.todolist.user.dto.response.UserResponse;
import com.example.todolist.user.entity.User;
import com.example.todolist.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //GET ME
    public UserResponse findMeUser(){
        User authenticatedUser = getAuthenticatedUser();
        return toResponseDTO(authenticatedUser);
    }

    //POST
    public UserResponse createUser(CreateUserRequest dto){

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new EmailAlreadyExistsException();
        }

        User user = new User(
                dto.getEmail(),
                dto.getUserName(),
                passwordEncoder.encode(dto.getPassword())
        );

        userRepository.save(user);
        return toResponseDTO(user);
    }

    //PUT
    @Transactional
    public UserResponse updateMeUserName(UpdateUserNameRequest dto){
        User authenticatedUser = getAuthenticatedUser();
        authenticatedUser.setUserName(dto.getUserName());

        return toResponseDTO(authenticatedUser);
    }

    @Transactional
    public UserResponse updateMeUserPassword(UpdateUserPasswordRequest dto){
        User authenticatedUser = getAuthenticatedUser();
        authenticatedUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        return toResponseDTO(authenticatedUser);
    }

    //DELETE
    public void deleteMeUser(){
        User authenticatedUser = getAuthenticatedUser();
        userRepository.delete(authenticatedUser);
    }

    //METHODS
    private UserResponse toResponseDTO(User user){
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getUserName(),
                user.getCreatedAt()
        );
    }

    public User getAuthenticatedUser(){
        String email = AuthUtil.getAuthenticatedEmail();

        return userRepository.findUserByEmail(email)
                .orElseThrow(AccessDeniedException::new);
    }
}
