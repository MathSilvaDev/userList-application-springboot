package com.example.todolist.service;

import com.example.todolist.exception.EmailAlreadyExistsException;
import com.example.todolist.exception.IdNotFoundException;
import com.example.todolist.dto.request.CreateUserRequest;
import com.example.todolist.dto.response.UserResponse;
import com.example.todolist.entities.User;
import com.example.todolist.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //GET ALL
    public List<UserResponse> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    //GET BY ID
    public UserResponse findUserById(UUID id){
        User user = getUserById(id);
        return toResponseDTO(user);
    }

    //POST
    public UserResponse createUser(CreateUserRequest dto){

        Optional<User> userOpt = userRepository.findUserByEmail(dto.getEmail());
        userOpt.ifPresent((_) -> {
            throw new EmailAlreadyExistsException();
        });

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
    public UserResponse updateUserById(UUID id, CreateUserRequest dto){
        User user = getUserById(id);

        user.setUserName(dto.getUserName());
        if(dto.getPassword() != null && !dto.getPassword().isBlank()){
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return toResponseDTO(user);
    }

    //DELETE
    public void deleteUserById(UUID id){
        getUserById(id);
        userRepository.deleteById(id);
    }

    //METHODS
    private UserResponse toResponseDTO(User user){
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getUserName(),
                user.getPassword(),
                user.getCreatedAt()
        );
    }

    private User getUserById(UUID id){
        return userRepository
                .findById(id)
                .orElseThrow(IdNotFoundException::new);
    }
}
