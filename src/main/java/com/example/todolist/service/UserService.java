package com.example.todolist.service;

import com.example.todolist.exception.EmailAlreadyExistsException;
import com.example.todolist.exception.IdNotFoundException;
import com.example.todolist.dto.request.UserRequestDTO;
import com.example.todolist.dto.response.UserResponseDTO;
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
    public List<UserResponseDTO> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    //GET BY ID
    public UserResponseDTO findUserById(UUID id){
        User user = getUserById(id);
        return toResponseDTO(user);
    }

    //POST
    public UserResponseDTO createUser(UserRequestDTO dto){

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
    public UserResponseDTO updateUserById(UUID id, UserRequestDTO dto){
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
    private UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getUserName(),
                user.getCreatedAt()
        );
    }

    private User getUserById(UUID id){
        return userRepository
                .findById(id)
                .orElseThrow(IdNotFoundException::new);
    }
}
